package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.AddTaxCategory.*
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.MultipartUtilityV2
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addCategory.CategoryDelegate
import com.adolphinpos.adolphinpos.addCategory.CategoryPresenter
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan.MainHallsModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.scanActivity.ScanActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.fragment_add_product_item.*
import kotlinx.android.synthetic.main.fragment_add_product_item.view.*
import kotlinx.android.synthetic.main.product_info_item.*
import kotlinx.android.synthetic.main.product_info_item.upload
import kotlinx.android.synthetic.main.product_info_item.upload2
import kotlinx.android.synthetic.main.product_info_item.view.*
import kotlinx.android.synthetic.main.product_info_item.view.lang
import kotlinx.android.synthetic.main.variation_layar.*
import kotlinx.android.synthetic.main.variation_layar.view.*
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList


class AddProductItemFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate , CategoryDelegate ,
    CompanyTaxCategoryDelegate,LookupDelegate,ProductDelegate {

    var picturePath: String =""
    var picturePath1: Bitmap? =null

    private lateinit var productNames: RecyclerView
    var mModelList: ArrayList<MainHallsModel.Data> = ArrayList()
    var option: ArrayList<OptionDataModel> = ArrayList()
    var optionIngredients: ArrayList<OptionDataModel> = ArrayList()
    var productSpecificationsStrings: ArrayList<String> = ArrayList()
    var productIngredientsSpecificationsStrings: ArrayList<String> = ArrayList()
    var mTaxCategoryModelList: ArrayList<TaxCategoryModel.Data> = ArrayList()
    var mLookupControlList: ArrayList<LookupModel.Data> = ArrayList()
    var categoryModel: ArrayList<CategoryModelNew.Data> = ArrayList()
    var productSpecificationsModel: ArrayList<ProductSpecificationsModel> = ArrayList()
    var variationDataModel: ArrayList<VariationModel> = ArrayList()
    var IngredientsDataModel: ArrayList<VariationModel> = ArrayList()
    var mProductPresenter: ProductPresnter? = null
    var productModel: ArrayList<ProductModel.Data.Data> = ArrayList()
    var mPresenter: CategoryPresenter? = null
    var mLookupPresrnter: LookupPresrnter? = null
    private lateinit var mAdapter: DashboardAdapter
    private lateinit var mAdapterIngredientsRecycler: DashboardAdapter
    private lateinit var mAdapterVariationRecycler: DashboardAdapter
    private lateinit var mAdapterVariationPossibilitiesRecycler: DashboardAdapter
    private lateinit var mAdapterIngredientsPossibilitiesRecycler: DashboardAdapter
    private lateinit var mAdapterOption: DashboardAdapter
    private lateinit var mAdapterOptionIngredients: DashboardAdapter
    var mCompanyTaxCategoryPresenter: CompanyTaxCategoryPresenter? = null



    fun createPossibilitiesVariation(){
        mAdapterVariationPossibilitiesRecycler = DashboardAdapter(requireActivity(), productSpecificationsModel,"ProductSpecificationsViewHolder")
        mAdapterVariationPossibilitiesRecycler!!.setOnClickItemCategory(this)
        val linearVertical3 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        VariationPossibilitiesRecycler!!.layoutManager = linearVertical3
        VariationPossibilitiesRecycler.setHasFixedSize(true)
        VariationPossibilitiesRecycler.adapter = mAdapterVariationPossibilitiesRecycler


        if (variationDataModel.size>1){

            for (i in variationDataModel.indices) {
                if (i==0)
                    continue

                for (a in  variationDataModel[0].Options!!.indices){



                    Log.d("AAAAAAAAA", variationDataModel[0].Options!![a].name)

                for (k in variationDataModel[i].Options!!.indices){

                    Log.d("BBBBBBBBBBBBBBBB", variationDataModel[0].Options!![a].name+variationDataModel[i].Options!![k].name)
                    productSpecificationsStrings.clear()
                    mAdapterVariationPossibilitiesRecycler.notifyDataSetChanged()
                    mAdapterVariationPossibilitiesRecycler.notifyDataSetChanged()
                    productSpecificationsStrings.add(variationDataModel[0].Options!![a].name)
                    productSpecificationsStrings.add(variationDataModel[i].Options!![k].name)
                    productSpecificationsModel.add(ProductSpecificationsModel(0,false,0,false,productSpecificationsStrings,0,0,0,10,10))
                    mAdapterVariationPossibilitiesRecycler.notifyDataSetChanged()
                    }



                }
            }
        }else{
            for (a in  variationDataModel[0].Options!!.indices){
                productSpecificationsStrings.clear()
                Log.d("BBBBBBBBBBBBBBBB", variationDataModel[0].Options!![a].name)
                productSpecificationsStrings.add(variationDataModel[0].Options!![a].name)
                productSpecificationsModel.add(ProductSpecificationsModel((cost.text.toString()).toInt(),false,0,false,productSpecificationsStrings,0,0,0,10,10))
                mAdapterVariationPossibilitiesRecycler.notifyDataSetChanged()
            }

            }





    }

    fun createPossibilitiesIngredients(){
        mAdapterIngredientsPossibilitiesRecycler = DashboardAdapter(requireActivity(), productSpecificationsModel,"ProductSpecificationsIngredientsViewHolder")
        mAdapterIngredientsPossibilitiesRecycler!!.setOnClickItemCategory(this)
        val linearVertical3 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        IngredientsPossibilitiesRecycler!!.layoutManager = linearVertical3
        IngredientsPossibilitiesRecycler.setHasFixedSize(true)
        IngredientsPossibilitiesRecycler.adapter = mAdapterIngredientsPossibilitiesRecycler


        if (IngredientsDataModel.size>1) {

            for (i in IngredientsDataModel.indices) {
                for (a in IngredientsDataModel[i].Options!!.indices) {
                    productIngredientsSpecificationsStrings.clear()
                    mAdapterIngredientsPossibilitiesRecycler.notifyDataSetChanged()
                    Log.d("BBBBBBBBBBBBBBBB", IngredientsDataModel[i].Options!![a].name)
                    productIngredientsSpecificationsStrings.add(IngredientsDataModel[i].Options!![a].name)
                    productSpecificationsModel.add(
                        ProductSpecificationsModel(
                            0,
                            false,
                            0,
                            false,
                            productIngredientsSpecificationsStrings,
                            0,
                            0,
                            0,
                            10,
                            10
                        )
                    )
                    mAdapterIngredientsPossibilitiesRecycler.notifyDataSetChanged()


                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    mAdapterIngredientsRecycler = DashboardAdapter(requireActivity(), IngredientsDataModel,"IngredientsViewHolder")
    mAdapterIngredientsRecycler!!.setOnClickItemCategory(this)
    val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
    view.IngredientsRecycler!!.layoutManager = linearVertical
    view.IngredientsRecycler.setHasFixedSize(true)
    view.IngredientsRecycler.adapter = mAdapterIngredientsRecycler




//    variationDataModel.add(VariationModel("walaa","3",mLookupControlList,null,true,true,1))
    mAdapterVariationRecycler = DashboardAdapter(requireActivity(), variationDataModel,"VariationViewHolder")
    mAdapterVariationRecycler!!.setOnClickItemCategory(this)
    val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
    view.VariationRecycler!!.layoutManager = linearVertical2
    view.VariationRecycler.setHasFixedSize(true)
    view.VariationRecycler.adapter = mAdapterVariationRecycler



//        val option: ArrayList<OptionDataModel> = java.util.ArrayList()
         mAdapterOption = DashboardAdapter(requireActivity(), option,"optionDataViewHolder")
         mAdapterOptionIngredients = DashboardAdapter(requireActivity(), optionIngredients,"optionDataViewHolder")

        val linearVertical4 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val linearVertical5 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        OptionsData!!.layoutManager = linearVertical4
        OptionsData.setHasFixedSize(true)
        OptionsData.setAdapter(mAdapterOption)
        OptionsDataIngredients!!.layoutManager = linearVertical5
        OptionsDataIngredients.setHasFixedSize(true)
        OptionsDataIngredients.setAdapter(mAdapterOptionIngredients)


        confirmOption.setOnClickListener {
            addOptionDialog.visibility=View.GONE
            option.add(OptionDataModel(optionName.text.toString()))
            optionName.text=Editable.Factory.getInstance().newEditable("")
            mAdapterOption.notifyDataSetChanged()
//                        common.variationDataModel[position].Options!!.add(OptionDataModel(optionName.text.toString()))
        }
        confirmOptionIngredients.setOnClickListener {
            addOptionDialogIngredients.visibility=View.GONE
            optionIngredients.add(OptionDataModel(optionNameIngredients.text.toString()))
            optionNameIngredients.text=Editable.Factory.getInstance().newEditable("")
            mAdapterOptionIngredients.notifyDataSetChanged()
//                        common.variationDataModel[position].Options!!.add(OptionDataModel(optionName.text.toString()))
        }

        addOption.setOnClickListener {
            addOptionDialog.visibility=View.VISIBLE

        }
        addOptionIngredients.setOnClickListener {
            addOptionDialogIngredients.visibility=View.VISIBLE

        }
        confirm.setOnClickListener {

            addAttributeDialog.visibility=View.GONE
            var option2: ArrayList<OptionDataModel> = ArrayList()
            option2.addAll(option)
            variationDataModel.add(VariationModel(AttributeName.text.toString(),NumberOfChoice.text.toString(),mLookupControlList,option2,required.isChecked,attribute.isChecked,mLookupControlList[controlType.selectedItemPosition].id!!,controlType.selectedItemPosition))

            mAdapterVariationRecycler.notifyDataSetChanged()

            AttributeName.text=Editable.Factory.getInstance().newEditable("")
            NumberOfChoice.text=Editable.Factory.getInstance().newEditable("")
            required.isChecked=false
            attribute.isChecked=false
            option.clear()
            mAdapterOption.notifyDataSetChanged()
//
//            mAdapterVariationRecycler = DashboardAdapter(requireActivity(), variationDataModel,"VariationViewHolder")
//            mAdapterVariationRecycler!!.setOnClickItemCategory(this)
//            val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
//            view.VariationRecycler!!.layoutManager = linearVertical2
//            view.VariationRecycler.setHasFixedSize(true)
//            view.VariationRecycler.adapter = mAdapterVariationRecycler


        }
        confirmIngredients.setOnClickListener {

            addIngredientsAttributeDialog.visibility=View.GONE
            var option2: ArrayList<OptionDataModel> = ArrayList()
            option2.addAll(optionIngredients)
            IngredientsDataModel.add(VariationModel(AttributeNameIngredients.text.toString(),NumberOfChoiceIngredients.text.toString(),mLookupControlList,option2,requiredIngredients.isChecked,attributeIngredients.isChecked,mLookupControlList[controlTypeIngredients.selectedItemPosition].id!!,controlTypeIngredients.selectedItemPosition))

            mAdapterIngredientsRecycler.notifyDataSetChanged()

            AttributeNameIngredients.text=Editable.Factory.getInstance().newEditable("")
            NumberOfChoiceIngredients.text=Editable.Factory.getInstance().newEditable("")
            requiredIngredients.isChecked=false
            attributeIngredients.isChecked=false
            optionIngredients.clear()
            mAdapterOptionIngredients.notifyDataSetChanged()
//
//            mAdapterVariationRecycler = DashboardAdapter(requireActivity(), variationDataModel,"VariationViewHolder")
//            mAdapterVariationRecycler!!.setOnClickItemCategory(this)
//            val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
//            view.VariationRecycler!!.layoutManager = linearVertical2
//            view.VariationRecycler.setHasFixedSize(true)
//            view.VariationRecycler.adapter = mAdapterVariationRecycler


        }




        view.uploadImage.setOnClickListener {

            showPictureDialog()
            upload2.visibility=View.VISIBLE
        }

        view.upload2.setOnClickListener {

            showPictureDialog()
        }

        mAdapter = DashboardAdapter(requireActivity(), productModel, "HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)
        view.productNames.adapter = mAdapter




    view.AddIngredients.setOnClickListener {
        addIngredientsAttributeDialog.visibility=View.VISIBLE
    }
    view.addVariation.setOnClickListener {

        addAttributeDialog.visibility=View.VISIBLE
//        variationDataModel.add(VariationModel("","",mLookupControlList,null,false,false,1))
//        mAdapterVariationRecycler.notifyDataSetChanged()
    }
        view.addOption.setOnClickListener {

            addOptionDialog.visibility=View.VISIBLE
//        variationDataModel.add(VariationModel("","",mLookupControlList,null,false,false,1))
//        mAdapterVariationRecycler.notifyDataSetChanged()
    }
        view.addOptionIngredients.setOnClickListener {

            addOptionDialogIngredients.visibility=View.VISIBLE
//        variationDataModel.add(VariationModel("","",mLookupControlList,null,false,false,1))
//        mAdapterVariationRecycler.notifyDataSetChanged()
    }
        view.lang.setOnClickListener {
            val intent = Intent(
                    requireActivity(),
                    ProductNameActivity::class.java
            )

            requireActivity().startActivity(intent)
        }


    view.infoIngredients.setOnClickListener {

        if(view.infoIngredients.drawable == resources.getDrawable(R.drawable.ic_arrow_down)){
            view.infoIngredients.setImageDrawable(resources.getDrawable(R.drawable.ic_more))
            view.infoIngredients.setImageResource(R.drawable.ic_more)

            view.showIngredientsPossibilities.visibility=View.GONE
            view.IngredientsRecycler.visibility=View.GONE
        }else{
            view.infoIngredients.setBackgroundResource(R.drawable.ic_arrow_down)
            view.showIngredientsPossibilities.visibility=View.VISIBLE
            view.IngredientsRecycler.visibility=View.VISIBLE
        }


    }


    view.showVariation.setOnClickListener {

        if(view.showVariation.drawable == resources.getDrawable(R.drawable.ic_arrow_down)){
            view.showVariation.setImageDrawable(resources.getDrawable(R.drawable.ic_more))
            view.showVariation.setImageResource(R.drawable.ic_more)
            view.showVariationPossibilities.visibility=View.GONE
            view.VariationRecycler.visibility=View.GONE
        }else{
            view.showVariation.setBackgroundResource(R.drawable.ic_arrow_down)
            view.showVariationPossibilities.visibility=View.VISIBLE
            view.VariationRecycler.visibility=View.VISIBLE
        }


    }



    view.showVariationPossibilities.setOnClickListener {

        view.VariationPossibilitiesRecycler.visibility=View.VISIBLE

        createPossibilitiesVariation()
    }
        view.showIngredientsPossibilities.setOnClickListener {

        view.IngredientsPossibilitiesRecycler.visibility=View.VISIBLE

        createPossibilitiesIngredients()
    }




    view.expireDate.setOnClickListener {
        val cldr   = Calendar.getInstance()
        val day:Int = cldr.get(Calendar.DAY_OF_MONTH)
        val month:Int = cldr.get(Calendar.MONTH)
        val year:Int = cldr.get(Calendar.YEAR)
        val dpd = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            expireDate.text = Editable.Factory.getInstance().newEditable("" + dayOfMonth + " / " + monthOfYear + " / " + year)
        }, year, month, day)

        dpd.show()
    }


       view.createProduct.setOnClickListener {

           val charset = "UTF-8"
           val requestURL = "http://161.97.164.114:8080/api/Product"

           val multipart = MultipartUtilityV2()
           multipart.MultipartUtilityV2(requestURL,requireActivity(),"POST")
           multipart.addFormField("Name", productName.text.toString())
           multipart.addFormField("Price", price.text.toString())
           multipart.addFormField("Description", Description.text.toString())
           multipart.addFormField("ExpiryDate", expireDate.text.toString())
           multipart.addFormField("Note", Note.text.toString())
           multipart.addFormField("Cost", cost.text.toString())
           multipart.addFormField("Quantity", Quantity.text.toString())
           multipart.addFormField("CategoryId", categoryModel[categorySpinner.selectedItemPosition].id.toString())
           multipart.addFormField("TaxCategoryId", mTaxCategoryModelList[categoryTaxSpinner.selectedItemPosition].id.toString())
           multipart.addFormField("OldPrice", "0")
           multipart.addFormField("IsNew","false")
           multipart.addFormField("IsTrackable","false")
           multipart.addFormField("Sku",barcode.text.toString())
           if (picturePath!=""){

               multipart.addFilePart("Image", File(picturePath))
               val response = multipart.finish() // response from server.
               try {
                   val jsonObject = JSONObject(response!!)
                   val dataPayload = jsonObject.getString("message")
                   Log.d("WWWWWWWWWWWWW",dataPayload)
                   DesignerToast.Custom(requireActivity(),dataPayload,
                       Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                       R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

               } catch (e: java.lang.Exception) {
                   e.printStackTrace()
                   DesignerToast.Custom(requireActivity(),response, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
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
                       DesignerToast.Custom(requireContext(),dataPayload,
                           Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                           R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

                   } catch (e: java.lang.Exception) {
                       e.printStackTrace()
                       DesignerToast.Custom(requireActivity(),response, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                           R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
                   }
                   // response from server.

               }


           }



       }
    }




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_add_product_item, container, false)
        productNames=view.productNames
        mAdapter =DashboardAdapter(requireActivity(), productModel, "HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)
        val llm = GridLayoutManager(activity, 3)
        llm.orientation = LinearLayoutManager.VERTICAL
        productNames.layoutManager = llm
        productNames.setHasFixedSize(true)
        productNames.adapter = mAdapter


        mProductPresenter = ProductPresnter(requireActivity())
        mProductPresenter!!.delegate = this
        mProductPresenter!!.getProduct(userInfo.companyId.toInt(),1,10)
        mCompanyTaxCategoryPresenter = CompanyTaxCategoryPresenter(requireActivity())
        mCompanyTaxCategoryPresenter!!.delegate = this
        mCompanyTaxCategoryPresenter!!.getCompanyTaxCategory()
        mPresenter = CategoryPresenter(requireActivity())
        mPresenter!!.delegate = this
        mPresenter!!.getCategories()
        mLookupPresrnter = LookupPresrnter(requireActivity())
        mLookupPresrnter!!.delegate = this
        mLookupPresrnter!!.getLookupProduct()
        mLookupPresrnter!!.getLookupControl()
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 50) {
                barcode.text = Editable.Factory.getInstance().newEditable(it.message.toString())
//                barcode.text = it.message.toString()
            }
        }




       view.barcodeBtn.setOnClickListener {
           val intent = Intent(activity, ScanActivity::class.java)
           startActivity(intent)
       }

        view.category.setOnClickListener {
           val intent = Intent(activity, AddTaxCategoryActivity::class.java)
           startActivity(intent)
       }


        return view
    }

    override fun onResume() {
        super.onResume()
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 50) {
                barcode.text = Editable.Factory.getInstance().newEditable(it.message.toString())

            }
        }

    }

    override fun onSelectItemCategory(position: Int) {



    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }

    override fun didGetCategorySuccess(response: CategoryModelNew) {
        categoryModel.clear()
        categoryModel.addAll(response.data)




        val dataCreateByStrings = Array<String>(categoryModel.size) { "" }
        for (i in categoryModel.indices) {
            dataCreateByStrings[i]=categoryModel[i].name.toString()
        }
        val aa =
                ArrayAdapter<Any>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, dataCreateByStrings)

        aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
        categorySpinner.adapter = aa

        categorySpinner.setSelection(0, true)
        val v: View = categorySpinner.selectedView
        (v as TextView).setTextColor(resources.getColor(R.color.appMainColor))

        categorySpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
            ) {

                common.selectedCategory = categoryModel[position].id!!

                (parent!!.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", categoryModel[position].name!!)
                categorySpinner.setSelection(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    override fun didGetCategoryFail(msg: String) {

    }

    override fun didAddCompanyTaxCategorySuccess(response: Int) {

    }

    override fun didAddCompanyTaxCategoryFail(msg: String) {

    }

    override fun didAddCompanyTaxCategoryRateSuccess(response: CategoryTaxModel) {

    }

    override fun didAddCompanyTaxCategoryRateFail(msg: String) {

    }

    override fun didGetCompanyTaxCategorySuccess(response: TaxCategoryModel) {
        mTaxCategoryModelList.clear()
        mTaxCategoryModelList.addAll(response.data)




        val dataCreateByStrings = Array<String>(mTaxCategoryModelList.size) { "" }
        for (i in mTaxCategoryModelList.indices) {
            dataCreateByStrings[i]=mTaxCategoryModelList[i].name.toString()
        }
        val aa =
            ArrayAdapter<Any>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, dataCreateByStrings)

        aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
        categoryTaxSpinner.adapter = aa

        categoryTaxSpinner.setSelection(0, true)
        val v: View = categoryTaxSpinner.selectedView
        (v as TextView).setTextColor(resources.getColor(R.color.appMainColor))

        categoryTaxSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {

                common.selectedCategoryTax = mTaxCategoryModelList[position].id!!

                (parent!!.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", mTaxCategoryModelList[position].name!!)
                categoryTaxSpinner.setSelection(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun didGetCompanyTaxCategoryFail(msg: String) {

    }

    override fun didEmpty() {

    }

    override fun didGetLookupProductSuccess(response: LookupModel) {


        val dataCreateByStrings = Array<String>(response.data.size) { "" }
        for (i in response.data.indices) {
            dataCreateByStrings[i] = response.data[i].name.toString()
        }
        val aa =
            ArrayAdapter<Any>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                dataCreateByStrings
            )

        aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerType.adapter = aa

        spinnerType.setSelection(0, true)
        val v: View = spinnerType.selectedView
        (v as TextView).setTextColor(resources.getColor(R.color.appMainColor))

        spinnerType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {

//                common.selectedCity = response.data[position].id!!

                (parent!!.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", response.data[position].name!!)
                spinnerType.setSelection(position)
                if (position==0){
                    seekBar1.visibility=View.GONE
                    conSpicy.visibility=View.GONE
                    seekBar2.visibility=View.GONE
                    conMeat.visibility=View.GONE
                    items.visibility=View.GONE
                    variation_layar.visibility=View.GONE

                }else if (position==1){
                    seekBar1.visibility=View.VISIBLE
                    conSpicy.visibility=View.VISIBLE
                    seekBar2.visibility=View.VISIBLE
                    conMeat.visibility=View.VISIBLE
                    items.visibility=View.VISIBLE
                    variation_layar.visibility=View.VISIBLE
                }else{
                    seekBar1.visibility=View.GONE
                    conSpicy.visibility=View.GONE
                    seekBar2.visibility=View.GONE
                    conMeat.visibility=View.GONE
                    items.visibility=View.VISIBLE
                    variation_layar.visibility=View.VISIBLE
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }




    }

    override fun didGetLookupProductFail(msg: String) {

    }

    override fun didEmptyProduct() {

    }

    override fun didGetLookupControlSuccess(response: LookupModel) {


        mLookupControlList.clear()
        mLookupControlList.addAll(response.data)
        mAdapterIngredientsRecycler.notifyDataSetChanged()
        mAdapterVariationRecycler.notifyDataSetChanged()

        val dataCreateByStrings = Array<String>(response.data!!.size) { "" }
        for (i in response.data!!.indices) {
            dataCreateByStrings[i]=response.data[i].name.toString()
        }

        val aa =
            ArrayAdapter<Any>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, dataCreateByStrings)

        aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
        controlType.adapter = aa
        controlTypeIngredients.adapter = aa

         controlType.setSelection(0, true)
        controlTypeIngredients.setSelection(0, true)
        val v: View = controlType.selectedView
        (v as TextView).setTextColor(requireActivity().resources.getColor(R.color.appMainColor))
        val v2: View = controlTypeIngredients.selectedView
        (v2 as TextView).setTextColor(requireActivity().resources.getColor(R.color.appMainColor))

        controlType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {


                (parent!!.getChildAt(0) as TextView).setTextColor(requireActivity().resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", response.data!![position].name!!)
                controlType.setSelection(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        controlTypeIngredients.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {


                (parent!!.getChildAt(0) as TextView).setTextColor(requireActivity().resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", response.data!![position].name!!)
                controlTypeIngredients.setSelection(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



    }

    override fun didGetLookupControlFail(msg: String) {

    }

    override fun didEmptyControl() {
    }

    override fun didGetProductSuccess(response: ProductModel.Data) {
        productModel.clear()
        productModel.addAll(response.data)
        mAdapter.notifyDataSetChanged()

    }

    override fun didGetProductFail(msg: String) {

    }

    override fun didProductEmpty() {

    }


    private fun showPictureDialog() {

        Log.d("EEEEEEEEEEEEEEEEEEEEE","$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
        val pictureDialog = AlertDialog.Builder(requireActivity())
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
                requireActivity(),
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
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                common.RESULT_LOAD_IMAGE_GALLERY
            )
            Toast.makeText(requireActivity(), "Gallery permission needed", Toast.LENGTH_LONG).show()

//            Alert.Instance.showMessage(this, "Gallery permission needed")


        }
    }


    private fun takePhotoFromCamera() {

        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Camera permission granted


            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, common.RESULT_LOAD_IMAGE_CAMERA)


        } else {
            // Camera permission not granted

            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                common.RESULT_LOAD_IMAGE_CAMERA
            )

            Toast.makeText(requireActivity(), "Camera permission needed", Toast.LENGTH_LONG).show()
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
                Toast.makeText(requireActivity(), "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }



        if (requestCode == common.RESULT_LOAD_IMAGE_GALLERY) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                choosePhotoFromGallary()

                Toast.makeText(requireActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(requireActivity(), "gallery permission denied", Toast.LENGTH_LONG).show()
            }
        }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode === common.RESULT_LOAD_IMAGE_GALLERY && resultCode === AppCompatActivity.RESULT_OK && null != data) {

            val selectedImage = data.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            picturePath1= MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImage)

            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor: Cursor? = requireActivity().contentResolver.query(
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


        if (requestCode === common.RESULT_LOAD_IMAGE_CAMERA && resultCode === AppCompatActivity.RESULT_OK && null != data) {


        }


    }

}