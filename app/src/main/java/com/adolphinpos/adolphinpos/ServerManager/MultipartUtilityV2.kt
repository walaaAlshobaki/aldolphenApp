package com.adolphinpos.adolphinpos.ServerManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.adolphinpos.adolphinpos.CompanyServiceBranches.AvatarParser
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoDelegate
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.userProfile.UpdateDataModel
import com.vdx.designertoast.DesignerToast
import org.json.JSONObject
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;


interface UserInfoUpdateDelegate{


    fun didAddSuccess(response: UpdateDataModel)
    fun didAddFail(msg:String)
}
class MultipartUtilityV2 {
    private var httpConn: HttpURLConnection? = null
    private var request: DataOutputStream? = null
    private var context: Context? = null
    private val boundary = "*****"
    private val crlf = "\r\n"
    private val twoHyphens = "--"
    var delegate: UserInfoUpdateDelegate? = null

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     *
     * @param requestURL
     * @throws IOException
     */
    @Throws(IOException::class)
    fun MultipartUtilityV2(requestURL: String?,context: Context,method: String) {
        this.context=context
        // creates a unique boundary based on time stamp
        val url = URL(requestURL)
        httpConn = url.openConnection() as HttpURLConnection
        httpConn!!.setUseCaches(false)
        httpConn!!.setDoOutput(true) // indicates POST method
        httpConn!!.setDoInput(true)
        httpConn!!.setRequestMethod(method)
        httpConn!!.setRequestProperty("Authorization", "Bearer " + userInfo.token)
        httpConn!!.setRequestProperty("Connection", "Keep-Alive")
        httpConn!!.setRequestProperty("Cache-Control", "no-cache")
        httpConn!!.setRequestProperty(
            "Content-Type", "multipart/form-data;boundary=" + boundary
        )
        request = DataOutputStream(httpConn!!.getOutputStream())
    }

    /**
     * Adds a form field to the request
     *
     * @param name  field name
     * @param value field value
     */
    @Throws(IOException::class)
    fun addFormField(name: String, value: String) {
        request!!.writeBytes(twoHyphens + boundary + crlf)
        request!!.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + crlf)
        request!!.writeBytes("Content-Type: text/plain; charset=UTF-8" + crlf)
        request!!.writeBytes(crlf)
        request!!.writeBytes(value + crlf)
        request!!.flush()
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName  name attribute in <input type="file" name="..."></input>
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(IOException::class)
    fun addFilePart(fieldName: String, uploadFile: File) {
        val fileName: String = uploadFile.getName()
        request!!.writeBytes(twoHyphens + boundary + crlf)
        request!!.writeBytes(
            "Content-Disposition: form-data; name=\"" +
                    fieldName + "\";filename=\"" +
                    fileName + "\"" + crlf
        )
        request!!.writeBytes(crlf)
        val bytes: ByteArray = Files.readAllBytes(uploadFile.toPath())
        request!!.write(bytes)
    }

    /**
     * Completes the request and receives response from the server.
     *
     * @return a list of Strings as response in case the server returned
     * status OK, otherwise an exception is thrown.
     * @throws IOException
     */
    @Throws(IOException::class)
    fun finish(): String? {
        var response: String = ""
        request!!.writeBytes(crlf)
        request!!.writeBytes(
            (twoHyphens + boundary +
                    twoHyphens + crlf)
        )
        request!!.flush()
        request!!.close()

        // checks server's status code first
        val status: Int = httpConn!!.getResponseCode()
        if (status == HttpURLConnection.HTTP_OK) {
            val responseStream: InputStream = BufferedInputStream(httpConn!!.getInputStream())
            val responseStreamReader = BufferedReader(InputStreamReader(responseStream))
            var line: String? = ""
            val stringBuilder = StringBuilder()
            while ((responseStreamReader.readLine().also { line = it }) != null) {
                stringBuilder.append(line).append("\n")
            }
            responseStreamReader.close()
            response = stringBuilder.toString()

            val jsonObject = JSONObject(response)
            val dataPayload = jsonObject.getString("message")
            Log.d("OOOOOOOOOOO",dataPayload)
//            val responseJson = common.parserJson.fromJson(response, UpdateDataModel::class.java)
//            delegate!!.didAddSuccess(responseJson)
            httpConn!!.disconnect()
        } else {


//
//            throw IOException("Server returned non-OK status: $status")
            response = status.toString()
//            DesignerToast.Custom(context,"Server returned non-OK status: $status", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
//                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
            Log.d("OOOOOOOOOOO","Server returned non-OK status: $status")
//           delegate!!.didAddFail("Server returned non-OK status: $status")
        }
        return response
    }
}