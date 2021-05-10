package com.adolphinpos.adolphinpos.ServerManager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Build
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

var serverManagerGet: ServerMamagerGet = ServerMamagerGet()



class ApiModelGet(msg: String, var result: Int, dataPayload: String) {

    var message: String = msg
    var data: String = dataPayload


}


class AuthModelGET {
    var auth_token: String = ""
}


interface callBackApiGet {

    fun SUCCESS(jsonObject: String)
    fun ERROR(msg: String)
    fun FAILER(msg: String)
    fun JSON(jsonObject: JSONObject, api: ApiModelGet?)
    fun EMPTY(result: Boolean)
    fun NO_INTERNET()
    fun ERROR_MSG(msg: String)
    fun NoMore(msg: String)


}


interface CallBackGet {

    fun SUCCESS(jsonObject: String)
    fun ERROR(msg: String)


}


enum class HttpMethodGet {
    GET,
    POST, DELETE,
    PUT
}


class ServerMamagerGet {

    val urlAPIs = UrlAPIs.instance




    var apiRoot = "http://161.97.164.114:8080/"

    var apiFolders: String = "api/"
    var apiDomainRoot = ""

    var root = ""
    companion object {
        val instance = ServerManager()
    }


    init {

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }


        apiDomainRoot = apiRoot + apiFolders


    }
    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }



    fun callApi(
        context: Context,
        requestMethod: HttpMethod,
        url: String,
        postData: MutableMap<String, Any>,
        callBack: callBackApiGet
    ) {


        val checkInternet = isNetworkConnected(context)

        if (!checkInternet) {

            val activity = context as Activity

            Toast.makeText(context, context.resources.getString(R.string.no_internet_msg), Toast.LENGTH_LONG).show()



            callBack.NO_INTERNET()

            return

        }


        val urlLinkApi: String = generateUrl(url, postData)
        Log.d("TTTTTTTTTTTTTTTTTTTT",urlLinkApi)

        val postJsonData: JSONObject = generatePostData(url, postData)


        val call = @SuppressLint("StaticFieldLeak")
        object : CallAPIOperationGet(context, requestMethod, postJsonData, url) {


            override fun onProgressUpdate(vararg values: String?) {
                var jsonObject: JSONObject = JSONObject()


                jsonObject = JSONObject(values[0])
                try {

                    Log.d("tag", "responseCode ajax: " + values[0].toString())
                    //var jsonArray: JSONArray = JSONArray(values[0].toString())


                    val dataPayload = jsonObject.getString("data")
                    if (!dataPayload.isNullOrEmpty()) {


                        try {

                            //if json object

                            val o = JSONObject(dataPayload)
                            callBack.SUCCESS(dataPayload)


                        } catch (e: JSONException) {


                            //if json array


                            val o = JSONArray(dataPayload)

                            callBack.SUCCESS(values[0].toString())


                        }


                        val msg = jsonObject.getString("success")

                        callBack.ERROR(msg)

                    } else {
                        val dataPayload = jsonObject.getString("success")
                        callBack.ERROR(dataPayload)

                    }


                    val messae = jsonObject.getString("message")
                    callBack.ERROR(
                            messae
                    )

                }  catch (ex: Exception) {
                    try {
                        val messae = jsonObject.getString("message")
                        Log.d("message :", messae)
                        callBack.ERROR(
                                messae
                        )
                        callBack.FAILER(messae)

                        Log.d("IS_LOGIN :", ex.localizedMessage)


                    }catch (ex: Exception) {
                        callBack.FAILER(ex.localizedMessage)
                    }catch (ex: Exception) {
//                            callBack.FAILER(ex.localizedMessage)
                    }
//                    callBack.FAILER(ex.localizedMessage)

                    Log.d("IS_LOGIN :", ex.localizedMessage)
//                        callBack.FAILER(ex.localizedMessage)

//                    callBack.FAILER(ex.localizedMessage)


                }
            }
        }





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urlLinkApi.replace("\\s+".toRegex(), "%20"))

            Log.d("** < api calling > ***", "${url} " + urlLinkApi.replace("\\s+".toRegex(), "%20"))
        } else {
            Log.d("** < api calling > ***", "${url} " + urlLinkApi.replace("\\s+".toRegex(), "%20"))

            call.execute(urlLinkApi.replace("\\s+".toRegex(), "%20"))
        }





    }


