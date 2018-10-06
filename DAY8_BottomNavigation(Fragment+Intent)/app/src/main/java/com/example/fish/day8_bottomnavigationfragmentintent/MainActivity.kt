package com.example.fish.day8_bottomnavigationfragmentintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val intent = intent()
    //    lateinit var fragment_1 : Fragment
    lateinit var manager: FragmentManager
    lateinit var transaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = supportFragmentManager
        showFragment(fragment_0, "fragment_0")
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    val fragment_0 = Fragment_0()
    val fragment_2 = Fragment_2()
    val fragment_1 = Fragment_1()

    val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.main -> {
                    showFragment(fragment_0, "fragment_0")
                    hideFragment(fragment_1, fragment_2)
                    return true
                }

                R.id.chat -> {
                    showFragment(fragment_1, "fragment_1")
                    hideFragment(fragment_0, fragment_2)
                    return true
                }

                R.id.profile -> {
                    showFragment(fragment_2, "fragment_2")
                    hideFragment(fragment_0, fragment_1)
                    return true
                }
            }
            return false
        }
    }

    fun showFragment(frag: Fragment, tag: String) {
        transaction = manager.beginTransaction()
        if (manager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.center, frag, tag).show(frag).commit()
        } else {
            transaction.show(frag).commit()
        }
    }

    fun hideFragment(hfrag_1: Fragment, hfrag_2: Fragment) {
        if (manager.findFragmentByTag(hfrag_1.tag) != null) transaction.hide(hfrag_1)
        if (manager.findFragmentByTag(hfrag_2.tag) != null) transaction.hide(hfrag_2)
    }
}


