package com.adolphinpos.adolphinpos.helper

import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException

class JWTUtils {

    companion object {


        val instance = JWTUtils()
        @Throws(Exception::class)
        open fun decoded(JWTEncoded: String): String? {

            try {
//                split = JWTEncoded.split("\\.").toTypedArray()
                Log.d("JWT_DECODED", "Header: " + getJson(JWTEncoded))
//                Log.d("JWT_DECODED", "Body: " + getJson(split.get(1)))
//                Log.d("JWT_DECODED", "Signiture: " + getJson(split.get(2)))
            } catch (e: UnsupportedEncodingException) {
                //Error
            }
            return getJson(JWTEncoded)
        }

        @Throws(UnsupportedEncodingException::class)
        private fun getJson(strEncoded: String): String {
            val decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE)
            return String(decodedBytes)
        }

    }



}