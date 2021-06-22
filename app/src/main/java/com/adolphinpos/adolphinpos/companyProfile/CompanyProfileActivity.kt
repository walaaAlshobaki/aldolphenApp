package com.adolphinpos.adolphinpos.companyProfile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.createPOS.PosSettingActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.home.MainActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_company_profile.*
import java.lang.Exception

class CompanyProfileActivity : AppCompatActivity() {
    var countryModel: CountryModel.Data? =null
    var picturePath: Bitmap? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_profile)

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
        sign.setOnClickListener {
            val i = Intent(this, PosSettingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }

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

        initData()
        Picasso.get()
            .load(R.drawable.user)
            .error(R.drawable.user)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(avatar_img)
    }

private fun initData() {

    Picasso.get().load(R.drawable.user).error(R.drawable.user).transform(CircleTransform()).into(userImage)
    Picasso.get().load(R.drawable.user).error(R.drawable.user).into(avatar_img)
    userName.text= userInfo.firstName +" "+ userInfo.lastName
    firstname.setText(userInfo.firstName)
    lastname.setText(userInfo.lastName)
    phoneNum.setText(userInfo.phoneNumber)
    email.setText(userInfo.email)
    age.setText("0")

//        mPresenter!!.getUserInfo()



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
}