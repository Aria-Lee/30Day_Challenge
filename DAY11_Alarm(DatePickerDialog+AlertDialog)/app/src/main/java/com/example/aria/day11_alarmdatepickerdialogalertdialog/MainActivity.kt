package com.example.aria.day11_alarmdatepickerdialogalertdialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date_edit.setOnClickListener(listener)
        time_edit.setOnClickListener(listener)
        button.setOnClickListener(listener)
    }

    val calender = Calendar.getInstance()

    val listener = View.OnClickListener {
        when (it) {
            date_edit -> {
                datePicker()
            }

            time_edit -> {
                timePicker()
            }

            button -> {
                Dialog()
            }
        }
    }

    fun datePicker() {
        DatePickerDialog(this,
                dateListener,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)).show()
    }

    fun timePicker() {
        TimePickerDialog(this,
                timeListener,
                calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE),
                true
        ).show()
    }

    val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        calender.set(year, month, day)
        format("yyyy / MM / dd", date_edit)
    }

//    val dateListener = object : DatePickerDialog.OnDateSetListener {
//        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
//                               dayOfMonth: Int) {
//            calender.set(Calendar.YEAR, year)
//            calender.set(Calendar.MONTH, monthOfYear)
//            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            format("yyyy / MM / dd", date_edit)
//        }
//    }

    val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, min->
        calender.set(hour, min)
        format("HH : mm", time_edit)
    }

    fun format(format: String, view: View) {
        val time = SimpleDateFormat(format, Locale.TAIWAN)
        (view as EditText).setText(time.format(calender.time))
    }

    fun Dialog(){
        AlertDialog.Builder(this)
                .setTitle("Your Time")
                .setMessage("${date_edit.text}   ${time_edit.text}")
                .setNegativeButton("OK"){ dialog, which ->
                    dialog.cancel()
                }.create().show()
    }

}
