# Day 2 : Discount(Seekbar+Keyboard)

#### 使用語言
* Kotlin
#### 使用元件
* TextView 
* EditText
* SeekBar
#### 使用Method
* setOnSeekBarChangeListener
  ( 偵測SeekBar狀態 )
```kotlin
seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        discount.setText(progress.toString() + "%")
        after.setText("${editText.text.toString().toFloat() * progress/100}")
        pg= progress
    }
            
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
})
```
* addTextChangedListener
  ( 偵測輸入文字變化 ) 
```kotlin
editText.addTextChangedListener(object :TextWatcher{
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(editText.text.toString() != "")
            after.setText("${editText.text.toString().toFloat() * pg/100}")
            else after.setText("0")
        }
})
```

#### 實作成果

![](https://i.imgur.com/TEe6z18.gif)


###### tags: `SeekBar` `addTextChangedListener`
> 　 －Aria-Lee,  Sep 27 2018 5:10 PM

