# Day 5 : WebView_Search(WebView+Keyboard)

#### 使用語言
* Kotlin
#### 使用元件
* WebView
#### 使用Method
* [WebViewClient](https://developer.android.com/reference/android/webkit/WebViewClient)
>* 無設置 WebViewClient：
> WebView 會請求 Activity Manage 選擇合適的URL 處理方式，一般情況就是啟動瀏覽器來加載URL
>
>* 有設置 WebViewClient 且其中的 shouldOverrideUrlLoading 方法返回true：
>由 host application 處理 URL，WebView不處理
>
>* 有設置 WebViewClient 且其中的 shouldOverrideUrlLoading 方法返回false：
>由 WebView 處理加載該 URL
>
>＃WebViewClient 源碼中 shouldOverrideUrlLoading 方法預設為返回 false
>
>* [參考資料](https://www.jianshu.com/p/3474cb8096da)
>
>* 源碼：
>```java
>     * @param view The WebView that is initiating the callback.
>     * @param url The url to be loaded.
>     * @return True if the host application wants to leave the current WebView
>     *         and handle the url itself, otherwise return false.
>     */
>     public boolean shouldOverrideUrlLoading(WebView view, String url) { 
>     return false;
>     }
>```

* [WebView](https://developer.android.com/reference/android/webkit/WebView)
>* [setWebViewClient](https://developer.android.com/reference/android/webkit/WebView.html#setWebViewClient(android.webkit.WebViewClient))
>```kotlin
>val client = WebViewClient()
>webView.setWebViewClient(client)
>```
>* [loadUrl](https://developer.android.com/reference/android/webkit/WebView#loadUrl(java.lang.String))
>```kotlin
>webView.loadUrl("https://www.google.com/")
>```
* 收鍵盤 hideSoftInputFromWindow
>```kotlin
>val view = window.peekDecorView()
>val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
>inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
>```
>* [window.peekDecorView()](https://developer.android.com/reference/android/view/Window#peekDecorView())
>取得當前的view，若view尚未存在會回傳 null
>
>＃[window.getDecorView](https://developer.android.com/reference/android/view/Window#getDecorView())
>　取得當前 Windows 中最頂層的 View，若上不存在不會回傳null ( 可能會crush? )
>
>* [InputMethodManager](https://developer.android.com/reference/android/view/inputmethod/InputMethodManager)
>用於控制或隱藏輸入法面板的Class
>
>* inputMethodManager.[hideSoftInputFromWindow](https://developer.android.com/reference/android/view/inputmethod/InputMethodManager.html#hideSoftInputFromWindow(android.os.IBinder,%20int))( view.getWindowToken(), 0)
>請求收鍵盤
>
#### 搜尋邏輯
* Google 搜尋為 GET Method
>當使用GET的方法時，會將資訊附加在URL上並作為QueryString的一部分。
>QueryString是一種key/value的組合，從問號「?」開始，每一組值都是用「&」隔開
>
>例如搜尋 good 時上方的網址為：
>https://www.google.com.tw/search?q=good&oq=good&aqs=chrome..69i57j69i60j69i65j69i60l2j0.2686j0j7&sourceid=chrome&ie=UTF-8
>
>其中下方這區段，即為用 GET Method 將搜尋字串 good 傳給 Google
>https://www.google.com.tw/search?q=good
>後方剩下的區段為提供給 Google 的其他資訊

* 依據輸入產出網址並 loadUrl
>依 GET Method 生成 Url 的方式產出搜尋的 Url
>```kotlin
>"https://www.google.com/search?q=" + editText.text.toString()
>```
>再讓WebView loadUrl
>```kotlin
>webView.loadUrl("https://www.google.com/search?q=" + editText.text.toString())
>```

#### 實作成果
![](https://i.imgur.com/Djw75ca.gif)

###### tags: `WebView` `WebViewClient` `GET Method`

> 　－Aria-Lee,   Oct 7  2018 7:17 PM








