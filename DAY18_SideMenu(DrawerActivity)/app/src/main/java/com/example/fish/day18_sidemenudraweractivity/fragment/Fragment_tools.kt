package com.example.fish.day18_sidemenudraweractivity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fish.day18_sidemenudraweractivity.R
import kotlinx.android.synthetic.main.fragment_content.view.*

class Fragment_tools  : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false )
        view.frag_image.setImageResource(R.drawable.ic_menu_manage)
        return view
    }
}