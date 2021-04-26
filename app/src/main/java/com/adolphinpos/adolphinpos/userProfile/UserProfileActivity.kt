package com.adolphinpos.adolphinpos.userProfile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.CompanyServiceBranches.AvatarParser
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.home.MainActivity
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoDelegate
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoPresenter
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_profile.country
import kotlinx.android.synthetic.main.activity_user_profile.email
import kotlinx.android.synthetic.main.activity_user_profile.firstname
import kotlinx.android.synthetic.main.activity_user_profile.flag
import kotlinx.android.synthetic.main.activity_user_profile.flagphone
import kotlinx.android.synthetic.main.activity_user_profile.lastname
import kotlinx.android.synthetic.main.activity_user_profile.phoneNum
import kotlinx.android.synthetic.main.activity_user_profile.sign
import kotlinx.android.synthetic.main.activity_user_profile.userImage
import kotlinx.android.synthetic.main.activity_user_profile.userName
import java.lang.Exception

class UserProfileActivity : AppCompatActivity() , UserInfoDelegate {
    var mPresenter: UserInfoPresenter? = null
    var countryModel: CountryModel.Data? =null
    var picturePath: Bitmap? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        mPresenter = UserInfoPresenter(this)
        mPresenter!!.delegate = this

        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }
        sign.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
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
                        .setPlaceHolder(R.drawable.ca,R.drawable.ca)
                        .load(countryModel!!.flag, flag)
                SvgLoader.pluck()
                        .with(this as Activity?)
                        .setPlaceHolder(R.drawable.ca,R.drawable.ca)
                        .load(countryModel!!.flag, flagphone)

            }
        }
        initData()
        Picasso.get()
            .load(R.drawable.user)
            .error(R.drawable.user)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(avatar_img)


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

            mPresenter!!.updateInfo(picturePath!!,firstname.text.toString(),lastname.text.toString(),"795152353",email.text.toString(),age.text .toString() ,countryModel!!.id)
        }
    }
    private fun initData() {


            if (userInfo.profilePicturePath==null){
                Picasso.get().load(R.drawable.user).error(R.drawable.user).into(avatar_img)
                Picasso.get().load(R.drawable.user).error(R.drawable.user).transform(CircleTransform()).into(userImage)

            }else{
                Picasso.get().load("https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg").error(R.drawable.user).placeholder(R.drawable.user).into(avatar_img)
                Picasso.get().load("https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg").error(R.drawable.user).placeholder(R.drawable.user).into(userImage)
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
                getString(R.string.select_action_from_gallery),
                getString(R.string.select_action_from_camera)
        )
        pictureDialog.setItems(
                pictureDialogItems

        )

        { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode === common.RESULT_LOAD_IMAGE_GALLERY && resultCode === RESULT_OK && null != data) {

//            try {
            val selectedImage = data.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            picturePath= MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
//                val cursor: Cursor? = contentResolver.query(selectedImage!!, filePath, null, null, null)
//                cursor!!.moveToFirst()
//                val imagePath: String = cursor!!.getString(cursor!!.getColumnIndex(filePath[0]))
//
//                val options = BitmapFactory.Options()
//                options.inPreferredConfig = Bitmap.Config.ARGB_8888
//                val bitmap = BitmapFactory.decodeFile(imagePath, options)

            // Do something with the bitmap


            // At the end remember to close the cursor or you will end with the RuntimeException!

            // Do something with the bitmap


            // At the end remember to close the cursor or you will end with the RuntimeException!
//                cursor.close()
            try {
//                mPresenter!!.uploadImageTap(bitmap, userInfo.userId, common.selectedServiceId, common.selectedServiceTypeId, PhoneNo.text.toString(), Email.text.toString(),
//                    TaxNo.text.toString(), Taxrecord.text.toString(), BranchName.text.toString(), countryModel!!.id!!)

            }catch (e: Exception){
                Log.d("EEEEEEEEEEEEEE", e.localizedMessage)

            }
            Picasso.get()
                    .load(selectedImage)
                    .placeholder(R.drawable.ic_user)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(avatar_img)

            // avatar_img.setImageBitmap(BitmapFactory.decodeFile(picturePath))

//                mPresenter!!.uploadImageTap(BitmapFactory.decodeFile(picturePath))

//            } catch (ex: Exception) {
//                Log.d("Exception", ex.localizedMessage)
//
//
//            }


//            Alert.Instance.showMessage(this, R.string.upload, true)


        }


        if (requestCode === common.RESULT_LOAD_IMAGE_CAMERA && resultCode === RESULT_OK && null != data) {

//             picturePath = data.extras!!.get("data") as Bitmap

//            mPresenter!!.uploadImageTap(thumbnail)

            // avatar_img!!.setImageBitmap(thumbnail)
//            saveImage(thumbnail)

        }


    }

    override fun didGetUserInfoSuccess(response: UserInfoModel) {
//        Picasso.get().load(response.profilePicturePath).error(R.drawable.user).placeholder(R.drawable.user).transform(CircleTransform()).into(userImage)
//        userName.text= response.firstName +" "+ response.lastName

        firstname.setText(response.firstName)
        lastname.setText(response.lastName)
        phoneNum.setText(response.phoneNumber)
        email.setText(response.email)
        age.setText(response.age.toString())
    }

    override fun didGetUserInfoFail(msg: String) {

    }

    override fun didEmpty() {

    }

    override fun didAddSuccess(response: AvatarParser) {
        TODO("Not yet implemented")
    }

    override fun didAddFail(msg: String) {
        TODO("Not yet implemented")
    }
}
