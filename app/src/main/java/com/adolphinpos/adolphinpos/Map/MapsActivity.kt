package com.adolphinpos.adolphinpos.Map

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.content_home.*
import java.io.IOException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (600000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 600000 /* 2 sec */

    private var latitude = 0.0
    private var longitude = 0.0
    private var selectlatitude = 0.0
    private var selectlongitude = 0.0

    private lateinit var mGoogleMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment


        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // on below line we are getting the
                // location name from search view.
                val location = searchView!!.query.toString()

                // below line is to create a list of address
                // where we will store the list of all address.
                var addressList: List<Address>? = null

                // checking if the entered location is null or not.
                if (location != null || location == "") {
                    // on below line we are creating and initializing a geo coder.
                    val geocoder = Geocoder(this@MapsActivity)
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.

                        addressList = geocoder.getFromLocationName(location, 6)
                        if (!addressList.isNullOrEmpty()){
                            val address: Address = addressList!![0]
                            selectlatitude=address.latitude
                            selectlongitude=address.longitude
                            Log.d("DDDDDDDDDDDDDDDDDDDD",selectlatitude.toString())
                            Log.d("DDDDDDDDDDDDDDDDDDDD",selectlongitude.toString())
                            // on below line we are creating a variable for our location
                            // where we will add our locations latitude and longitude.
                            val latLng = LatLng(address.latitude, address.longitude)

                            // on below line we are adding marker to that position.
                            mGoogleMap.addMarker(MarkerOptions().position(latLng).title(location))

                            // below line is to animate camera to that position.
                            var cameraPosition: CameraPosition = CameraPosition.Builder()
                                .target(latLng)
                                .zoom(20f)
                                .bearing(0f)
                                .tilt(1f)
                                .build()


                            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null)
                        }else{
                            DesignerToast.Custom(this@MapsActivity,"No result", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                        DesignerToast.Custom(this@MapsActivity,"No result", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                            R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)

                    }
                    // on below line we are getting the location
                    // from our list a first position.

                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        mapFragment.getMapAsync(this)

        currentPosition.setOnClickListener {

            getCurrentLocation()
        }
        done.setOnClickListener {

            if (selectlatitude != 0.0){
                RxBus.publish(MessageEvent(100, LatLng(selectlatitude, selectlongitude)))
                onBackPressed()
            }else{
                DesignerToast.Custom(this@MapsActivity,"No selected location", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
            }


        }
        close.setOnClickListener {
            finish()
        }

    }



    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap

        if (mGoogleMap != null) {
//            mGoogleMap!!.addMarker(
//                MarkerOptions().position(LatLng(latitude, longitude)).title("Current Location")
//            )
        }

    }

    // 3.
    protected fun startLocationUpdates() {
        // initialize location request object
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = UPDATE_INTERVAL
            setFastestInterval(FASTEST_INTERVAL)
        }

        // initialize location setting request builder object
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // initialize location service object
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        // call register location listener
        registerLocationListner()
    }

    private fun registerLocationListner() {
        // initialize location callback object
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChanged(locationResult!!.lastLocation)
            }
        }
        // 4. add permission if android version is greater then 23
        if(Build.VERSION.SDK_INT >= 23 && checkPermission()) {
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(
                mLocationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }
    }

    fun getCurrentLocation(duration: Int = 1000) {


        val current = LatLng(latitude, longitude)





        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(current))
        var cameraPosition: CameraPosition = CameraPosition.Builder()
            .target(current)
            .zoom(15f)
            .bearing(0f)
            .tilt(1f)
            .build()


        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null)


    }
    //
    private fun onLocationChanged(location: Location) {
        // create message for toast with updated latitude and longitudefa
        var msg = "Updated Location: " + location.latitude  + " , " +location.longitude
        latitude=location.latitude
        longitude=location.longitude

        // show toast message with updated location
        //Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        val location = LatLng(location.latitude, location.longitude)
//        mGoogleMap!!.clear()
        mGoogleMap.addMarker(MarkerOptions().position(location).title("Current Location"))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
//        var cameraPosition: CameraPosition = CameraPosition.Builder()
//            .target(location)
//            .zoom(15f)
//            .bearing(0f)
//            .tilt(1f)
//            .build();
//
//
//        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null);
    }

    private fun checkPermission() : Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            requestPermissions()
            return false
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"),
            1
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if (permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION ) {
                registerLocationListner()
            }
        }
    }
}
