package com.example.endlesspagerscrollingsample.webImpl

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.AppCompatImageView


object VideoPlayerHelper {

    @SuppressLint("SetJavaScriptEnabled")
    fun configureWebView(
        activity: Activity,
        webView: WebView,
        placeHolder: AppCompatImageView,
        htmlUrl: String,
        videoUrl: String,
    ) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
        }

        webView.apply {
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    activity.runOnUiThread {
                        evaluateJavascript("playVideo('${videoUrl}');", null);
                    }
                }
            }

            addJavascriptInterface(object : Any() {
                @JavascriptInterface
                fun onVideoReady() {
                    activity.runOnUiThread {
                        Log.e("VideoPlayerHelper", "onVideoReady")
                        placeHolder.visibility = View.GONE
                    }
                }

                @JavascriptInterface
                fun onVideoError(errorMessage: String) {
                    activity.runOnUiThread {
                        Log.e("VideoPlayerHelper", "onVideoError : $errorMessage")
                        placeHolder.visibility = View.VISIBLE
                    }
                }
            }, "Android")

            activity.runOnUiThread {
                loadUrl(htmlUrl)
            }
        }
    }
}