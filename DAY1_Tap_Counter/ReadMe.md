# Day 1 : Tap Counter

#### 使用語言
*   Kotlin
#### 使用元件
* TextView 
* Button
#### 使用View
* Menu
#### 使用Method
* setOnClickListener
  ( 設定點擊事件 )
```kotlin=
button.setOnClickListener {
    a = a+1
    textView.setText(a.toString())
}
```
* override fun onCreateOptionsMenu　
  ( 載入自定義Menu ) 
```kotlin=
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    super.onCreateOptionsMenu(menu)
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
}
```
* override fun onOptionsItemSelected
  ( 設定自定義Menu Item的事件)
``` kotlin=
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.reset) {
        a = 0
        textView.setText(a.toString())
        }
    return true
}
```
#### 實作成果

![](https://i.imgur.com/cLCpS9N.gif)


###### tags: `setOnClickListener` `menu`
> 　[name=Aria-Lee]　[time=Thu, Sep 27, 2018 4:39 PM]
