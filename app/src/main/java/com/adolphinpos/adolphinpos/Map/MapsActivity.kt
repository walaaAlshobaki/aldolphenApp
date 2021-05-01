package com.adolphinpos.adolphinpos.Map

import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.adolphinpos.adolphinpos.R
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.*
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.content_home.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    private var latitude = 0.0
    private var longitude = 0.0

    private lateinit var mGoogleMap: GoogleMap
    var placeAutoComplete: PlaceAutocompleteFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        placeAutoComplete =
            fragmentManager.findFragmentById(R.id.place_autocomplete) as PlaceAutocompleteFragment

        placeAutoComplete!!.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.d("Maps", "Place selected: " + place.name)
            }

            override fun onError(status: Status) {
                Log.d("Maps", "An error occurred: $status")
            }
        })
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        close.setOnClickListener {

            finish()
        }

    }


    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap;

        if (mGoogleMap != null) {
            mGoogleMap!!.addMarker(
                MarkerOptions().position(LatLng(latitude, longitude)).title("Current Location")
            )
        }

    }

    // 3.
    protected fun startLocationUpdates() {
        // initialize location request object
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            setInterval(UPDATE_INTERVAL)
            setFastestInterval(FASTEST_INTERVAL)
        }

        // initialize location setting request builder object
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // initialize location service object
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient!!.checkLocationSettings(locationSettingsRequest)

        // call register location listener
        registerLocationListner()
    }

    private fun registerLocationListner() {
        // initialize location callback object
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChanged(locationResult!!.getLastLocation())
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

    //
    private fun onLocationChanged(location: Location) {
        // create message for toast with updated latitude and longitudefa
        var msg = "Updated Location: " + location.latitude  + " , " +location.longitude

        // show toast message with updated location
        //Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        val location = LatLng(location.latitude, location.longitude)
        mGoogleMap!!.clear()
        mGoogleMap!!.addMarker(MarkerOptions().position(location).title("Current Location"))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        var cameraPosition: CameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(15f)
            .bearing(0f)
            .tilt(1f)
            .build();


        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null);
    }

    private fun checkPermission() : Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            return true;
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
