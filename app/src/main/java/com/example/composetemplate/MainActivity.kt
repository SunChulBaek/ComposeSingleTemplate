package com.example.composetemplate

import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.composetemplate.ui.common.MyWebView
import com.example.composetemplate.ui.detail.PhotoDetailScreen
import com.example.composetemplate.ui.home.HomeScreen
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                MainNavHost(
                    showToast = { text ->
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).apply {
                            this.show()
                        }
                    },
                    onBack = { this.finishAffinity() }
                )
            }
        }
    }
}

fun defaultEnterTransition(): EnterTransition = slideInVertically(
    initialOffsetY = { fullHeight -> fullHeight }
)

fun defaultExitTransition(): ExitTransition = slideOutVertically(
    targetOffsetY = { fullHeight -> fullHeight }
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost(
    navController: NavHostController = rememberAnimatedNavController(),
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // 홈
        composable("home") {
            HomeScreen(
                navigate = { route -> navController.navigate(route) },
                showToast = showToast,
                onBack = onBack
            )
        }
        // 상세화면
        composable(
            route = "photo/{title}/{url}",
            arguments = listOf(
                navArgument("url" ) { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            ),
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() }
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val encodedUrl = backStackEntry.arguments?.getString("url")
            val decodedUrl = String(Base64.decode(encodedUrl, 0))
            PhotoDetailScreen(title = title, url = decodedUrl)
        }
        composable(
            route = "webview/{url}",
            arguments = listOf(
                navArgument("url" ) { type = NavType.StringType }
            ),
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() }
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url")
            val decodedUrl = String(Base64.decode(encodedUrl, 0))
            MyWebView(url = decodedUrl)
        }
    }
}