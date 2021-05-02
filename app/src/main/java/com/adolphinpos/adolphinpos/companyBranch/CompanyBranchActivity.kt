package com.adolphinpos.adolphinpos.companyBranch

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.AddCurrencyTypeModel
import com.adolphinpos.adolphinpos.Map.MapsActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.authorized_employees.AuthorizedEmployeesActivity
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.plan.PlanActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.adolphinpos.adolphinpos.steps.Step2Activity
import com.ahmadrosid.svgloader.SvgLoader
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_company_branch.*
import kotlinx.android.synthetic.main.activity_company_branch.country
import kotlinx.android.synthetic.main.activity_company_branch.flag
import kotlinx.android.synthetic.main.activity_company_branch.flagphone
import kotlinx.android.synthetic.main.activity_company_branch.loginBtn
import kotlinx.android.synthetic.main.activity_company_branch.phoneNum
import kotlinx.android.synthetic.main.activity_register.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class CompanyBranchActivity : AppCompatActivity() , OnMapReadyCallback,CityDelegate,BranchDelegate {
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
    var rawArray: ArrayList<Int> = ArrayList()
    var cityModel: ArrayList<CityModel.Data> = ArrayList()
    private var latitude = 0.0
    private var longitude = 0.0
    private var cityId = 0
    var cityPresenter: CityPresenter? = null
    var BranchPresnter: BranchPresnter? = null
    var countryModel: CountryModel.Data? =null
    private lateinit var mGoogleMap: GoogleMap
    val MY_PERMISSIONS_REQUEST_LOCATION = 99
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_branch)

        cityPresenter = CityPresenter(this)
        cityPresenter!!.delegate = this
       BranchPresnter = BranchPresnter(this)
       BranchPresnter!!.delegate = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        }

        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 1) {
                countryModel = it.message as CountryModel.Data
//                    mPresenter!!.scheduleTap(day!!.format(formatted))
                country.text= countryModel!!.name
                SvgLoader.pluck()
                    .with(this as Activity?)
                    .setPlaceHolder(R.drawable.ca, R.drawable.ca)
                    .load(countryModel!!.flag, flag)
                SvgLoader.pluck()
                    .with(this as Activity?)
                    .setPlaceHolder(R.drawable.ca, R.drawable.ca)
                    .load(countryModel!!.flag, flagphone)


                cityPresenter!!.getCity(countryModel!!.id)
            }
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        rawArray.add(R.raw.uber_style)

        search.setOnClickListener {

            val i = Intent(this, MapsActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }

        Branches.setOnClickListener {
            getPopup(Branches,cityModel)

        }

        country.setOnClickListener{

            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        flag.setOnClickListener{

            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        flagphone.setOnClickListener{
            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }

        loginBtn.setOnClickListener {
            for (i in cityModel.indices) {
                if (Branches.text.toString()==cityModel[i].name){
                    Log.d("Branches",cityModel[i].id.toString())
                    cityId=cityModel[i].id!!
                }

            }
            var address = ""
            var city = ""
            var adminArea = ""
            var zip = 0
            var country = ""
            var url = "https://www.google.com/maps/search/?api=1&query=${latitude},${longitude}"
            val geocoder = Geocoder(this, Locale.ENGLISH)
            try {


                val addresses = geocoder.getFromLocation(latitude, longitude, 5)

                if (addresses.size > 0) {

                    var strAddress = StringBuilder()



                    for (i in 0..addresses.size) {

                        val fetchedAddress = addresses.get(i)


                         address = addresses[i].getAddressLine(0)
                         city = addresses[i].locality
                         adminArea = addresses[i].adminArea
                        if (addresses[i].postalCode==null){
                            zip=0
                        }else{
                            zip = addresses[i].postalCode as Int

                        }
                        if (addresses[i].countryName==null){
                            country = countryModel!!.name
                        }else{
                            country = addresses[i].countryName
                        }




                        strAddress = StringBuilder()

                        for (r in 0..fetchedAddress.getMaxAddressLineIndex()) {


                            strAddress.append(fetchedAddress.getAddressLine(i)).append("\n")


                            Log.d("getAddressFromLocation", "IDLE ${addresses[i]}")
                        }

//
//                    if (strAddress.toString().contains("Unnamed Road,", ignoreCase = true) || strAddress.toString().contains("Unnamed Road،,", ignoreCase = true)) {
//
//
//                    }else{


                        break

//                    }


                    }


                } else {



                }
            } catch (e: IOException) {
                e.printStackTrace()

                Log.d("didGetGeocoderSearching", "Could not get address..!")
            }

            BranchPresnter!!.addPaymentMethood(BranchName.text.toString()
            ,zip,"","",longitude.toString(),latitude.toString()
                ,url,"",country,"","",
                address,address,cityId,countryModel!!.id,"00"+countryModel!!.callingCodes+phoneNum.text.toString())

        }
    }

    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    private fun getPopup(textView: TextView, arrayList: ArrayList<CityModel.Data>) {
        val popupMenu = PopupMenu(this, textView)
        for (i in 0 until arrayList.size) {
            popupMenu.menu.add(i, Menu.FIRST, i, arrayList[i].name)
        }
        popupMenu.setOnMenuItemClickListener { item ->
            textView.text = item.title
            false
        }
        popupMenu.show()
    }
    fun getAddressFromLocation(latitude: Double, longitude: Double) {




        val geocoder = Geocoder(this, Locale.ENGLISH)
        try {


            val addresses = geocoder.getFromLocation(latitude, longitude, 5)

            if (addresses.size > 0) {

                var strAddress = StringBuilder()



                for (i in 0..addresses.size) {

                    val fetchedAddress = addresses.get(i)


                    val address = addresses[i].getAddressLine(0)
                    val city = addresses[i].locality
                    val adminArea = addresses[i].adminArea
                    val zip = addresses[i].postalCode
                    val country = addresses[i].countryName
                    val url = addresses[i].url


                    strAddress = StringBuilder()

                    for (r in 0..fetchedAddress.getMaxAddressLineIndex()) {


                        strAddress.append(fetchedAddress.getAddressLine(i)).append("\n")


                        Log.d("getAddressFromLocation", "IDLE ${addresses[i]}")
                    }

//
//                    if (strAddress.toString().contains("Unnamed Road,", ignoreCase = true) || strAddress.toString().contains("Unnamed Road،,", ignoreCase = true)) {
//
//
//                    }else{


                    break

//                    }


                }


            } else {



            }
        } catch (e: IOException) {
            e.printStackTrace()

            Log.d("didGetGeocoderSearching", "Could not get address..!")
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap;

        if (mGoogleMap != null) {
            mGoogleMap!!.addMarker(
                MarkerOptions().position(LatLng(latitude, longitude)).title("Current Location")
            )
        }

        mGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mGoogleMap.isTrafficEnabled = false
        mGoogleMap.isIndoorEnabled = false
        mGoogleMap.isBuildingsEnabled = false
        mGoogleMap.uiSettings.isCompassEnabled = false
        mGoogleMap.uiSettings.isMapToolbarEnabled = false

        mGoogleMap.uiSettings.isZoomControlsEnabled = false
        mGoogleMap.uiSettings.isMyLocationButtonEnabled = false
        // mMap.setInfoWindowAdapter(CustomInfoWindow(this))



        mGoogleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this, rawArray[0] // UBER STYLE
            )
        );

    }


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
        if(Build.VERSION.SDK_INT >= 23 && checkLocationPermission()) {
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(
                mLocationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }
    }
    private fun onLocationChanged(location: Location) {
        // create message for toast with updated latitude and longitudefa
        var msg = "Updated Location: " + location.latitude  + " , " +location.longitude

        // show toast message with updated location
        //Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        val location = LatLng(location.latitude, location.longitude)
        latitude=location.latitude
        longitude=location.longitude
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
    fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {

                    }
                } else {
                    Toast.makeText(
                        this, "permission denied",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return
            }
        }
    }

    override fun didGetCitySuccess(response: CityModel) {
        cityModel.addAll(response.data)
    }

    override fun didGetCityFail(msg: String) {

    }

    override fun didEmpty() {

    }

    override fun didAddBranchSuccess(response: AddBranchModel) {

        common.branchId=response.data
        DesignerToast.Custom(this,"successfully Added",
            Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        val i = Intent(this, Step2Activity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun didAddBranchFail(msg: String) {
        if (msg=="Added"||msg=="true"){


            DesignerToast.Custom(this,"successfully Added",
                Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
            val i = Intent(this, Step2Activity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }else{

            DesignerToast.Custom(this,msg, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        }
    }
}






