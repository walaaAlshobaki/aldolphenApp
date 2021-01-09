package com.adolphinpos.adolphinpos.helper

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.login.LoginActivity

interface SessionLoginCallBack{

    fun didLoginSuccess()
    fun didLoginFail()


}


public class SessionManager(context: Context) : SQLiteOpenHelper(context, SessionManager.DATABASE_NAME, null, SessionManager.DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 5
        private val DATABASE_NAME = "loginAppDataBaseManhal"
    }

    private val TABLE_NAME = "loginAppManhal"
    val trueWord = "true"
    private val IS_LOGIN = "IsLoggedIn"



    var KEY_ID = "id"
    var key_firstName = "firstName"
    var key_lastName = "lastName"
    var key_country = "country"

    var key_userid = "userid"
    var key_phoneNumber = "phoneNumber"

    var key_email = "email"

    var key_IS_LOGIN = "IS_LOGIN"

    var key_AUTH = "auth"




    var context: Context? = null

    init {
        this.context = context

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_APPS_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                key_firstName + " TEXT," +
                key_lastName + " TEXT," +
                key_country + " TEXT," +
                key_userid + " TEXT," +
                key_phoneNumber + " TEXT," +
                key_email + " TEXT," +
                key_AUTH + " TEXT" +
                ")"

        db!!.execSQL(CREATE_APPS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // you can implement here migration process
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


    fun createLoginSession(userConfig:UserConfig ) {
        // Storing login value as TRUE
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null);
        val values = ContentValues()
        values.put(key_firstName, userConfig.firstName)
        values.put(key_lastName, userConfig.lastName)
        values.put(key_country, userConfig.country)

        values.put(key_userid, userConfig.userid)
        values.put(key_phoneNumber, userConfig.phoneNumber)
        values.put(key_email, userConfig.email)
        values.put(key_AUTH, userConfig.auth_token)




        // Inserting new row into blogs table
        db.insert(TABLE_NAME, null, values)
        db.close() // Closing database connection

    }

//    fun savelanguage(
//        lang: String
//    ) {
//        Log.w("savelang :", lang)
//        val db = this.writableDatabase
//        val strSQL = "UPDATE " + TABLE_NAME + " SET langUI = " + "'" + common.langUI + "'"
//        db!!.execSQL(strSQL)
//        Log.w("savelang done :", lang)
//    }


    fun checkLogin(result:SessionLoginCallBack) {

        // Check login status
        val inetnt: Intent
        val db = this.writableDatabase
        val countQuery = "SELECT  * FROM " + TABLE_NAME
        val cursor = db.rawQuery(countQuery, null)
        var check: Boolean = false

        if (cursor.moveToFirst()) {
            check = true
//            inetnt = Intent(context, language::class.java)
            result.didLoginSuccess()
        } else {
            check = false
            result.didLoginFail()
//            inetnt = Intent(context, login::class.java)
        }
        cursor.close()
        db.close()

//        inetnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        // Add new Flag to start new Activity
//        inetnt.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//
//        // Staring Login Activity
//        context!!.startActivity(inetnt)
//        val activity = context!! as Activity
//        activity.finish()
    }

    /**
     * Get stored session data
     */
    fun getUserDetails(): UserConfig {


        val user: UserConfig = UserConfig()
        val db = this.readableDatabase
        var selectQuery = ""





        selectQuery = "SELECT  " +
                KEY_ID + "," +
                key_firstName + "," +
                key_lastName + "," +
                key_country + "," +

                key_userid + "," +
                key_phoneNumber + "," +
                key_email + "," +
                key_AUTH +

                " FROM " + TABLE_NAME

        val cursor = db.rawQuery(selectQuery, null)
        try {
            cursor.let {
                if (cursor.moveToFirst()) {
                    do {



                        if (cursor != null && cursor.getString(0) != null) {
                            user.userid = cursor.getString(0)
                        }

                        if (cursor != null && cursor.getString(1) != null) {
                            user.firstName = cursor.getString(1)
                        }

                        if (cursor != null && cursor.getString(2) != null) {
                            user.lastName = cursor.getString(2)
                        }
                        if (cursor != null && cursor.getString(3) != null) {
                            user.country = cursor.getString(3)
                        }
                        if (cursor != null && cursor.getString(4) != null) {
                            user.phoneNumber = cursor.getString(4)
                        }
                        if (cursor != null && cursor.getString(5) != null) {
                            user.email = cursor.getString(5)
                        }
                        if (cursor != null && cursor.getString(6) != null) {
                            user.auth_token = cursor.getString(6)
                        }











                    } while (cursor.moveToNext())

                    cursor.close()
                    db.close()
                    // return bloglist if there is blogs in database

                }

            }

        } catch (ex: Exception) {

        }
        // return user
        return user
    }


    /**
     * Clear session details
     */
    fun logoutUser() {

        try {

            val db = this.writableDatabase
            db.delete(TABLE_NAME, null, null);

            userConfig=UserConfig()
//            common.session!!.createLoginSession(userConfig);

            val i = Intent(context, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(i)

            //------------facebook Logout--------------

            //------------twitter log out--------------

            //------------google log out--------------



            val activity = context!! as Activity
            activity.finish()

        } catch (ex: Exception) {

        }



    }


    /**
     * Quick check for login
     */
    // Get Login State
    fun isLoggedIn(): Boolean {
        // Check login status
        val inetnt: Intent
        val db = this.writableDatabase
        val countQuery = "SELECT  * FROM " + TABLE_NAME
        val cursor = db.rawQuery(countQuery, null)
        var check: Boolean = false

        if (cursor.moveToFirst()) {
            check = true

        } else {
            check = false

        }
        cursor.close()
        db.close()



        return check
    }




    fun getLanguage(): String {


        var _lang = "en"
        try {
            val db = this.writableDatabase
            val countQuery = "SELECT  * FROM " + TABLE_NAME
            val cursor = db.rawQuery(countQuery, null)


            cursor.let {
                cursor.moveToFirst()
                if (cursor != null && cursor.getString(8) != null) {
                    _lang = cursor.getString(8)
                }
            }
            cursor.close()
            db.close()
            Log.w("load language done :", _lang)
            return _lang
        } catch (ex: Exception) {
            Log.w("load language error :", ex.toString())
            return _lang
        }


    }





//    fun getAccountType(): String {
//
//        try {
//            var _accountType = "normal"
//
//            val db = this.writableDatabase
//            val countQuery = "SELECT  * FROM " + TABLE_NAME
//            val cursor = db.rawQuery(countQuery, null)
//
//
//            cursor.let {
//                cursor.moveToFirst()
//                if (cursor != null && cursor.getString(9) != null) {
//                    _accountType = cursor.getString(9)
//                }
//            }
//            cursor.close()
//            db.close()
//
//
//            return _accountType
//        } catch (ex: Exception) {
//            common.session!!.createLoginSession(
//                UserConfig.getString("uname"),
//                UserConfig.getString("subscribe"),
//                UserConfig.getString("country"),
//                UserConfig.getString("fullname"),
//                UserConfig.getString("userid"),
//                UserConfig.getString("avatar"),
//                UserConfig.getString("gender"),
//                UserConfig.getString("langUI"),
//                UserConfig.getString("email"),
//                "skip"
//            );
//            return "skip"
//        }
//
//
//    }
}




fun ClearCookies(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    } else {
        val cookieSyncMngr = CookieSyncManager.createInstance(context)
        cookieSyncMngr.startSync()
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookie()
        cookieManager.removeSessionCookie()
        cookieSyncMngr.stopSync()
        cookieSyncMngr.sync()
    }
}