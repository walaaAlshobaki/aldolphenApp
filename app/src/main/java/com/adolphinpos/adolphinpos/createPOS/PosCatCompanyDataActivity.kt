package com.adolphinpos.adolphinpos.createPOS

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.CompanyServiceBranches.AvatarParser
import com.adolphinpos.adolphinpos.CompanyServiceBranches.CompanyServiceBranchesDelegate
import com.adolphinpos.adolphinpos.CompanyServiceBranches.CompanyServiceBranchesPresenter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.select_employees.SelectEmployeeActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pos_cat_company_data.*
import kotlinx.android.synthetic.main.activity_pos_cat_company_data.flag
import kotlinx.android.synthetic.main.activity_pos_cat_company_data.loginBtn
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class PosCatCompanyDataActivity : AppCompatActivity(), CompanyServiceBranchesDelegate {
    var countryModel: UserEmployeeModel.Data? =null
    var mPresenter: CompanyServiceBranchesPresenter? = null
    var picturePath: Bitmap? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_cat_company_data)
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }
        mPresenter = CompanyServiceBranchesPresenter(this)
        mPresenter!!.delegate = this
        userName.text= userInfo.firstName +" "+ userInfo.lastName

        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 2) {
                countryModel = it.message as UserEmployeeModel.Data
//                    mPresenter!!.scheduleTap(day!!.format(formatted))
                userName.text= countryModel!!.firstName+" "+countryModel!!.lastName


            }
        }




        loginBtn.setOnClickListener {
//            mPresenter!!.uploadImageTap(picturePath!!, userInfo.userId,
//                common.selectedServiceId,
//                common.selectedServiceTypeId,
//                PhoneNo.text.toString(),
//                Email.text.toString(),
//                TaxNo.text.toString(),
//                Taxrecord.text.toString()
//                ,BranchName.text.toString(),
//                countryModel!!.id!!)
            val i = Intent(this, PosSettingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        flag.setOnClickListener {
            val i = Intent(this, SelectEmployeeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        imageView4.setOnClickListener {
            showPictureDialog()
        }
        userName.setOnClickListener {
            val i = Intent(this, SelectEmployeeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
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

            }catch (e:Exception){
                Log.d("EEEEEEEEEEEEEE", e.localizedMessage)

            }
                Picasso.get()
                    .load(selectedImage)
                    .placeholder(R.drawable.ic_user)
                    .transform(CircleTransform())
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(imageView4)

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

    override fun didAddSuccess(response: AvatarParser) {
            val i = Intent(this, PosSettingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
    }

    override fun didAddFail(msg: String) {

    }
}