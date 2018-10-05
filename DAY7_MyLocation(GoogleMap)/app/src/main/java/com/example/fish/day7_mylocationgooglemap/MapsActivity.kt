package com.example.fish.day7_mylocationgooglemap

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var thisView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        thisView = window.decorView
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager()
        mMap.isMyLocationEnabled = true
    }

    var oriLocation : Location? = null

    fun locationManager(){
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        var isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        var isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!(isGPSEnabled || isNetworkEnabled))
            Snackbar.make(thisView, "目前無開啟任何定位功能", Snackbar.LENGTH_LONG).show()
        else
        try {
            if (isGPSEnabled ) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        0L, 0f, locationListener)
                oriLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }
            else if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        0L, 0f, locationListener)
                oriLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
        if(oriLocation != null) {
            drawMarker()
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(oriLocation!!.latitude, oriLocation!!.longitude), 12.0f))
        }
    }

    val locationListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            if(oriLocation == null) {
                oriLocation = location
                drawMarker()
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12.0f))

        }
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }

    }

    fun drawMarker(){
        var lntLng = LatLng(oriLocation!!.latitude, oriLocation!!.longitude)
        mMap.addMarker(MarkerOptions().position(lntLng).title("Current Position"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            locationManager()
        }
        return true
    }
}
