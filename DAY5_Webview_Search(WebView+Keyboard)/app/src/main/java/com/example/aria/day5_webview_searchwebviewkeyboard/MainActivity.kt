package com.example.aria.day5_webview_searchwebviewkeyboard

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = WebViewClient()
        webView.setWebViewClient(client)
        webView.loadUrl("https://www.google.com/")

        search.setOnClickListener {
            val view = window.peekDecorView()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
            webView.loadUrl("https://www.google.com/search?q=" + editText.text.toString() )
        }
    }
}
