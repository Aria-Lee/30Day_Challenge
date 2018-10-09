package com.example.fish.day7_mylocationgooglemap

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var thisView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thisView = window.decorView
        button.setOnClickListener {
            permission()
        }
    }

    fun permission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        } else startIntent()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 0) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startIntent()
            } else {
                val snackBar = Snackbar.make(thisView, "無定位功能無法執行程序", Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction("OK", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        snackBar.dismiss()
                    }
                }).setActionTextColor(Color.LTGRAY)
                        .show()
//                Toast.makeText(this, "無定位功能無法執行程序", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun startIntent() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}
