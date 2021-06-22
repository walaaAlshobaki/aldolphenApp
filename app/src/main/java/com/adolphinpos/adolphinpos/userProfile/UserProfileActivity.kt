package com.adolphinpos.adolphinpos.userProfile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.MultipartUtilityV2
import com.adolphinpos.adolphinpos.ServerManager.UserInfoUpdateDelegate
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoDelegate
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoPresenter
import com.adolphinpos.adolphinpos.productManagerHomePage.ProductManagerMainActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryDelegate
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.adolphinpos.adolphinpos.registeration.country.CountryPresenter
import com.ahmadrosid.svgloader.SvgLoader
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_user_profile.age
import kotlinx.android.synthetic.main.activity_user_profile.avatar_img
import kotlinx.android.synthetic.main.activity_user_profile.country
import kotlinx.android.synthetic.main.activity_user_profile.email
import kotlinx.android.synthetic.main.activity_user_profile.firstname
import kotlinx.android.synthetic.main.activity_user_profile.flag
import kotlinx.android.synthetic.main.activity_user_profile.flagphone
import kotlinx.android.synthetic.main.activity_user_profile.lastname
import kotlinx.android.synthetic.main.activity_user_profile.phoneNum
import kotlinx.android.synthetic.main.activity_user_profile.sign
import kotlinx.android.synthetic.main.activity_user_profile.update_layer
import kotlinx.android.synthetic.main.activity_user_profile.upload
import kotlinx.android.synthetic.main.activity_user_profile.userImage
import kotlinx.android.synthetic.main.activity_user_profile.userName
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream


class UserProfileActivity : AppCompatActivity() , UserInfoDelegate, CountryDelegate , UserInfoUpdateDelegate {
    var mPresenter: UserInfoPresenter? = null
    var countryModel: CountryModel.Data? =null
    var picturePath: String =""
    var picturePath1: Bitmap? =null
    var CounPresenter: CountryPresenter? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        mPresenter = UserInfoPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getUserInfo()
        CounPresenter = CountryPresenter(this)
        CounPresenter!!.delegate = this
        CounPresenter!!.getCountry()
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }
        Log.d("WWWWWWWWWWWWW", userInfo.token)
        sign.setOnClickListener {
            val i = Intent(this, ProductManagerMainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 1) {
                countryModel = it.message as CountryModel.Data
//                    mPresenter!!.scheduleTap(day!!.format(formatted))
                country.text= countryModel!!.name
                SvgLoader.pluck()
                        .with(this as Activity?)

                        .load(countryModel!!.flag, flag)
                SvgLoader.pluck()
                        .with(this as Activity?)

                        .load(countryModel!!.flag, flagphone)

            }
        }
        initData()



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
        upload.setOnClickListener {
            showPictureDialog()

        }

        update_layer.setOnClickListener {


            val charset = "UTF-8"
            val requestURL = "http://161.97.164.114:8080/api/User/Info"

            val multipart = MultipartUtilityV2()
            multipart.MultipartUtilityV2(requestURL,this,"PUT")
            multipart.addFormField("FirstName", firstname.text.toString())
            multipart.addFormField("LastName", lastname.text.toString())
            multipart.addFormField("Email", email.text.toString())
            multipart.addFormField("PhoneNumber", phoneNum.text.toString())
            multipart.addFormField("Age", age.text.toString())
            multipart.addFormField("CountryId", userInfo.contryId.toString())
            if (picturePath!=""){

                multipart.addFilePart("Image", File(picturePath))
                val response = multipart.finish() // response from server.
                try {
                    val jsonObject = JSONObject(response!!)
                    val dataPayload = jsonObject.getString("message")
                    Log.d("WWWWWWWWWWWWW",dataPayload)
                    DesignerToast.Custom(this,dataPayload,
                        Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                        R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    DesignerToast.Custom(this,response, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                        R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
                }

            }else{

//                if(userInfo.profilePicturePath==""){
//                    DesignerToast.Custom(this,"update your photo", Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
//                            R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219);
//                }else{

                    val decodedString: ByteArray = Base64.decode(userInfo.profilePicturePath, Base64.DEFAULT)
                    // Bitmap Image
                    // Bitmap Image
                    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

                    val filename = "MyImage.png"
                    val file = Environment.getExternalStorageDirectory()
                    val dest = File(file, filename)

                    try {
                        val out = FileOutputStream(dest)
                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
                        out.flush()
                        out.close()
                        multipart.addFilePart("Image", null)

                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
//                    }
                    val response = multipart.finish() // response from server.


                    try {
                        val jsonObject = JSONObject(response!!)
                        val dataPayload = jsonObject.getString("message")
                        Log.d("WWWWWWWWWWWWW",dataPayload)
                        DesignerToast.Custom(this,dataPayload,
                            Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                        DesignerToast.Custom(this,response, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                            R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
                    }
         // response from server.

                }


            }



        }
    }
    private fun initData() {


            if (userInfo.profilePicturePath==""){
                Picasso.get().load(R.drawable.user).error(R.drawable.user).into(avatar_img)
                Picasso.get().load(R.drawable.user).error(R.drawable.user).transform(CircleTransform()).into(
                        userImage
                )

            }else{

                Picasso.get().load( userInfo.profilePicturePath).error(R.drawable.user).placeholder(R.drawable.user).transform(CircleTransform()).into(userImage)
                Picasso.get().load( userInfo.profilePicturePath).error(R.drawable.user).placeholder(R.drawable.user).into(avatar_img)
//                avatar_img. setImageBitmap(decodedByte)
////                avatar_img. setImageBitmap(decodedByte)
            }
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        firstname.setText(userInfo.firstName)
        lastname.setText(userInfo.lastName)
        phoneNum.setText(userInfo.phoneNumber)
        email.setText(userInfo.email)
        age.setText(userInfo.age.toString())




    }
    private fun toggle_editText(isActive: Boolean) {


        firstname.isEnabled = isActive

        lastname.isEnabled = isActive
        phoneNum.isEnabled = isActive
        email.isEnabled = isActive


    }
    private fun showPictureDialog() {


        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle(getString(R.string.select_action))
        val pictureDialogItems = arrayOf(
                getString(R.string.select_action_from_gallery)
        )
        pictureDialog.setItems(
                pictureDialogItems

        )

        { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()

            }


        }



        pictureDialog.show()
    }


    private fun choosePhotoFromGallary() {


        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, common.RESULT_LOAD_IMAGE_GALLERY)

//            val galleryIntent = Intent(
//                Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            )
//
//            startActivityForResult(galleryIntent, common.RESULT_LOAD_IMAGE_GALLERY)


        } else {

            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    common.RESULT_LOAD_IMAGE_GALLERY
            )
            Toast.makeText(this, "Gallery permission needed", Toast.LENGTH_LONG).show()

//            Alert.Instance.showMessage(this, "Gallery permission needed")


        }
    }


    private fun takePhotoFromCamera() {

        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Camera permission granted


            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, common.RESULT_LOAD_IMAGE_CAMERA)


        } else {
            // Camera permission not granted

            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    common.RESULT_LOAD_IMAGE_CAMERA
            )

            Toast.makeText(this, "Camera permission needed", Toast.LENGTH_LONG).show()
