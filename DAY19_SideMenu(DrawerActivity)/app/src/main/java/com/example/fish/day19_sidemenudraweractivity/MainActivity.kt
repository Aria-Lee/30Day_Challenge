package com.example.fish.day19_sidemenudraweractivity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.fish.day19_sidemenudraweractivity.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var manager :FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle()
        initActivity()
    }

    fun toggle(){
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun initActivity(){
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        manager = supportFragmentManager
        manager.beginTransaction().add(R.id.main, Fragment_main()).commit()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.backHome -> {
                manager.beginTransaction().replace(R.id.main, Fragment_main()).commit()
            }

            R.id.nav_camera -> {
                manager.beginTransaction().replace(R.id.main, Fragment_import()).commit()
            }
            R.id.nav_gallery -> {
                manager.beginTransaction().replace(R.id.main, Fragment_gallery()).commit()
            }
            R.id.nav_slideshow -> {
                manager.beginTransaction().replace(R.id.main, Fragment_slideShow()).commit()
            }
            R.id.nav_manage -> {
                manager.beginTransaction().replace(R.id.main, Fragment_tools()).commit()
            }
            R.id.nav_share -> {
                manager.beginTransaction().replace(R.id.main, Fragment_share()).commit()
            }
            R.id.nav_send -> {
                manager.beginTransaction().replace(R.id.main, Fragment_send()).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
