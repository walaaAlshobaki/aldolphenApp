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
import com.adolphinpos.adolphinpos.registeration.country.CountryDelegate
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.adolphinpos.adolphinpos.registeration.country.CountryPresenter
import com.adolphinpos.adolphinpos.steps.Step2Activity
import com.ahmadrosid.svgloader.SvgLoader
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_company_branch.*
import kotlinx.android.synthetic.main.activity_company_branch.countryCodePicker
import kotlinx.android.synthetic.main.activity_company_branch.countryCodePicker2
import kotlinx.android.synthetic.main.activity_company_branch.currentPosition



import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class CompanyBranchActivity : AppCompatActivity() , OnMapReadyCallback,CityDelegate,BranchDelegate,
    CountryDelegate {
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (600000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 600000 /* 2 sec */
    var rawArray: ArrayList<Int> = ArrayList()
    var cityModel: ArrayList<CityModel.Data> = ArrayList()
    private var latitude = 0.0
    private var longitude = 0.0
    private var cityId = 0
    private lateinit var marker: Marker
    var cityPresenter: CityPresenter? = null
    var BranchPresnter: BranchPresnter? = null
    var countryModel: CountryModel.Data? =null
    var mCountryPresenter: CountryPresenter? = null
    var phoneCode=""
    var latLng: LatLng? =null
    private lateinit var mGoogleMap: GoogleMap
    val MY_PERMISSIONS_REQUEST_LOCATION = 99
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_branch)
        close.setOnClickListener {
            finish()
        }
        cityPresenter = CityPresenter(this)
        cityPresenter!!.delegate = this
       BranchPresnter = BranchPresnter(this)
       BranchPresnter!!.delegate = this
        mCountryPresenter = CountryPresenter(this)
        mCountryPresenter!!.delegate = this
        mCountryPresenter!!.getCountry()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        }

        phoneCode=countryCodePicker.selectedCountryCode
        countryCodePicker.setOnCountryChangeListener(CountryCodePicker.OnCountryChangeListener { selectedCountry ->
            Log.d(

                "Updated", selectedCountry.name + "CODE" + selectedCountry.phoneCode,

                )
            phoneCode = selectedCountry.phoneCode
            countryCodePicker2.setCountryForNameCode(selectedCountry.phoneCode)
            mCountryPresenter!!.getCountry()
        })
        countryCodePicker2.setOnCountryChangeListener(CountryCodePicker.OnCountryChangeListener { selectedCountry ->
            Log.d(

                "Updated", selectedCountry.name + "CODE" + selectedCountry.phoneCode,

                )
            phoneCode = selectedCountry.phoneCode
            countryCodePicker.setCountryForNameCode(selectedCountry.phoneCode)
            mCountryPresenter!!.getCountry()
        })

        Log.d(

            "Updated" ,countryCodePicker.selectedCountryName+"CODE  "+countryCodePicker.selectedCountryCode,

            )

        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 1) {
                countryModel = it.message as CountryModel.Data
//                    mPresenter!!.scheduleTap(day!!.format(formatted))
//                country.text= countryModel!!.name
//                SvgLoader.pluck()
//                    .with(this as Activity?)
//                    .setPlaceHolder(R.drawable.ca, R.drawable.ca)
//                    .load(countryModel!!.flag, flag)
//                SvgLoader.pluck()
//                    .with(this as Activity?)
//                    .setPlaceHolder(R.drawable.ca, R.drawable.ca)
//                    .load(countryModel!!.flag, flagphone)
//                callCode.text= "(+"+countryModel!!.callingCodes+")"
//
//                cityPresenter!!.getCity(countryModel!!.id)


            }else if (it.action== 100){

                Log.d("LLLLLLLLLLLLLLLLLLLL",it.message.toString())

                mGoogleMap!!.clear()
//                marker.remove()
//                marker.setVisible(false)

                latLng = it.message as LatLng
                latitude=latLng!!.latitude
                longitude=latLng!!.longitude


                mGoogleMap!!.addMarker(MarkerOptions().position( it.message).title("Current Location"))
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng( it.message))
                var cameraPosition: CameraPosition = CameraPosition.Builder()
                    .target( it.message)
                    .zoom(15f)
                    .bearing(0f)
                    .tilt(1f)
                    .build();


                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null);
//

            }

        }
        currentPosition.setOnClickListener {

            getCurrentLocation()
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

//        country.setOnClickListener{
//
//            val i = Intent(this, CountryActivity::class.java)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(i)
//
//        }
//        code.setOnClickListener{
//
//            val i = Intent(this, CountryActivity::class.java)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(i)
//
//        }
//        flagcode.setOnClickListener{
//            val i = Intent(this, CountryActivity::class.java)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(i)
//        }

        loginBtn.setOnClickListener {


            if (BranchName.text.isNullOrEmpty()||phoneNum.text.isNullOrEmpty()|| Branches.text.isNullOrEmpty()){
                DesignerToast.Custom(this,"All filed is required",Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                    R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219);
            }else{
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
                                zip = 0

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
            mGoogleMap!!.clear()

//            mGoogleMap!!.addMarker(
//                MarkerOptions().position(LatLng(latitude, longitude)).title("Current Location")
//
//            )


            RxBus.listen(MessageEvent::class.java).subscribe {
                if (it.action == 100) {
                    mGoogleMap!!.clear()
//                    marker.remove()
//                    marker.setVisible(false)
                    latLng = it.message as LatLng
                    latitude=latLng!!.latitude
                    longitude=latLng!!.longitude


                    mGoogleMap!!.addMarker(MarkerOptions().position(latLng).title("Current Location"))
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                    var cameraPosition: CameraPosition = CameraPosition.Builder()
                        .target(latLng)
                        .zoom(15f)
                        .bearing(0f)
                        .tilt(1f)
                        .build();


                    mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null);
//
////
                }

            }
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
    protected fun startLocationUpdates() {
        // initialize location request object
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            setPriority(LocationRequest.PRIORITY_LOW_POWER)
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

        var msg = "Updated Location: " + location.latitude  + " , " +location.longitude

        val location = LatLng(location.latitude, location.longitude)

        latitude=location.latitude
        longitude=location.longitude
        mGoogleMap!!.addMarker(MarkerOptions().position(location).title("Current Location"))
//        val markerOptions = MarkerOptions().position(location)
//
//        marker = mGoogleMap.addMarker(markerOptions)
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
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

    override fun didGetCountrySuccess(response: CountryModel) {
        Log.d("SSSSSSSSSSSSSSSS",userInfo.contryId.toString())
        val iterator = (response.data.indices).iterator()

        if (iterator.hasNext()) {
            iterator.next()
        }

// do something with the rest of elements
        iterator.forEach {

            if (response.data[it].callingCodes == phoneCode) {
                countryModel=response.data[it]
                Log.d("SSSSSSSSSSSSSSSS",response.data[it].toString())
                cityPresenter!!.getCity(response.data[it].id)
            }




        }
    }

    override fun didGetCountryFail(msg: String) {

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






