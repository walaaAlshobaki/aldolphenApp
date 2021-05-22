package com.adolphinpos.adolphinpos.ServerManager

import android.util.Log
import com.adolphinpos.adolphinpos.Splash.userInfo
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MultipartUtility(requestURL: String, charset: String) {

    private var boundary: String? = null
    private val LINE_FEED = "\r\n"
    private var httpConn: HttpURLConnection? = null
    private var charset: String? = null
    private var outputStream: OutputStream? = null
    private var writer: PrintWriter? = null

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     *
     * @param requestURL
     * @param charset
     * @throws IOException
     */
    @Throws(IOException::class)
    fun MultipartUtility(requestURL: String, charset: String?) {
        this.charset = charset

        // creates a unique boundary based on time stamp
        boundary = "===" + System.currentTimeMillis() + "==="
        val url = URL(requestURL)
        Log.e("URL", "URL : $requestURL")
        httpConn = url.openConnection() as HttpURLConnection
        httpConn!!.setUseCaches(false)
        httpConn!!.setDoOutput(true) // indicates POST method
        httpConn!!.setDoInput(true)
        httpConn!!.requestMethod="PUT"
        httpConn!!.setRequestProperty(
            "Content-Type",
            "multipart/form-data; boundary=$boundary"
        )
        httpConn!!.setRequestProperty("Authorization", "Bearer " + userInfo.token)

        httpConn!!.setRequestProperty("User-Agent", "CodeJava Agent")
        httpConn!!.setRequestProperty("Test", "Bonjour")
        outputStream = httpConn!!.getOutputStream()
        writer = PrintWriter(
            OutputStreamWriter(outputStream, charset),
            true
        )
    }

    /**
     * Adds a form field to the request
     *
     * @param name  field name
     * @param value field value
     */
    fun addFormField(name: String, value: String?) {
        writer!!.append("--$boundary").append(LINE_FEED)
        writer!!.append("Content-Disposition: form-data; name=\"$name\"")
            .append(LINE_FEED)
        writer!!.append("Content-Type: text/plain; charset=$charset").append(
            LINE_FEED
        )
        writer!!.append(LINE_FEED)
        writer!!.append(value).append(LINE_FEED)
        writer!!.flush()
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName  name attribute in <input type="file" name="..."></input>
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    @Throws(IOException::class)
    fun addFilePart(fieldName: String, uploadFile: File) {
        val fileName: String = uploadFile.getName()
        writer!!.append("--$boundary").append(LINE_FEED)
        writer!!.append(
            "Content-Disposition: form-data; name=\"" + fieldName
                    + "\"; filename=\"" + fileName + "\""
        )
            .append(LINE_FEED)
        writer!!.append(
            (
                    "Content-Type: "
                            + URLConnection.guessContentTypeFromName(fileName))
        )
            .append(LINE_FEED)
        writer!!.append("Content-Transfer-Encoding: binary").append(LINE_FEED)
        writer!!.append(LINE_FEED)
        writer!!.flush()
        val inputStream = FileInputStream(uploadFile)
        val buffer = ByteArray(4096)
        var bytesRead = -1
        while ((inputStream.read(buffer).also { bytesRead = it }) != -1) {
            outputStream!!.write(buffer, 0, bytesRead)
        }
        outputStream!!.flush()
        inputStream.close()
        writer!!.append(LINE_FEED)
        writer!!.flush()
    }

    /**
     * Adds a header field to the request.
     *
     * @param name  - name of the header field
     * @param value - value of the header field
     */
    fun addHeaderField(name: String, value: String) {
        writer!!.append("$name: $value").append(LINE_FEED)
        writer!!.flush()
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
        val response = StringBuffer()
        writer!!.append(LINE_FEED).flush()
        writer!!.append("--$boundary--").append(LINE_FEED)
        writer!!.close()

        // checks server's status code first
        val status: Int = httpConn!!.getResponseCode()
        httpConn!!.errorStream
        if (status == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    httpConn!!.getInputStream()
                )
            )
            var line: String? = null
            while ((reader.readLine().also { line = it }) != null) {
                response.append(line)
            }
            reader.close()
            httpConn!!.disconnect()
            val jsonData = convertStreamToJson(httpConn!!.errorStream)

            Log.w("** < api calling > ***", " responseCode: " + jsonData)
        } else {
            throw IOException("Server returned non-OK status: $status")

        }
        return response.toString()
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