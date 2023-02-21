package com.example.composetemplate.ui.home

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composetemplate.BuildConfig
import com.example.composetemplate.R
import com.example.composetemplate.event.NavItemReselectEvent
import com.example.composetemplate.ui.common.PText
import com.example.composetemplate.ui.home.tab1.Tab1Screen
import com.example.composetemplate.ui.home.tab2.Tab2Screen
import com.example.composetemplate.ui.home.tab3.Tab3Screen
import com.example.composetemplate.ui.home.tab4.Tab4Screen
import com.example.composetemplate.util.EventBus
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch
import timber.log.Timber

// 하단탭 관련
sealed class Screen(
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
    val route: String,
    @StringRes val resourceId: Int,
    val content: (@Composable (String, (String) -> Unit, (String) -> Unit) -> Unit)
) {
    object Tab1 : Screen(R.drawable.ic_place_outline, R.drawable.ic_place, "tab1", R.string.tab1, { route, showSnackbar, navigate ->
        Tab1Screen(route = route, showSnackbar = showSnackbar, navigate = navigate)
    })
    object Tab2 : Screen(R.drawable.ic_chat_outline, R.drawable.ic_chat, "tab2", R.string.tab2, { route, showSnackbar, navigate ->
        Tab2Screen(route = route, showSnackbar = showSnackbar, navigate = navigate)
    })
    object Tab3 : Screen(R.drawable.ic_camera_outline, R.drawable.ic_camera, "tab3", R.string.tab3, { route, showSnackbar, navigate ->
        Tab3Screen(route = route, showSnackbar = showSnackbar, navigate = navigate)
    })
    object Tab4 : Screen(R.drawable.ic_settings_outline, R.drawable.ic_settings, "tab4", R.string.tab4, { route, showSnackbar, navigate ->
        Tab4Screen(route = route, showSnackbar = showSnackbar, navigate = navigate)
    })
}

val ITEMS = listOf(Screen.Tab1, Screen.Tab2, Screen.Tab3, Screen.Tab4)
val START_DESTINATION = Screen.Tab1

// 백 키 관련
const val BACK_PRESS_DELAY_TIME: Long = 2000
var backKeyPressedTime: Long = 0
var toast: Toast? = null

// 하단탭에 대한 네비게이션만 처리
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigate: (String) -> Unit,
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    val navController = rememberAnimatedNavController()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // 백키 2회에 종료 처리
    BackCloseHandler(navController, showToast, onBack)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar() },
        bottomBar = {
            MyBottomNavigation(navController, ITEMS) { route ->
                Timber.d("[템플릿] $route 리셀렉")
                scope.launch {
                    EventBus.publish(NavItemReselectEvent(route))
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            HomeNavHost(
                navController = navController,
                items = ITEMS,
                startDestination = START_DESTINATION,
                navigate = navigate,
                showSnackbar = { text ->
                    // showSnackbar
                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(text)
                    }
                }
            )
        }
    }
}

/**
 * 백키 2회에 종료 처리
 */
@Composable
fun BackCloseHandler(
    navController: NavHostController,
    showToast: (String) -> Toast,
    onBack: () -> Unit
) = BackHandler {
    if (!navController.popBackStack()) {
        if (System.currentTimeMillis() > backKeyPressedTime + BACK_PRESS_DELAY_TIME) {
            backKeyPressedTime = System.currentTimeMillis()
            toast = showToast("\'뒤로\' 버튼 한번 더 누르시면 종료됩니다.")
            return@BackHandler
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + BACK_PRESS_DELAY_TIME) {
            toast?.cancel()
            onBack.invoke()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() = TopAppBar(
    title = { PText(stringResource(R.string.app_name))},
    navigationIcon = {
        IconButton(onClick = { }) {
            Icon(Icons.Default.Menu, "Menu")
        }
    }
)

@Composable
fun MyBottomNavigation(navController: NavHostController, items: List<Screen>, onReselect: (String) -> Unit) = NavigationBar {
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    items.forEachIndexed { index, screen ->
        NavigationBarItem(
            icon = { Icon(painterResource(
                if (selectedItem == index) {
                    screen.iconSelected
                } else {
                    screen.icon
                }), null) },
            label = { PText(stringResource(screen.resourceId)) },
            selected = selectedItem == index,
            onClick = {
                selectedItem = index
                // 다른탭으로 이동할때만 네비게이션
                val from = currentDestination?.route
                val to = screen.route
                if (from != to) {
                    navController.navigate(screen.route) {
                        popUpTo(0) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                } else {
                    onReselect.invoke(currentDestination.route!!)
                }
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavHost(
    navController: NavHostController,
    items: List<Screen>,
    startDestination: Screen,
    navigate: (String) -> Unit,
    showSnackbar: (String) -> Unit
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        items.forEach { screen ->
            composable(
                route = screen.route,
                enterTransition = {
                    val from = initialState.destination.route?.substring(3)?.toInt() ?: 0
                    val to = screen.route.substring(3).toInt()
                    slideInHorizontally(initialOffsetX = { fullWidth -> if (from < to) fullWidth else -fullWidth })
                },
                exitTransition = {
                    val from = screen.route.substring(3).toInt()
                    val to = targetState.destination.route?.substring(3)?.toInt() ?: 0
                    slideOutHorizontally(targetOffsetX = { fullWidth -> if (from < to) -fullWidth else fullWidth })
                }
            ) {
                screen.content.invoke(
                    screen.route,
                    // showSnackbar
                    { text -> showSnackbar(text) },
                    // 상세화면 네비게이션
                    { route -> navigate.invoke(route) }
                )
            }
        }
    }
}