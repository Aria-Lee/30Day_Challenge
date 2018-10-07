# Day 3 : Image picker (Intent + ImageView)

#### 使用語言
* Kotlin
#### 使用元件
* ImageView
* Button
#### 使用Method
* 自定義Button樣式
```kotlin
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    //設置shape為長方形
    android:shape="rectangle">
    //設置圓角半徑
    <corners
        android:bottomLeftRadius="20dp"
        android:bottomRightRadius="20dp"
        android:radius="20dp"
        android:topLeftRadius="20dp"
        android:topRightRadius="20dp" />
    //設置文字與Button邊界距離
    <padding
        android:bottom="10dp"
        android:left="10dp"
        android:right="10dp"
        android:top="10dp" />
    //設置Button顏色
    <solid android:color="#48CFAD" />
</shape>

```
* 請求權限 ( 相機、
>  1. 在 AndroidManifest.xml 中加入下列 uses-permission
>```kotlin
><uses-permission >android:name="android.permission.CAMERA" />
><uses-permission >android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
><uses-permission >android:name="android.permission.READ_EXTERNAL_STORAGE" />
>```
>
> 2. 從[官方文件](https://developer.android.com/reference/android/Manifest.permission)可以找到這三個權限的 Protection level 皆為：
> **Protection level: dangerous**
> 因此必須在App開啟時向使用者請求權限
> 
> ```kotlin
>fun permission(){
>    ActivityCompat.requestPermissions(this, arrayOf(
>    android.Manifest.permission.CAMERA,
>    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
>    android.Manifest.permission.READ_EXTERNAL_STORAGE), 0)
>}
> ```
>
>3. 可以用下列方法檢查使用者目前為拒絕還是允許該權限
>>ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
>>
>>此方法會得到兩種可能結果
>>>* PackageManager.PERMISSION_GRANTED　允許
>>>* PackageManager.PERMISSION_DENIED　拒絕
>

* [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider)
> 
> API 24 版本以上，Android 不再允許在 app 中透漏 file://Uri 給其他 app  ( [官方文件](https://developer.android.com/reference/android/os/FileUriExposedException) )
> 因此 google 提供了 [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider) 來生成 content://Uri 取代 file://Uri
> FileProvider 將隱藏真實的共享檔案路徑，並將路徑轉換為 content:// Uri
> 
>**在 AndroidManifest.xml 中加上 provider**
>>```kotlin
>><provider
>>    android:name="android.support.v4.content.FileProvider"
>>    android:authorities="com.example.aria.day3_image_pickerintentimageview.fileprovider"
>>    android:exported="false"
>>    android:grantUriPermissions="true">
>>    <meta-data
>>        android:name="android.support.FILE_PROVIDER_PATHS"
>>        android:resource="@xml/provider_paths" />
>></provider>
>>```
>>* android:authorities：
>>用來標識 provider 的唯一標誌，同一台手機中一個"authority"只能被一個 app 使用
>>
>>* android:exported：必須設為false，因為 FileProvider 不應被設置為公開
>>
>>* android:grantUriPermissions：控制共享文件的訪問權限
>>
>>* android:resource：於xml 路徑下設置的 provider_paths.xml
>
>**在 res/xml/ 下新增 provider_paths.xml**
>>```kotlin
>><?xml version="1.0" encoding="utf-8"?>
>><paths xmlns:android="http://schemas.android.com/apk/res/android">
>>     <external-path name="external_files" path="."/>
>></paths>
>>```
>>此檔案為 file://Uri 轉換為 content://Uri 的規則
>>* 寫法：  <files-path　path="images/"　name="my_images" />
>>
>>* files-path：
>>共享文件路徑的子元素，一份 provider_paths.xml 需含一至多個下列元素
>>
>>
>>| 　文件路徑下的子元素| 對應路徑 |
>>| -----------| -------- | 
>>| files-path | 內部儲存空間應用私有目錄下的 files/ 目錄<br>等同 Context.getFilesDir() 獲取的路徑   
>>| cache-path  | 內部儲存空間應用私有目錄下的 cache/ 目錄<br>等同 Context.getCacheDir() 獲取的路徑   |
>>| external-path | 外部儲存空間的根目錄<br>等同 Environment.getExternalStorageDirectory() 獲取的路徑   |
>>| external-files-path  | 外部儲存空間應用私有目錄下的 files/ 目錄<br>等同 Context.getExternalFilesDir(null) 獲取的路徑   |
>>| external-cache-path  | 外部儲存空間應用私有目錄下的 cache/ 目錄<br>等同  Context.getExternalCacheDir() 獲取的路徑   |
>>* path：指定當前子元素目錄下需要共享的子目錄名稱
>>
>>* name：用來取代上述path指定的目錄名稱的別名
>>
>>* 上述寫法表示：
>>將 Context.getFilesDir() 獲取的路徑下的目錄 images/ 取代為別名 my_images
>>
>>
>>```kotlin
>><?xml version="1.0" encoding="utf-8"?>
>><paths xmlns:android="http://schemas.android.com/apk/res/android">
>>     <external-path name="external_files" path="."/>
>></paths>
>>```
>
>**取得 content://Uri**
>>1. 定義File
>>val file =File ( 路徑, 路徑下的子路徑或檔案名稱 )
>>
>>2. 取得 uri
>>FileProvider.getUriForFile(context, authorities, file)
>>
>>＃此 authorities 和 AndroidManifest.xml 的 provider 中設定的 authorities 相同即可
>>
* componion object
> 設定componion object參數作為intent回傳的requestCode，供 onActivityResult 判斷
> ```kotlin
> private companion object {
>     val PHOTO_FROM_GALLERY = 1
>     val PHOTO_FROM_CAMERA = 2
> }
> ```
> 
* [Intent](https://developer.android.com/reference/android/content/Intent)
>**intent to Album**
>>要從其他應用程式擷取檔案有兩種方式：
>>* [Intent.ACTION_GET_CONTENT](https://developer.android.com/reference/android/content/Intent?hl=zh-tw#ACTION_GET_CONTENT)：
>>intent 必須 setType ，透過指定的 MIME Type 取得相對應類型的檔案
>>```kotlin 
>>val intent = Intent(Intent.ACTION_GET_CONTENT)
>>intent.setType("image/*")
>>```
>>* [Intent.ACTION_PICK](https://developer.android.com/reference/android/content/Intent?hl=zh-tw#ACTION_PICK)
>>intent 不一定要setType，但須設置Uri並透過指定的Uri來取得該目錄下的檔案
>>```kotlin
>>val uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_Uri
>>val intent = Intent(Intent.ACTION_PICK, uri)
>>```
>>* 設定完 intent 後 [startActivityForResult](https://developer.android.com/reference/android/app/Activity?hl=zh-tw#startActivityForResult(android.content.Intent,%20int))
>>startActivityForResult ( intent : Intent , requestCode : Int)
>>requestCode：設置供後續回傳 result 時判斷用
>>```kotlin
>>startActivityForResult(intent, PHOTO_FROM_GALLERY)
>>```
>>**intent to Camera**
>>* [MediaStore](https://developer.android.com/reference/android/provider/MediaStore).[ACTION_IMAGE_CAPTURE](https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE)
>>```kotlin
>>val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
>>```
>>* 設置並提供 Camera 存放照片的Uri
>>```kotlin
>>lateinit var saveUri: Uri  //外部定義變數
>> 
>>val tmpFile = File(Environment.getExternalStorageDirectory().toString(), System.currentTimeMillis().toString() + ".jpg")
>>val uriForCamera = FileProvider.getUriForFile(this, "com.example.aria.day3_image_pickerintentimageview.fileprovider", tmpFile)
>>saveUri = uriForCamera　　//將Uri存進變數供後續onActivityResult使用
>>intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForCamera)
>>```
>>>* [File](https://developer.android.com/reference/java/io/File?hl=zh-tw) 
>>>[File( pathname : String )](https://developer.android.com/reference/java/io/File.html?hl=zh-tw#File(java.lang.String))
>>>[File( parent-pathname : String, child-pathname : String)](https://developer.android.com/reference/java/io/File.html?hl=zh-tw#File(java.lang.String,%20java.lang.String))
>>>* [MediaStore.EXTRA_OUTPUT](https://developer.android.com/reference/android/provider/MediaStore.html#EXTRA_OUTPUT)
>>>>透過 intent.putExtra 的方式，通知相機新照片的儲存位置(Uri)，
>>>>KEY 為官方設定好的 MediaStore.EXTRA_OUTPUT
>>>>
>>>>＃若 intent 至相機時"**無**"提供Uri：
>>>>　照片會以 intent.putextra("data", bitmap) 的方式回傳至 result
>>>>
>>>>＃若 intent 至相機時"**有**"提供Uri：
>>>>　則 result 回傳的 data 會為 null ， 故需自行另存 Uri 以供後續讀圖使用
>>>>
>>>* 設定完 intent 後 [startActivityForResult](https://developer.android.com/reference/android/app/Activity?hl=zh-tw#startActivityForResult(android.content.Intent,%20int))
>>>```kotlin
>>>startActivityForResult(intent, PHOTO_FROM_CAMERA)
>>>```
* override [onActivityResult( requestCode: Int, resultCode: Int, data: Intent? )](https://developer.android.com/reference/android/app/Activity?hl=zh-tw#onActivityResult(int,%20int,%20android.content.Intent))
>* 參數
>>* requestCode：前面設置用來辨別是哪個 intent 回傳的結果
>>
>>* resultCode：確認 intent 成功與否
>>
>>* data ：intent 帶回的資料
>>
>>```kotlin
>>override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
>>    super.onActivityResult(requestCode, resultCode, data)
>>    when (requestCode) {
>>        PHOTO_FROM_GALLERY -> {
>>            when (resultCode) {
>>                Activity.RESULT_OK -> { }
>>                Activity.RESULT_CANCELED -> { }
>>            }
>>        }
>>
>>        PHOTO_FROM_CAMERA -> {
>>            when (resultCode) {
>>                Activity.RESULT_OK -> { }
>>                Activity.RESULT_CANCELED -> { }
>>            }
>>        }
>>    }
>>}
>>```
>* 相簿回傳處理
>>```kotlin
>>val uri = data!!.data                       
>>imageView.setImageURI(uri)
>>```
>>
>>＃相簿會以 Intent( action: String, uri: Uri) 的方式回傳 Uri
>>　須用 data.data 的方式取得 Uri
>>＃data.data = data.getData()
>>
>* 相機回傳處理
>>透過 [Glide](https://github.com/bumptech/glide) 套件利用前面存好的 Uri 載入圖片至 imageView ( 避免產生OOM問題)
>>```kotlin
>>Glide.with(this).load(saveUri).into(imageView)
>>```
>>
>>＃若前面無提供 Uri 給相機，
>>　會以 intent.putextra("data", bitmap) 的方式回傳 bitmap
>>　須用 data.extra.get("data") 方式取得
>>

#### 實作成果

![](https://i.imgur.com/6xiWIa7.gif)

###### tags: `Album` `Camera` `Permission` `FileProvider` `onActivityResult` `Uri` `Glide`
> 　－Aria-Lee,  Oct 7 2018 5:00 PM