//    @SuppressLint("LogNotTimber")
//    fun callApiUpload(
//        context: Context,
//        requestMethod: HttpMethod,
//        url: String,
//        postData: MutableMap<String, Any>,
//        fileData: Bitmap,
//        callBack: callBackApiGet
//    ) {
//
//
//        val checkInternet = isNetworkConnected(context)
//
//        if (!checkInternet) {
//
//            val activity = context as Activity
//
//            Toast.makeText(context, context.resources.getString(R.string.no_internet_msg), Toast.LENGTH_LONG).show()
//
//
//
//            callBack.NO_INTERNET()
//
//            return
//
//        }
//
//
//        val urlLinkApi: String = generateUrl(url, postData)
//
//
//
//        val call = @SuppressLint("StaticFieldLeak")
//        object : UploadOperation(context, requestMethod, fileData, url) {
//
//
//
//            override fun onProgressUpdate(vararg values: String?) {
//                try {
//
//                    Log.d("tag", "responseCode ajax: *" + values[0].toString())
//                    //var jsonArray: JSONArray = JSONArray(values[0].toString())
//                    var jsonObject: JSONObject = JSONObject()
//
//
//                    jsonObject = JSONObject(values[0].toString())
//
//
//                    val result = jsonObject.getString("result")
//
//                    if (result == "1") {
//
//
//                        val dataPayload = jsonObject.getString("data")
//
//
//                        try {
//
//                            //if json object
//
//                            val o = JSONObject(dataPayload)
//                            callBack.SUCCESS(dataPayload)
//
//
//                        } catch (e: JSONException) {
//
//
//                            //if json array
//
//
//                            val o = JSONArray(dataPayload)
//
//                            callBack.SUCCESS(values[0].toString())
//
//
//                        }
//
//
//                    } else if (result == "2") {
//
//                        callBack.NoMore("No more")
//
//
//                    } else {
//
//                        val msg = jsonObject.getString("message")
//                        val msgData = jsonObject.getString("data")
//
//                        Log.d("** < api calling > ***", url + "* message: " + msgData)
//
//                        callBack.ERROR(msg)
//
//                    }
//
//
//                } catch (ex: Exception) {
//                    callBack.FAILER(ex.toString())
//                    Log.d("IS_LOGIN :", ex.toString())
//
//                }
//            }
//        }
//
//
//
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//
//            call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urlLinkApi.replace("\\s+".toRegex(), "%20"))
//
//            Log.d("** < api calling > ***", "${url}* " + urlLinkApi.replace("\\s+".toRegex(), "%20"))
//        } else {
//            Log.d("** < api calling > ***", "${url}* " + urlLinkApi.replace("\\s+".toRegex(), "%20"))
//
//            call.execute(urlLinkApi.replace("\\s+".toRegex(), "%20"))
//        }
//
//
//    }



        private fun generateUrl(url: String, postData: MutableMap<String, Any>): String {

            var parametersString = ""

            var callUrl: String = ""


            for ((key, value) in postData) {
                parametersString += "$key=$value&"

            }

            //remove last charater &
            if (parametersString.endsWith("&")) {
                parametersString = parametersString.substring(0, parametersString.count() - 1)
            }


//        if (url == UrlAPIs.instance.LmsDomain) {
//
//            callUrl = "https://www.manhal.com/${common.langUI}/api/app/get-lms-domain/?"
//        } else {
            callUrl = apiRoot+ apiFolders+ url
//
//        }


            callUrl += parametersString


            return callUrl

        }


    private fun generatePostData(url: String, postData: MutableMap<String, Any>): JSONObject {


        val jsonParam = JSONObject()


        for ((key, value) in postData) {

            jsonParam.put(key, value)
        }

        return jsonParam
    }

//
//    fun refreshFireBaseKey(tokenID: String, context: Context, callBackAuth: CallBack) {
//
//        val paramsDictionary = mutableMapOf<String, Any>()
//
//        paramsDictionary["lang"] = common.langUI
//        paramsDictionary["uuid"] = tokenID
//
//
//        serverManager.callApi(
//            context,
//            HttpMethod.POST,
//            UrlAPIs.instance.fireBaseUpdateToken,
//            paramsDictionary,
//            object : callBackApi {
//
//
//                override fun SUCCESS(jsonObject: String) {
//
//
//                    callBackAuth.SUCCESS(tokenID)
//
//                }
//
//                override fun ERROR(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun FAILER(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {
//
//                }
//
//                override fun EMPTY(result: Boolean) {
//                    callBackAuth.ERROR("EMPTY")
//                }
//
//                override fun NO_INTERNET() {
//                    callBackAuth.ERROR(context.resources.getString(R.string.no_internet_msg))
//                }
//
//                override fun ERROR_MSG(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun NoMore(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//
//            })
//
//    }
//
//
//    fun logout(context: Context, callBackAuth: CallBack) {
//
//        val paramsDictionary = mutableMapOf<String, Any>()
//
//        paramsDictionary["lang"] = common.langUI
//
//
//
//        serverManager.callApi(
//            context,
//            HttpMethod.POST,
//            UrlAPIs.instance.logout,
//            paramsDictionary,
//            object : callBackApi {
//
//
//                override fun SUCCESS(jsonObject: String) {
//
//
//                    callBackAuth.SUCCESS("")
//
//                }
//
//                override fun ERROR(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun FAILER(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {
//
//                }
//
//                override fun EMPTY(result: Boolean) {
//                    callBackAuth.ERROR("EMPTY")
//                }
//
//                override fun NO_INTERNET() {
//                    callBackAuth.ERROR(context.resources.getString(R.string.no_internet_msg))
//                }
//
//                override fun ERROR_MSG(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun NoMore(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//
//            })
//
//    }



