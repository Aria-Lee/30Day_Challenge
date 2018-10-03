# Day 4 : Scalable_ImageView(MotionEvent)

#### 使用語言
* Kotlin
#### 使用元件
* ImageView
#### 使用Method
* setOnTouchListener
  ( 偵測 ImageView 觸摸狀態 )
  
```kotlin
moveImage.setOnTouchListener(mOnTouchListener)

val mOnTouchListener = object : View.OnTouchListener {
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event!!.action and MotionEvent.ACTION_MASK) {
            //設置事件
        }
        //通知ViewGroup要接收此事件，事件將不往下傳遞
        return true 
    }
}      
```
* [MotionEvent](https://developer.android.com/reference/android/view/MotionEvent)
> **MotionEvent.action**：
>> [ACTION_DOWN](https://developer.android.com/reference/android/view/MotionEvent#ACTION_DOWN) ： 第一根手指按下時觸發
>> 
>> [ACTION_POINTER_DOWN](https://developer.android.com/reference/android/view/MotionEvent.html#ACTION_POINTER_DOWN) ： 第二、三、四..等等的手指按下時觸發
>> 
>> [ACTION_MOVE](https://developer.android.com/reference/android/view/MotionEvent.html#ACTION_MOVE) ： 螢幕上觸控點滑動時觸發
>> 
>> [ACTION_POINTER_UP](https://developer.android.com/reference/android/view/MotionEvent.html#ACTION_POINTER_UP) ： 在螢幕剩一個觸控點之前，手指離開螢幕時觸發
>> 
>> [ACTION_UP](https://developer.android.com/reference/android/view/MotionEvent.html#ACTION_UP) ： 最後一根手指離開螢幕時觸發
>>
>>:::info
>>＃目前測試發現，三指同時按下時，event.pointerCount 會>>為 2，
>>　且無論手指依序或同時起來，ACTION_POINTER_UP 及 ACTION_UP 皆不會觸發
>>　這部分仍待了解原因
>>:::
> **event**：
>>[event.rawX](https://developer.android.com/reference/android/view/MotionEvent.html#getRawX()) / [event.rawY](https://developer.android.com/reference/android/view/MotionEvent.html#getRawY())：觸控點相對於螢幕左上角的坐標
>>
>>[event.getX(Index](https://developer.android.com/reference/android/view/MotionEvent.html#getX(int))) / [event.getY(Index)](https://developer.android.com/reference/android/view/MotionEvent.html#getY(int))：觸控點相對於 view 自身左上角的坐標
>>
>>[event.pointerCount](https://developer.android.com/reference/android/view/MotionEvent.html#getPointerCount())：目前螢幕上觸控點的數量
>>
>>[event.findPointerIndex(Id)](https://developer.android.com/reference/android/view/MotionEvent.html#findPointerIndex(int))：取得觸摸點的 Index 值
>>
>>[event.getPointerId(Index)](https://developer.android.com/reference/android/view/MotionEvent.html#getPointerId(int))：取得觸摸點的 Id 
>>:::info
>>＃ Id : 每根手指從按下至離開，會擁有一個固定Id
>>＃ Index : 範圍為 [ 0 , pointerCount-1]，且每根手指的Index有可能會變動

* View.layout(l, t, r, b)
> l ： view.left
> t ： view.top
> r ： view.right = view.left + view.width
> b ： view.bottom = view.top + view.height
>:::info
>＃ View.x = view.left + view.translateX
>:::

* 座標系統
 ![](https://i.imgur.com/dhni4RA.png)
 
#### 設計邏輯
* 定義變數 mode 供 ACTION_MOVE 使用
```kotlin
companion object {
    val MODE_NONE = 0
    val MODE_MOVE = 1
    val MODE_ZOOM = 2
} 

ACTION_DOWN -> { mode = MODE_MOVE }

ACTION_POINTER_DOWN -> { 
    if (event.pointerCount <= 2) {
        //兩指皆在View內才觸發
        if(條件){ mode = MODE_ZOOM } 
    } 
    else mode = MODE_NONE
}

ACTION_POINTER_UP -> { mode = MODE_NONE }
```
* 拖曳計算
> 目標：找出新位置的 view.x 及 view.y 並設置
> 作法：
> ACTION_DOWN 時先將要扣掉的長度存進變數
> ACTION_MOVE 時設定新的 view.x 及 view.y
> ![](https://i.imgur.com/UZzGs6D.png)
> 
>Code：
>```kotlin
>ACTION_DOWN -> {
>    xToSub = event.raxX - view.x
>    yToSub = event.raxY - view.y
>}
>
>ACTION_MOVE -> {
>    view!!.x = event.rawX - xToSub
>    view.y = event.rawY - yToSub
>}
>```

* 拖曳邊界，避免 View 跑出畫面回不來
> 1. 自定義期望的拖曳邊界
> 2. ACTION_UP時，用 view 當前的上下左右判斷是否跑出邊界
> 3. 若超出邊界，則重新設置 view.x 及 view.y

* 縮放計算
> 1. ACTION_POINTER_DOWN 時取得兩觸控點的距離及中點
> :::info
> ＃ 不能用 event.rawX，因為無法透過 Index 或 Id 取得兩個點分別的 event.rawX
> :::
> 2. 透過中點座標及初始距離找 left 的位置
> left = midX - (oriX * ratio) + view.translateX
> 
>![](https://i.imgur.com/yYltSmO.png)
> :::info
> ＃ 沒加 view.translateX 的話求出來的為 view.x
> :::
> 3. 用上述方式取得 top 
> 4. right = left + (原 width * ratio)
>    bottom = top + (原 height * ratio)
> 5. 設置view.layout(left, top, right, bottom)

* 避免縮到太小
> 設定 view 的最小長、寬
> 若 (原 width * ratio) < 最小值，則將 width 設為自定義的最小值

#### 實作成果
![](https://i.imgur.com/jqsv0NK.gif)


###### tags: `onTouch` `MotionEvent` `座標系統` `多指觸控` `View` `Layout` 
> 　－Aria-Lee,  Oct 2 2018 4:23 PM
