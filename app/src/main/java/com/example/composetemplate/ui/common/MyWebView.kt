package com.example.composetemplate.ui.common

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.web.*
import timber.log.Timber

const val WEBVIEW_PROGRESS_ERROR = -1

// 웹뷰 (+ 프로그레스, 에러 화면)
@Composable
fun MyWebView(modifier: Modifier = Modifier, url: String) {
    var webViewProgress by remember { mutableStateOf(0) }
    val webViewState = rememberWebViewState(url = url)
    val webViewNavigator = rememberWebViewNavigator()
    WebView(
        modifier = modifier,
        state = webViewState,
        navigator = webViewNavigator,
        client = object : AccompanistWebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                Timber.d("[템플릿] onReceivedError")
                webViewProgress = WEBVIEW_PROGRESS_ERROR
            }
        },
        chromeClient = object : AccompanistWebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Timber.d("[템플릿] onProgressChanged($newProgress)")
                if (webViewProgress != WEBVIEW_PROGRESS_ERROR) {
                    webViewProgress = newProgress
                }
            }
        },
        onCreated = { webView ->
            with(webView) {
                settings.run {
                    allowFileAccess = true
                    domStorageEnabled = true
                    isHorizontalScrollBarEnabled = false
                    isVerticalScrollBarEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                    javaScriptEnabled = true
                    loadsImagesAutomatically = true
                }
            }
        }
    )
    Timber.d("[템플릿] progress = $webViewProgress, state = ${webViewState.loadingState}")
    when (webViewState.loadingState) {
        LoadingState.Finished -> if (webViewProgress == WEBVIEW_PROGRESS_ERROR) {
            ErrorScreen()
        }
        else -> LoadingScreen(background = Color.Transparent, progress = webViewProgress / 100f)
    }
}