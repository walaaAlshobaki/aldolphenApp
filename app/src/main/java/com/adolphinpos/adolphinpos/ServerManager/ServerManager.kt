package com.adolphinpos.adolphinpos.ServerManager

import android.R.attr.bitmap
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
import androidx.annotation.RequiresApi
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


var serverManager: ServerManager = ServerManager()

class UrlAPIs {

    companion object {
        val instance = UrlAPIs()
    }

    val login = "http://161.97.164.114:8080/api/User"
    val Company = "http://161.97.164.114:8080/api/Company"
    val CompanyInfo = "Company?"
    val Category = "Product/Category?"

    val email = "http://161.97.164.114:8080/api/User/RestPassword/Email"
    val code = "http://161.97.164.114:8080/api/User/Activation/Code"
    val newBranch = "http://161.97.164.114:8080/api/Company/Service/Branch"
    val newCompanyBranch = "http://161.97.164.114:8080/api/Company/Branch"
    val updateInfo = "http://161.97.164.114:8080/api/User/Info"
    val Country = "Country?"
    val userInfo = "User/Info?"
    val Poliicy = "User/Poliicy?"
    val Hall = "Company/Hall?"
    val Tables = "Company/Tables?"
    val PoliicyPermissions = "User/Poliicy/Permissions?"
    val Permissions = "User/Permissions?"
    val Service = "Service?"
    val ServiceTypes = "Service/Types?"
    val Validate = "User/Activation/Validate?"
    val emailValidate = "http://161.97.164.114:8080/api/User/RestPassword/Validate"
    val reset = "http://161.97.164.114:8080/api/User/RestPassword"
    val addHalls = "http://161.97.164.114:8080/api/Company/Hall"
    val addTable = "http://161.97.164.114:8080/api/Company/Tables"

    val Invite = "http://161.97.164.114:8080/api/User/Invite"
    val addPolice = "http://161.97.164.114:8080/api/User/Poliicy"
    val addCurrency= "http://161.97.164.114:8080/api/Company/Currency"
    val addPaymentMethood = "http://161.97.164.114:8080/api/Company/PaymentMethod"
    val Users = "Company/Users?"
    val deleteUsers = "User?"
    val deletePoliicy = "User/Poliicy?"
    val Branches = "Company/Service/Branches?"
    val restPhoneNumber = "http://161.97.164.114:8080/api/User/RestPhoneNumber"
    val Currency = "Setup/Currency?"
    val PaymentMethood = "Setup/PaymentMethood?"
    val City = "Setup/Country/City?"
    val ServiceType = "Service/Types?"

}

class ApiModel(msg: String, var result: Int, dataPayload: String) {

    var message: String = msg
    var data: String = dataPayload


}


class AuthModel {
    var auth_token: String = ""
}

interface callBackApi {

    fun SUCCESS(auth_token: String)
    fun ERROR(msg: String)
    fun FAILER(msg: String)
    fun JSON(jsonObject: JSONObject, api: ApiModel?)
    fun EMPTY(result: Boolean)
    fun NO_INTERNET()
    fun ERROR_MSG(msg: String)
    fun NoMore(msg: String)


}

interface CallBack {

    fun SUCCESS(jsonObject: String)
    fun ERROR(msg: String)


}

enum class HttpMethod {
    GET,
    POST, DELETE,
    PUT
}

class ServerManager {
    val urlAPIs = UrlAPIs.instance

    var apiRoot = "http://161.97.164.114:8080/"

    var apiFolders: String = "api/"
    var apiDomainRoot = ""