//            Alert.Instance.showMessage(this, "Camera permission needed")


        }


    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == common.RESULT_LOAD_IMAGE_CAMERA) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                takePhotoFromCamera()

                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }



        if (requestCode == common.RESULT_LOAD_IMAGE_GALLERY) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                choosePhotoFromGallary()

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "gallery permission denied", Toast.LENGTH_LONG).show()
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode === common.RESULT_LOAD_IMAGE_GALLERY && resultCode === RESULT_OK && null != data) {

//            try {
            val selectedImage = data.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            picturePath1= MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)


            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor: Cursor? = contentResolver.query(
                    selectedImage!!,
                    filePathColumn, null, null, null
            )
            cursor!!.moveToFirst()

            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
             picturePath = cursor.getString(columnIndex)

            cursor.close()



            Picasso.get()
                .load(selectedImage)
                .placeholder(R.drawable.ic_user)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(avatar_img)

            Picasso.get().load(selectedImage).error(R.drawable.user).placeholder(R.drawable.user).transform(CircleTransform()).into(userImage)



        }


        if (requestCode === common.RESULT_LOAD_IMAGE_CAMERA && resultCode === RESULT_OK && null != data) {

//             picturePath = data.extras!!.get("data") as Bitmap

//            mPresenter!!.uploadImageTap(thumbnail)

            // avatar_img!!.setImageBitmap(thumbnail)
//            saveImage(thumbnail)

        }


    }


    override fun didGetUserInfoSuccess(response: UserInfoModel) {

        var profilePicturePathString=""
        var ageString=0
        var branchIdString=0
        if (response.profilePicturePath==null){
            Log.d("profilePicturePath", response.profilePicturePath.toString())
            profilePicturePathString=""
        }else{

        Picasso.get().load(response.profilePicturePath).error(R.drawable.user).placeholder(R.drawable.user)
        .into(avatar_img)
            Picasso.get().load(response.profilePicturePath).error(R.drawable.user).placeholder(R.drawable.user)
        .into(userImage)

        }
        if (response.age==null){
            Log.d("ageString", response.age.toString())
            ageString=0
        }else{

            ageString=response.age as Int
        }

        if (response.branchId==null){
            Log.d("branchIdString", response.branchId.toString())
            branchIdString=0
        }else{

            branchIdString=response.branchId as Int
        }


        userName.text= response.firstName +" "+ response.lastName


        firstname.setText(response.firstName)
        lastname.setText(response.lastName)
        phoneNum.setText(response.phoneNumber)
        email.setText(response.email)
        age.setText(response.age.toString())


        userInfo = UserInfoModel(
                response.firstName!!,
                response.lastName!!,
                response.isVerfied!!,
                response.phoneNumber!!,
                response.email!!,
                userConfig.auth_token,
                userConfig.userid.toInt(),
                response.companyId!!.toString(),
                profilePicturePathString, ageString, branchIdString, response.contryId!!


//
//,response.age!!,response.branchId

        )
        common.session!!.createLoginSession(userInfo)


    }

    override fun didGetUserInfoFail(msg: String) {

    }

    override fun didGetCountrySuccess(response: CountryModel) {
        Log.d("SSSSSSSSSSSSSSSS", userInfo.contryId.toString())
        val iterator = (response.data.indices).iterator()

        if (iterator.hasNext()) {
            iterator.next()
        }

// do something with the rest of elements
        iterator.forEach {

            if (response.data[it].id.equals(userInfo.contryId)) {
                Log.d("SSSSSSSSSSSSSSSS", response.data[it].toString())
                country.text =response.data[it].name
                SvgLoader.pluck()
                    .with(this as Activity?)

                    .load(response.data[it].flag, flag)
                SvgLoader.pluck()
                    .with(this as Activity?)

                    .load(response.data[it].flag, flagphone)
            }




        }
    }

    override fun didGetCountryFail(msg: String) {

    }

    override fun didEmpty() {

    }



    override fun didAddSuccess(response: UpdateDataModel) {

    }

    override fun didAddFail(msg: String) {
        DesignerToast.Custom(this,msg, Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
    }
}
