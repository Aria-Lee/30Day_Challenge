package com.example.fish.day19_littlebirdsoundmediaplayermediarecorder

import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.content.pm.PackageManager
import android.media.AudioRecord
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.*
import android.os.Environment.DIRECTORY_ALARMS
import android.support.v7.app.AppCompatActivity
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.support.v4.provider.DocumentFile
import android.support.v7.app.AlertDialog
import android.text.TextUtils.split
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.SeekBar
import com.example.fish.day19_littlebirdsoundmediaplayermediarecorder.R.id.textView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.net.URI
import java.util.jar.Manifest

@TargetApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaRecorder: MediaRecorder
    lateinit var animator: ObjectAnimator
    lateinit var thread: Thread
    val handler = Handler()
    lateinit var myUri : Uri
    lateinit var oriFile : File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityInit()
//        val  sd=Environment.getExternalStorageDirectory();
//        val path=sd.getPath()+"/aaaaa"

    }

    fun activityInit() {
        mediaPlayer = MediaPlayer.create(this, R.raw.country_cue_1)
        animator = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f)
        animator.duration = 2000
        animator.interpolator = LinearInterpolator()
        mediaPlayerAndProgressUpdate()
        startAndPause.setOnClickListener(playListener)
        stop.setOnClickListener(playListener)
        seekBar.setOnSeekBarChangeListener(seekBarListener)
        progressSeekBar.setOnSeekBarChangeListener(progressSeekBarListener)
        record.setOnClickListener(recordListener)
//        choose.setOnClickListener(chooseListener)
        setThread()
        getPermission()
        addDirectory()
    }

    fun addDirectory(){
        val file=File(Environment.getExternalStorageDirectory().path+"/aaaaa")
        if(!file.exists()) file.mkdir()
    }

    fun mediaPlayerAndProgressUpdate(){
        mediaPlayer.setOnCompletionListener(mediaPlayerListener)
        this.progressSeekBar.max = mediaPlayer.duration
    }

    fun setThread() {
        thread = Thread(Runnable {
            if (progressSeekBar.progress < progressSeekBar.max) {
                progressSeekBar.progress = mediaPlayer.currentPosition
            }
            handler.postDelayed(thread, 200)
        })
    }

    fun getPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            0 -> {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    AlertDialog.Builder(this)
                        .setTitle("提醒")
                        .setMessage("無提供麥克風權限將無法使用錄音功能")
                        .create()
                        .show()
                }
            }
        }
    }


    val playListener = View.OnClickListener {
        when (it) {
            startAndPause -> {
                if (mediaPlayer.isPlaying) {
                    startAndPause.setText("START")
                    animator.pause()
                    mediaPlayer.pause()
                    handler.removeCallbacks(thread)
                } else {
                    if (animator.isPaused) animator.resume()
                    else {
                        animator.repeatCount = -1
                        animator.start()
                        println("aaaaaa $animator")

                    }
                    startAndPause.setText("PAUSE")
                    startAndPause.setText("PAUSE")
                    mediaPlayer.start()
                    handler.postDelayed(thread, 200)
                }
            }

            stop -> {
                handler.removeCallbacks(thread)
                animator.end()
                mediaPlayer.stop()
                mediaPlayer.prepare()
                progressSeekBar.progress = 0
//                mediaPlayer.reset()
                startAndPause.setText("START")
            }
        }
    }

    val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            mediaPlayer.setVolume(progress / 100f, progress / 100f)
            textView.text = "Volume : $progress %"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    val progressSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) mediaPlayer.seekTo(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            handler.removeCallbacks(thread)
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            handler.post(thread)
        }

    }

    val mediaPlayerListener = MediaPlayer.OnCompletionListener {
        handler.removeCallbacks(thread)
        mediaPlayer.stop()
        mediaPlayer.prepare()
        animator.end()
        progressSeekBar.progress = 0
        startAndPause.text = "Start"
    }

    val recordListener = View.OnClickListener {
        it as Button
        when (it .text) {
            "RECORD" -> {
                oriFile = File(Environment.getExternalStorageDirectory() , "aaaaa/new.3gp")
                myUri = FileProvider.getUriForFile(this, "day19",oriFile)
                mediaRecorder = MediaRecorder()
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                mediaRecorder.setOutputFile(oriFile.absolutePath)
                mediaRecorder.prepare()
                mediaRecorder.start()
                it.text = "STOP"
            }

            "STOP" -> {
                mediaRecorder.stop()
                mediaRecorder.release()
                mediaPlayer = MediaPlayer.create(this, myUri)
                mediaPlayerAndProgressUpdate()
                it.text = "RECORD"
            }
        }

    }

//    val chooseListener = View.OnClickListener {
////        val uri = Uri.parse(Environment.getExternalStorageDirectory().getPath())
////                + "/Alarms/")
////        val storageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
////        val volume = storageManager.primaryStorageVolume
////        uri = FileProvider.getUriForFile(this, "day19",Environment.getExternalStorageDirectory())
////        val intent = volume.createAccessIntent(Environment.DIRECTORY_ALARMS)
//        val file = File(Environment.getExternalStorageDirectory(), "/aaaaa")
//        println("**********  ${Uri.fromFile(file)}")
//
//        val uri = FileProvider.getUriForFile(this, "day19", file)
//        grantUriPermission(this.packageName, uri, FLAG_GRANT_READ_URI_PERMISSION)
////        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//        val intent = Intent(Intent.ACTION_VIEW)
//
////        intent.addCategory(Intent.CATEGORY_OPENABLE)
//        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uri)
////        intent.setType("*/*")
//        println("**********  ${uri}")
////        intent.flags= FLAG_GRANT_READ_URI_PERMISSION
//
////         val intent = Intent(ACTION_PICK,
////             )
//
////        intent.addCategory(Intent.CATEGORY_APP_MUSIC)
////        println("********** $uri")
//
////        val intent = Intent(Intent.ACTION_GET_CONTENT)
////        intent.setType("*/*")
////        startActivityForResult(intent, 0)
//        startActivity(Intent.createChooser(intent, "choose"))
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode){
//            0 ->{
//                when (resultCode) {
//                    Activity.RESULT_OK -> {
//                        val uri = data!!.data
//                        println("********** RESULT_OK $uri")
////                        val a = DocumentFile.fromTreeUri(this, uri)
////                        val b =  a!!.findFile("Alarms")
////                        println("********** RESULT_OK a ${a.uri}")
////                        println("********** RESULT_OK b ${b?.uri}")
////                        if ("com.android.externalstorage.documents".equals(uri.getAuthority())) { // 【ExternalStorageProvider】
////                            val id = DocumentsContract.getDocumentId(uri)
////                            val split = id.split(":")
////                            val type = split[0]
////                            if ("primary".equals(type, ignoreCase = true)) println("********** RESULT_OK ${Environment.getExternalStorageDirectory()}  /  $split[1]}")
////                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
////                        val a = DocumentFile.fromTreeUri(this, uri)
////////                        println("********** ${a!!.uri}")
////                        val intent = Intent(ACTION_OPEN_DOCUMENT_TREE, uri)
//////
//////
////                        intent.setType("video/3gp")
////                        startActivityForResult(intent, 1)
//
////                        val dataUri = data!!.data
////                        mediaPlayer.release()
////                        mediaPlayer = MediaPlayer.create(this, dataUri)
//                    }
//                    Activity.RESULT_CANCELED -> {
//                        Log.wtf("getImageResult", resultCode.toString())
//                    }
//                }
//            }
//        }
//    }
}
