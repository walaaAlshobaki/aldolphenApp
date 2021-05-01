package com.adolphinpos.adolphinpos.Map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.adolphinpos.adolphinpos.helper.Common
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.*

interface CallBack {

    fun SUCCESS(jsonObject: String)
    fun ERROR(msg: String)


}
interface DirectionDelegate {

    fun didGetDirectionSuccess(response: Direction, isDraw: Boolean)
    fun didGetDirectionFail(msg: String)
    fun didDirectionEmpty()


    fun didGetGeocoderSuccess(
        adress: Address,
        strAddress: String,
        loc: LatLng
    )

    fun didGetGeocoderFail(msg: String)
    fun didGetGeocoderSearching()


}




class mapPresenter(
    val mContext: Context,
    val common: Common

) {
    var isDriverFound = false
    var delegate: DirectionDelegate? = null








    fun getAddressFromLocation(latitude: Double, longitude: Double) {



        delegate!!.didGetGeocoderSearching()
        val geocoder = Geocoder(mContext, Locale.ENGLISH)
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

                    strAddress = StringBuilder()

                    for (r in 0..fetchedAddress.getMaxAddressLineIndex()) {


                        strAddress.append(fetchedAddress.getAddressLine(i)).append("\n")


                        Log.d("getAddressFromLocation","IDLE ${fetchedAddress.getAddressLine(r)}")
                    }

//
//                    if (strAddress.toString().contains("Unnamed Road,", ignoreCase = true) || strAddress.toString().contains("Unnamed Road،,", ignoreCase = true)) {
//
//
//                    }else{
                    delegate!!.didGetGeocoderSuccess(
                        fetchedAddress,
                        strAddress.toString(),
                        LatLng(latitude, longitude)

                    )

                    break

//                    }


                }


            } else {

                delegate!!.didGetGeocoderSearching()

            }
        } catch (e: IOException) {
            e.printStackTrace()

            delegate!!.didGetGeocoderFail("Could not get address..!")
        }
    }



    fun getLocaliyFromLocation(latitude: Double, longitude: Double, callBack: CallBack) {


        val geocoder = Geocoder(mContext, Locale.ENGLISH)
        try {


            val addresses = geocoder.getFromLocation(latitude, longitude, 50)

            if (addresses.size > 0) {

                var strAddress = StringBuilder()



                for (i in 0 until addresses.size) {

                    val fetchedAddress = addresses.get(i)


                    val address = addresses[i].getAddressLine(0)
                    val city1 = addresses[0].getAddressLine(1)


                    val city = addresses[i].locality // amman
                    val adminArea = addresses[i].adminArea
                    val zip = addresses[i].postalCode
                    val country = addresses[i].countryName // jordan

                    strAddress = StringBuilder()

                    if (city == null) {

                        if(adminArea != null ){

                            val cit=adminArea.split(" ")[0]

                            callBack.SUCCESS(
                                cit
                            )

                            break

                        }

                        continue
                    }

//
//                    if (strAddress.toString().contains("Unnamed Road,", ignoreCase = true) || strAddress.toString().contains("Unnamed Road،,", ignoreCase = true)) {
//
//
//                    }else{
                    callBack.SUCCESS(
                        city
                    )

                    break

//                    }


                }


            } else {

                callBack.ERROR("error")

            }
        } catch (e: IOException) {
            e.printStackTrace()

            callBack.ERROR("error")
        }
    }









}


