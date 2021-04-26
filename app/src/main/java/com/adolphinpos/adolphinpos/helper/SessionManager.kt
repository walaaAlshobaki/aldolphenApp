package com.manhal.lms.app.Helper

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
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.login.LoginActivity
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel


interface SessionLoginCallBack{

    fun didLoginSuccess()
    fun didLoginFail()


}


public class SessionManager(context: Context) : SQLiteOpenHelper(context, SessionManager.DATABASE_NAME, null, SessionManager.DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 5
        private val DATABASE_NAME = "loginAppDataBase"
    }

    private val TABLE_NAME = "loginApp"
    val trueWord = "true"
    private val IS_LOGIN = "IsLoggedIn"



    var KEY_ID = "id"
    var key_firstname = "firstName"
    var key_lastname = "lastName"
    var key_semail = "email"
    var key_isVerfied = "isVerfied"
    var key_phoneNumber = "phoneNumber"
    var key_token = "key_token"
    var companyId = "companyId"
    var key_userid = "userId"
    var age = "age"
    var branchId = "branchId"
    var profilePicturePath = "profilePicturePath"
    var key_IS_LOGIN = "IS_LOGIN"

    var context: Context? = null

    init {
        this.context = context

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_APPS_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                key_firstname + " TEXT," +
                key_lastname + " TEXT," +
                key_semail + " TEXT," +
                key_isVerfied + " TEXT," +
                key_userid + " TEXT," +
                key_phoneNumber + " TEXT," +
                key_token + " TEXT," +
                companyId + " TEXT," +
                branchId + " TEXT," +
                profilePicturePath + " TEXT," +
                age + " TEXT," +
                key_IS_LOGIN + " TEXT" +
                ")"


        db!!.execSQL(CREATE_APPS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // you can implement here migration process
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }



    fun createLoginSession(userConfig:UserInfoModel ) {
        Log.d("createLoginSession",userConfig.toString())
        // Storing login value as TRUE
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null);
        val values = ContentValues()
        values.put(key_firstname, userConfig.firstName)
        values.put(key_lastname, userConfig.lastName)
        values.put(key_semail, userConfig.email)
        values.put(key_isVerfied, userConfig.isVerfied)
        values.put(key_phoneNumber, userConfig.phoneNumber)
        values.put(key_token, userConfig.token)
        values.put(key_userid, userConfig.userId)
        values.put(companyId, userConfig.companyId)

        if (userConfig.profilePicturePath==null){
            values.put(profilePicturePath, "")

        }else{
            values.put(profilePicturePath, userConfig.profilePicturePath.toString())

        }
                values.put(branchId, userConfig.branchId.toString())
           values.put(age, userConfig.age.toString())

        values.put(key_IS_LOGIN, "false")


        // Inserting new row into blogs table
        db.insert(TABLE_NAME, null, values)
        // Closing database connection

    }

//    fun savelanguage(
//        lang: String
//    ) {
//        Log.w("savelang :", lang)
//        val db = this.writableDatabase
////        val strSQL = "UPDATE " + TABLE_NAME + " SET langUI = " + "'" + common.langUI + "'"
//        db!!.execSQL(strSQL)
//        Log.w("savelang done :", lang)
//    }


    fun checkLogin(result:SessionLoginCallBack) {

        // Check login status
        val inetnt: Intent
        val db = this.writableDatabase
        val countQuery = "SELECT  * FROM " + TABLE_NAME
        val cursor = db.rawQuery(countQuery, null)
        Log.d("checkLogin",countQuery)
        var check: Boolean = false

        if (cursor.moveToLast()) {
            Log.d("checkLogin",cursor.getString(7))

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
    fun getUserDetails(): UserInfoModel {


        val user: UserInfoModel = UserInfoModel()
        val db = this.readableDatabase
        var selectQuery = ""





        selectQuery = "SELECT  " +
                KEY_ID + "," +
                key_firstname + "," +
                key_lastname + "," +
                key_semail + "," +
                key_isVerfied + "," +
                key_userid + "," +
                key_phoneNumber + "," +
                key_token + "," +
                companyId + "," +

                profilePicturePath + "," +
                                branchId + "," +
                age + "," +

                key_IS_LOGIN +



                " FROM " + TABLE_NAME

        val cursor = db.rawQuery(selectQuery, null)
        try {
            cursor.let {
                if (cursor.moveToLast()) {
                    do {



                        if (cursor != null && cursor.getString(1) != null) {
                            user.firstName = cursor.getString(1)
                        }

                        if (cursor != null && cursor.getString(2) != null) {
                            user.lastName = cursor.getString(2)
                            Log.d("WWWWWWWWWWWWWWWWWWWww",user.lastName)

                        }

                        if (cursor != null && cursor.getString(3) != null) {
                            user.email = cursor.getString(3)
                        }
                        if (cursor != null && cursor.getString(4) != null) {
                            user.isVerfied = cursor.getString(4).toBoolean()
                        }
                        if (cursor != null && cursor.getString(5) != null) {
                            user.userId = cursor.getString(5).toInt()
                        }
                        if (cursor != null && cursor.getString(6) != null) {
                            user.phoneNumber = cursor.getString(6)
                        }
                        if (cursor != null && cursor.getString(7) != null) {
                            user.token = cursor.getString(7)
                        }
                        if (cursor != null && cursor.getString(8) != null) {
                            user.companyId = cursor.getString(8)
                        }
//
                        if (cursor != null && cursor.getString(9) != null) {
                            user.profilePicturePath = cursor.getString(9)
                        }
                        if (cursor != null && cursor.getString(10) != null) {
                            user.branchId = cursor.getInt(10)
                        }
                        if (cursor != null && cursor.getString(11) != null) {
                            user.age = cursor.getInt(11)
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

            userInfo=UserInfoModel()
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