    var root = ""

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
        postData: JSONObject,
        callBack: callBackApi
    ) {


        val checkInternet = isNetworkConnected(context)

        if (!checkInternet) {

            val activity = context as Activity

            Toast.makeText(
                context,
                context.resources.getString(R.string.no_internet_msg),
                Toast.LENGTH_LONG
            ).show()



            callBack.NO_INTERNET()

            return

        }


//        val urlLinkApi: String = generateUrl(url, postData)
        Log.d("TTTTTTTTTTTTTTTTTTTT", url)

//        val postJsonData: JSONObject = generatePostData(url, postData)
//        executePost(url, postData.toString())

        val call = @SuppressLint("StaticFieldLeak")
        object : CallAPIOperation(context, requestMethod, postData, url) {


            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressUpdate(vararg values: String?) {
                var jsonObject: JSONObject = JSONObject()


                jsonObject = JSONObject(values[0])
                try {

//                    Log.d("tag", "responseCode ajax: " + values[0].toString())
                    //var jsonArray: JSONArray = JSONArray(values[0].toString())



                    val dataPayload = jsonObject.getString("data")



                    //if json object
                    if (!dataPayload.isNullOrEmpty()) {
                        try {
//                            val jwt = JWT.decode(dataPayload)
////                            val test =jwt.getClaim("NameIdentifier")
////                            println("Decoded: $decodedString")
//                            val claims = jwt.claims //Key is the Claim name
//
//                            val claim = claims["nameid"]
//                            Log.d("jsonObject", "responseCode ajax: " + claim!!.asString())


                            callBack.SUCCESS(dataPayload)


                        } catch (e: JSONException) {



                        }

                        val msg = jsonObject.getString("success")

                        callBack.ERROR(msg)
                    }
                    else {
                        val dataPayload = jsonObject.getString("success")
                        callBack.ERROR(dataPayload)

                    }


                    val messae = jsonObject.getString("message")
                    callBack.ERROR(
                        messae
                    )


                }
                catch (ex: Exception) {
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

            call.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR, url.replace(
                    "\\s+".toRegex(),
                    "%20"
                )
            )

            Log.d("** < api calling > ***", "${url} " + url.replace("\\s+".toRegex(), "%20"))
        } else {
            Log.d("** < api calling > ***", "${url} " + url.replace("\\s+".toRegex(), "%20"))

            call.execute(url.replace("\\s+".toRegex(), "%20"))
        }


    }





    @SuppressLint("LogNotTimber")
    fun callApiUpload(
        context: Context,
        requestMethod: HttpMethod,
        url: String,
        postData: MutableMap<String, Any>,
        fileData: Bitmap,
        callBack: callBackApi
    ) {


        val checkInternet = isNetworkConnected(context)

        if (!checkInternet) {

            val activity = context as Activity

            Toast.makeText(
                context,
                context.resources.getString(R.string.no_internet_msg),
                Toast.LENGTH_LONG
            ).show()



            callBack.NO_INTERNET()

            return

        }


        val urlLinkApi: String = generateUrl(url, postData)


        val call = @SuppressLint("StaticFieldLeak")
        object : UploadOperation(context, requestMethod, fileData, postData, url) {


            override fun onProgressUpdate(vararg values: String?) {
                try {

                    Log.d("tag", "responseCode ajax: *" + values[0])
                    //var jsonArray: JSONArray = JSONArray(values[0].toString())
                    var jsonObject: JSONObject = JSONObject()


                    jsonObject = JSONObject(values[0])


                    val result = jsonObject.getString("result")

                    if (result == "1") {


                        val dataPayload = jsonObject.getString("data")


                        try {

                            //if json object

//                            val o = JSONObject(dataPayload)
                            callBack.SUCCESS(dataPayload)


                        } catch (e: JSONException) {


                            //if json array


//                            val o = JSONArray(dataPayload)

                            callBack.SUCCESS(values[0].toString())


                        }


                    } else if (result == "2") {

                        callBack.NoMore("No more")


                    } else {

                        val msg = jsonObject.getString("message")
                        val msgData = jsonObject.getString("data")

                        Log.d("** < api calling > ***", url + "* message: " + msgData)

                        callBack.ERROR(msg)

                    }


                } catch (ex: Exception) {
                    callBack.FAILER(ex.toString())
                    Log.d("IS_LOGIN :", ex.toString())

                }
            }
        }





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            call.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR, urlLinkApi.replace(
                    "\\s+".toRegex(),
                    "%20"
                )
            )

            Log.d(
                "** < api calling > ***",
                "${url}* " + urlLinkApi.replace("\\s+".toRegex(), "%20")
            )
        } else {
            Log.d(
                "** < api calling > ***",
                "${url}* " + urlLinkApi.replace("\\s+".toRegex(), "%20")
            )

            call.execute(urlLinkApi.replace("\\s+".toRegex(), "%20"))
        }


    }

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



        callUrl = apiRoot + apiFolders + url



        callUrl += parametersString


        return callUrl

    }


    fun executePost(targetURL: String?, requestJSON: String): String? {
        var connection: HttpURLConnection? = null
        var `is`: InputStream? = null

        return try {
            //Create connection
            val url = URL(targetURL)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection!!.setRequestProperty("Content-Type", "application/json")

            connection.useCaches = false
            connection.doOutput = true

            //Send request
            val wr = DataOutputStream(connection.outputStream)
            wr.writeBytes(requestJSON)

            wr.close()

            //Get Response
            try {
                `is` = connection.inputStream
            } catch (ioe: IOException) {
                if (connection is HttpURLConnection) {
                    val httpConn = connection
                    val statusCode = httpConn.responseCode
                    if (statusCode != 200) {
                        `is` = httpConn.errorStream
                    }
                }
            }
            val rd = BufferedReader(InputStreamReader(`is`))
            val response = StringBuilder() // or StringBuffer if Java version 5+
            var line: String?
            while (rd.readLine().also { line = it } != null) {
                response.append(line)
                response.append('\r')
            }
            rd.close()

            Log.d("WWWWWWWWWWWWWWWW", response.toString())
            response.toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        } finally {
            connection?.disconnect()
        }
    }
}

