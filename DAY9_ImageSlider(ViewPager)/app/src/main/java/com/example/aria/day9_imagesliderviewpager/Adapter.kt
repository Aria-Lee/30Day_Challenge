package com.example.aria.day9_imagesliderviewpager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.imageview.view.*
import java.util.zip.Inflater

class Adapter(val context: Context, val list: List<Int>) : PagerAdapter() {
    override fun isViewFromObject(v: View, any: Any): Boolean {
        Log.wtf("aaaaa", "view:"+v.tag.toString())
        Log.wtf("aaaaa", "any:"+(any as View).tag.toString())

        return v == any
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.imageview, container, false)
        view.tag = position+1
        Log.wtf("aaaaa", view.tag.toString())
        Glide.with(context).load(list[position]).into(view.imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}