//
//    fun refreshAuthKey(context: Context, callBackAuth: CallBack) {
//
//
//        val paramsDictionary = mutableMapOf<String, Any>()
//
//        paramsDictionary["lang"] = common.langUI
//
//
//        serverManager.callApi(
//            context,
//            HttpMethod.GET,
//            UrlAPIs.instance.refreshToken,
//            paramsDictionary,
//            object : callBackApi {
//
//
//                override fun SUCCESS(jsonObject: String) {
//
//
//                    val responseJson = common.parserJson.fromJson(jsonObject.toString(), AuthModel::class.java)
//
//
//                    callBackAuth.SUCCESS(responseJson.auth_token)
//
//                }
//
//                override fun ERROR(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun FAILER(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {
//
//                }
//
//                override fun EMPTY(result: Boolean) {
//                    callBackAuth.ERROR("EMPTY")
//                }
//
//                override fun NO_INTERNET() {
//                    callBackAuth.ERROR(context.resources.getString(R.string.no_internet_msg))
//                }
//
//                override fun ERROR_MSG(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//                override fun NoMore(msg: String) {
//                    callBackAuth.ERROR(msg)
//                }
//
//
//            })
//
//
//    }


}

abstract class CallAPIOperationGet(
    context: Context,
    requestMethod: HttpMethod,

    postData: JSONObject,
    operationUrl: String
) : AsyncTask<String, String, String>() {

    var context: Context
    var requestMethod: String

    private val postData: JSONObject

    var operationUrl: String = ""


    init {

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }



        this.context = context

        this.requestMethod = requestMethod.toString()

        this.postData = postData

        this.operationUrl = operationUrl

    }


    @SuppressLint("LogNotTimber")
    override fun onPreExecute() {
        Log.d(
            "** < api calling > ***",
            "$operationUrl *************************************************************************************************"
        )

        Log.d(
            "** < api calling > ***",
            "$operationUrl *** START CALLING API *****|||${requestMethod}|||*******|||${operationUrl}|||"
        )

        Log.d(
            "** < api calling > ***",
            "$operationUrl *************************************************************************************************"
        )

    }


    @SuppressLint("LogNotTimber")
    override fun doInBackground(vararg p0: String?): String {
        try {
            val url = URL(p0[0])


            val urlConnection = url.openConnection() as HttpURLConnection


            if (urlConnection.requestMethod == "POST") {
                val os = DataOutputStream(urlConnection.outputStream)
                os.writeBytes(postData.toString())

                os.flush()
                os.close()


                urlConnection.doOutput = true
                urlConnection.doInput = true

            }

            urlConnection.requestMethod = requestMethod

            urlConnection.setRequestProperty("Content-Type", "application/json")
//            urlConnection.setRequestProperty("Accept", "application/json")
            urlConnection.setRequestProperty("Authorization", "Bearer " + userInfo.token)


            urlConnection.useCaches = false

            urlConnection.connect()


            val responseCode = urlConnection.responseCode

            if (urlConnection != null) {


                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val jsonData = convertStreamToJson(urlConnection.inputStream)

                    Log.w("** < api calling > ***", "${operationUrl} responseCode: " + jsonData)

                    publishProgress(jsonData)

                } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                    Log.w("** < api calling > ***", responseCode.toString())


                    val jsonData = convertStreamToJson(urlConnection!!.errorStream)

                    Log.w("** < api calling > ***", "${url} responseCode: " + jsonData)

                    Log.d("HttpURLConnection", "Response: $jsonData")
                    publishProgress(jsonData)

                }


//                Log.d("** < api calling > ***", "$operationUrl auth_token :" + (userConfig.auth_token))
                Log.d("** < api calling > ***", "$operationUrl CODE :" + (urlConnection.responseCode.toString()))
                Log.d("** < api calling > ***", "$operationUrl MSG :" + urlConnection.responseMessage)

                urlConnection.disconnect()
            }

        } catch (ex: Exception) {
            Log.d("** < api calling > ***", "$operationUrl " + ex.toString())
            Log.d(
                "** < api calling > ***",
                "$operationUrl *** END CALLING API ***************************************************************************"
            )
            Log.d(
                "** < api calling > ***",
                "$operationUrl ***********************************************************************************************"
            )


        }
        return ""
    }


    override fun onCancelled(result: String?) {


    }


    override fun onProgressUpdate(vararg values: String?) {


    }


    @SuppressLint("LogNotTimber")
    override fun onPostExecute(result: String?) {

        Log.d(
            "** < api calling > ***",
            "$operationUrl *** END CALLING API ***************************************************************************"
        )
        Log.d(
            "** < api calling > ***",
            "$operationUrl ***********************************************************************************************"
        )


    }


    private fun convertStreamToJson(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        var line: String
        var allLine: String = ""
        try {


            do {

                line = bufferReader.readLine()
                if (line != null) {
                    allLine += line


                }

            } while (line != null)

            bufferReader.close()
        } catch (ex: Exception) {


//        Log.w("buffer error", ex.toString())
        }
        return allLine
    }




}
