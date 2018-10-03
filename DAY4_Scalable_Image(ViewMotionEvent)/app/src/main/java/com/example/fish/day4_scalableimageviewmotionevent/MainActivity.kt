package com.example.fish.day4_scalableimageviewmotionevent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.fish.day4_scalableimageviewmotionevent.MainActivity.Companion.MODE_MOVE
import com.example.fish.day4_scalableimageviewmotionevent.MainActivity.Companion.MODE_NONE
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sqrt


class MainActivity : AppCompatActivity() {

    companion object {
        val MODE_NONE = 0
        val MODE_MOVE = 1
        val MODE_ZOOM = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveImage.setOnTouchListener(mOnTouchListener)
    }

    var imgWidth = 500
    var imgHight = 500
    var xToSubtract = 0f
    var yToSubtract = 0f
    var mode = 0
    var originDistance = 0.00
    var midX = 0f
    var midY = 0f
    var originX = 0f
    var originY = 0f
    var originW = 0
    var originH = 0
    var offsetY = 0f
    var location_x = 0f
    var location_y = 0f
    var dx = 0f
    var dy = 0f
    var stayX = 0f
    var adjustX = 0f
    var adjustY = 0f

    val mOnTouchListener = object : View.OnTouchListener {

        override fun onTouch(view: View?, event: MotionEvent?): Boolean {

            when (event!!.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    mode = MODE_MOVE
                    down(view, event)
                }

                MotionEvent.ACTION_POINTER_DOWN -> {
                    if (event.pointerCount <= 2) {
                        dx = event.getX(1) - event.getX(0)
                        dy = event.getY(1) - event.getY(0)
                        offsetY = event.rawY - event.getY(0) - view!!.y
                        location_x = event.rawX + dx
                        location_y = event.rawY + dy - offsetY

                        if (view!!.x <= location_x &&
                                location_x <= (view.x + view.width) &&
                                view.y <= location_y &&
                                location_y <= (view.y + view.height)) {
                            mode = MODE_ZOOM
                            originDistance = distance(event)
                            originX = view!!.x
                            originY = view.y
                            originW = view!!.width
                            originH = view!!.height
                            midX = (event.getX(0) + event.getX(1)) / 2 + view.x
                            midY = (event.getY(0) + event.getY(1)) / 2 + view.y
                        } else mode = MODE_NONE
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    move(view, event)
                }

                MotionEvent.ACTION_POINTER_UP -> {
                    if (event.pointerCount <= 2) {
                        mode = MODE_NONE
//                        pointerUp(view, event)
                    }
                }

                MotionEvent.ACTION_UP -> {
                    up(view, event)
                }
            }
            return true
        }
    }

    fun down(view: View?, event: MotionEvent) {
        xToSubtract = event.rawX - view!!.x
        yToSubtract = event.rawY - view.y
    }

    fun move(view: View?, event: MotionEvent) {
        when (mode) {
            MODE_MOVE -> {
                view!!.x = event.rawX - xToSubtract
                view.y = event.rawY - yToSubtract
            }

            MODE_ZOOM -> {
                var afterDistance = distance(event)
                var mag = afterDistance / originDistance
                var l = (midX - (midX - originX) * mag - view!!.translationX).toInt()
                var t = (midY - (midY - originY) * mag - view!!.translationY).toInt()
                var w: Double
                var h: Double

                if ((originW * mag < imgWidth) or (originH * mag < imgHight)) {
                    w = imgWidth.toDouble()
                    h = imgHight.toDouble()
                } else {
                    w = originW * mag
                    h = originH * mag
                }
                view!!.layout(l, t, (l + w).toInt(), (t + h).toInt())
            }

            MODE_NONE -> {
            }
        }
    }

//    fun pointerUp(view: View?, event: MotionEvent) {
//        xToSubtract = event.rawX + event.getX(1) - event.getX(0) - view!!.x
//            yToSubtract = event.rawY + event.getY(1) - event.getY(0) - view.y
//    }

    fun up(view: View?, event: MotionEvent) {
        when (mode) {
            MODE_MOVE -> {
                val dx = 200
                val dy_t = 200
                val dy_b = 500
                val x = if (view!!.rootView.width - view.x <= dx) (view!!.rootView.width - dx).toFloat()
                else if (view.x + view.width <= dx) (dx - view.width).toFloat()
                else event.rawX - xToSubtract
                val y = if (view!!.rootView.height - view.y <= dy_b) (view!!.rootView.height - dy_b).toFloat()
                else if (view.y + view.height <= dy_t) (dy_t - view.height).toFloat()
                else event.rawY - yToSubtract
                view.x = x
                view.y = y
            }

            MODE_NONE -> {
            }
        }
    }

    fun distance(event: MotionEvent): Double {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x + y * y).toDouble())
    }
}
