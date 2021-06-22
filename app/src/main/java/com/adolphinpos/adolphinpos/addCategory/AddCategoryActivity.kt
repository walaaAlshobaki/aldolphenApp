package com.adolphinpos.adolphinpos.addCategory

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.MultipartUtilityV2
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.CategoryModelNew
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_add_category.uploadImage

import org.json.JSONObject
import java.io.File

class AddCategoryActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate,CategoryDelegate {

    var picturePath: String =""
    var picturePath1: Bitmap? =null
    var selectedPosition: Int =0
    var mPresenter: CategoryPresenter? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPresenter = CategoryPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getCategories()
//        categoryModel.add(CategoryModelNew.Data(-2,"","ADD CATEGORY",true))



        uploadImage.setOnClickListener {

            showPictureDialog()
            upload2.visibility=View.VISIBLE
        }

        upload2.setOnClickListener {

            showPictureDialog()
        }

        loginBtn.setOnClickListener {


            val charset = "UTF-8"
            val requestURL = "http://161.97.164.114:8080/api/Product/Category"

            val multipart = MultipartUtilityV2()
            multipart.MultipartUtilityV2(requestURL,this,"POST")
            multipart.addFormField("Name", catName.text.toString())

            if (picturePath!=""){

                multipart.addFilePart("Image", File(picturePath))
                val response = multipart.finish() // response from server.
                val jsonObject = JSONObject(response!!)
                val dataPayload = jsonObject.getString("message")
                Log.d("WWWWWWWWWWWWW",dataPayload)
                DesignerToast.Custom(this,dataPayload,
                        Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                        R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

            }


            }

    }

    override fun onSelectItemCategory(position: Int) {




    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }

    override fun didGetCategorySuccess(response: CategoryModelNew) {

    }

    override fun didGetCategoryFail(msg: String) {

    }

    override fun didEmpty() {

    }

    private fun showPictureDialog() {

Log.d("EEEEEEEEEEEEEEEEEEEEE","$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle(getString(R.string.select_action))
        val pictureDialogItems = arrayOf(
                getString(R.string.select_action_from_gallery),

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode === common.RESULT_LOAD_IMAGE_GALLERY && resultCode === RESULT_OK && null != data) {

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
                    .into(upload)


        }


        if (requestCode === common.RESULT_LOAD_IMAGE_CAMERA && resultCode === RESULT_OK && null != data) {


        }


    }
}