abstract class CallAPIOperation(
    context: Context,
    requestMethod: HttpMethod,
    postData: JSONObject,
    operationUrl: String
) : AsyncTask<String, String, String>() {


    var context: Context
    var requestMethod: String

    private val postData: JSONObject

    var operationUrl: String = ""

    var urlConnection: HttpURLConnection? = null

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
            "$operationUrl *** START CALLING API *****|||${requestMethod}|||*******"
        )

        Log.d(
            "** < api calling > ***",
            "$operationUrl *************************************************************************************************"
        )

    }


    @SuppressLint("LogNotTimber")
    override fun doInBackground(vararg p0: String?): String {
        try {


            val url = URL(operationUrl)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection!!.requestMethod = requestMethod
            urlConnection!!.setRequestProperty("Content-Type", "application/json; charset=utf-8 ")
            urlConnection!!.setRequestProperty("Authorization", "Bearer " + userInfo.token)
//            urlConnection!!.setRequestProperty("Content-Length", "" + postData.toString().length)
            urlConnection!!.setRequestProperty(
                "Content-Length",
                Integer.toString(postData.toString().length)
            )

            urlConnection!!.useCaches = false
            urlConnection!!.doOutput = true
            urlConnection!!.doInput = true

            if (urlConnection!!.requestMethod == "POST") {

                val wr = DataOutputStream(urlConnection!!.outputStream)
                wr.writeBytes(postData.toString())
                wr.close()
                Log.d(
                    "** < api calling > ***",
                    "${operationUrl}* MSG :" + postData.toString()
                )
            } else if (urlConnection!!.requestMethod == "PUT") {

                val out = OutputStreamWriter(
                    urlConnection!!.getOutputStream()
                )
                out.write(postData.toString())
                out.close()
//                val wr = DataOutputStream(urlConnection!!.outputStream)
//                wr.writeBytes(postData.toString())
//                wr.close()
                Log.d(
                    "** < api calling > ***",
                    "${operationUrl}* MSG :" + postData.toString()
                )
            }

            urlConnection!!.connect()
            val responseCode = urlConnection!!.responseCode


            if (urlConnection != null) {
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val jsonData = convertStreamToJson(urlConnection!!.inputStream)

                    Log.w("** < api calling > ***", "${url} responseCode: " + jsonData)

                    publishProgress(jsonData)

                } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                    Log.w("** < api calling > ***", responseCode.toString())


                    val jsonData = convertStreamToJson(urlConnection!!.errorStream)

                    Log.w("** < api calling > ***", "${url} responseCode: " + jsonData)

                    Log.d("HttpURLConnection", "Response: $jsonData")
                    publishProgress(jsonData)

                }


                Log.d(
                    "** < api calling > ***",
                    "$operationUrl auth_token :" + (userConfig.auth_token)
                )
                Log.d(
                    "** < api calling > ***",
                    "$operationUrl auth_token :" + urlConnection!!.errorStream
                )
                Log.d(
                    "** < api calling > ***",
                    "$operationUrl CODE :" + (urlConnection!!.responseCode.toString())
                )
                Log.d(
                    "** < api calling > ***",
                    "$operationUrl MSG :" + urlConnection!!.responseMessage
                )

                urlConnection!!.disconnect()
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

abstract class UploadOperation(
    context: Context,
    requestMethod: HttpMethod,
    fileData: Bitmap,
    postData: MutableMap<String, Any>,
    operationUrl: String
) : AsyncTask<String, String, String>() {


    var context: Context
    var requestMethod: String
    val fileData: Bitmap
    var operationUrl: String = ""
    var postData: MutableMap<String, Any>? = null


    init {

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }



        this.context = context

        this.requestMethod = requestMethod.toString()

        this.fileData = fileData

        this.operationUrl = operationUrl
        this.postData=postData

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


            val url = URL(operationUrl)
            val urlConnection = url.openConnection() as HttpURLConnection


//           urlConnection.setRequestProperty("Authorization", "Bearer " + userConfig.auth_token)

            urlConnection.doOutput = true
            urlConnection.useCaches = false


            val paramName = "Image"
            val fileName = "fileName.jpg"


            val crlf = "\r\n"
            val twoHyphens = "--"
            val boundary = "*****"


            urlConnection.setRequestProperty("Connection", "Keep-Alive")
            urlConnection.setRequestProperty("Cache-Control", "no-cache")
            urlConnection.setRequestProperty("Authorization", "Bearer " + userInfo.token)
            urlConnection.setRequestProperty(
                "Content-Type", "multipart/form-data;boundary=$boundary"
            )


            val pixels = ByteArray(fileData.getWidth() * fileData.getHeight())
            for (i in 0 until fileData.getWidth()) {
                for (j in 0 until fileData.getHeight()) {
                    //we're interested only in the MSB of the first byte,
                    //since the other 3 bytes are identical for B&W images
                    pixels[i + j] = ((fileData.getPixel(i, j) and 0x80 shr 7).toByte())
                }
            }

//            request.write(pixels)

            val request = DataOutputStream(
                urlConnection.outputStream
            )

            request.writeBytes(twoHyphens + boundary + crlf)
            request.writeBytes(
                "Content-Disposition: form-data; name=\"" +
                        paramName + "\";filename=\"" +
                        fileName + "\"" + crlf
            )

            request.writeBytes(crlf)


//            request.write(convertToByte(fileData))
            request.write(pixels)

            request.writeBytes(crlf)
            request.writeBytes(
                twoHyphens + boundary +
                        twoHyphens + crlf
            )

            for((key, value) in postData!!){
                request.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"")
                request.writeBytes(crlf)

                request.writeBytes(crlf);
                request.writeBytes(value.toString())
                request.writeBytes(crlf)

            }







            request.flush()
            request.close()





            urlConnection.requestMethod = "PUT"


            urlConnection.connect()


            val responseCode = urlConnection.responseCode

            if (urlConnection != null) {


                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val jsonData = convertStreamToJson(urlConnection.inputStream)

                    Log.w("** < api calling > ***", "${operationUrl}* responseCode: " + jsonData)

                    publishProgress(jsonData)

                } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST){
                    Log.w("** < api calling > ***", responseCode.toString())

                    val jsonData = convertStreamToJson(urlConnection!!.errorStream)

                    Log.w("** < api calling > ***", "${url} responseCode: " + jsonData)

                    Log.d("HttpURLConnection", "Response: $jsonData")
                    publishProgress(jsonData)
                }else{

                }


                Log.d(
                    "** < api calling > ***",
                    "${operationUrl}* auth_token :" + (userInfo.token)
                )
                Log.d(
                    "** < api calling > ***",
                    "${operationUrl}* CODE :" + (urlConnection.responseCode.toString())
                )
                Log.d(
                    "** < api calling > ***",
                    "${operationUrl}* MSG :" + urlConnection.responseMessage
                )

                urlConnection.disconnect()
            }

        } catch (ex: Exception) {
            Log.d("** < api calling > ***", "${operationUrl} " + ex.toString())
            Log.d(
                "** < api calling > ***",
                "${operationUrl} *** END CALLING API ***************************************************************************"
            )
            Log.d(
                "** < api calling > ***",
                "${operationUrl} ***********************************************************************************************"
            )


        }
        return ""
    }

    private fun convertToByte(bitmap: Bitmap): ByteArray {


        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        val image = stream.toByteArray()


        return image

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
            "${operationUrl} ***********************************************************************************************"
        )


    }


    private fun convertStreamToJson(inputStream: InputStream): String {

        val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        var line: String
        var allLine: String = ""
        try {


            do {

                line = bufferedReader.readLine()
                if (line != null) {
                    allLine += line


                }

            } while (line != null)

            bufferedReader.close()
        } catch (ex: Exception) {


//        Log.w("buffer error", ex.toString())
        }
        return allLine
    }


}