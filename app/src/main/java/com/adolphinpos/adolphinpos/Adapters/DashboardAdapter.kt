package com.adolphinpos.adolphinpos.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypeModel
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.categoryes.CategoryModel

import com.adolphinpos.adolphinpos.employee_permissions.PoliicyPermissionModel
import com.adolphinpos.adolphinpos.home.HomeModel
import com.adolphinpos.adolphinpos.home.ServiceTypeModel
import com.adolphinpos.adolphinpos.home.ServiesModel
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodModel

import com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan.HallsInfoModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan.MainHallsModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan.TableModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.gallery.CashDrawerModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.home.productManagmentModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.product_info_item.*
import org.w3c.dom.Text
import java.util.*


class DashboardAdapter(
    private val context: Context,
    private val data: List<*>?,
    val action: String
):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_TYPE_ONE = 1
    val VIEW_TYPE_TWO = 2
    val VIEW_TYPE_THREE = 3
    val VIEW_TYPE_4 = 4
    val VIEW_TYPE_5 = 5
    val VIEW_TYPE_6 = 6
    val VIEW_TYPE_7 = 7
    val VIEW_TYPE_8 = 8
    val VIEW_TYPE_9 = 9
    val VIEW_TYPE_10 = 10
    val VIEW_TYPE_11 = 11
    val VIEW_TYPE_12 = 12
    val VIEW_TYPE_13 = 13
    val VIEW_TYPE_14 = 14
    val VIEW_TYPE_15 = 15
    val VIEW_TYPE_16 = 16
    val VIEW_TYPE_17 = 17
    val VIEW_TYPE_18 = 18
    val VIEW_TYPE_19 = 19
    val VIEW_TYPE_20 = 20
    val VIEW_TYPE_21 = 21
    val VIEW_TYPE_22 = 22
    val VIEW_TYPE_23 = 23

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vv: View?
        return if (viewType == VIEW_TYPE_ONE) {
            DashboardViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.home_page_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_TWO) {
            EmpPermissionsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.permission_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_4) {
            CategoryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.category_cell_item,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_5) {
            IconViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.icon_cell_item,
                    parent,
                    false
                )
            )
        }
        else if (viewType == VIEW_TYPE_6) {
            ProductViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.product_cell_item,
                    parent,
                    false
                )
            )
        } else if (viewType == VIEW_TYPE_7) {
            ProductManagmentModelViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.product_maneger_item_cell,
                    parent,
                    false
                )
            )
        } else if (viewType == VIEW_TYPE_9) {
            OrderManagmentModelViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.main_order_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_8) {
            MainCategoryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.main_category_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_10) {
            CurrencyViewModel(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.cell_currency_item,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_17) {
            PaymantViewModel(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.payment_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_18) {
            HallsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.permission_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_11) {
            CashDrawerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.cash_drawer_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_12) {
            TransactionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.transactions_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_13) {
            ItemsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.transactions_items_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_14) {
            OrderOnHoldViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.order_on_hold_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_15) {
            HallsHoldViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.halls_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_16) {
            HallsInfoHoldViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.halls_info_cell_item,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_19) {
            IngredientsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.variation_option_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_21) {
            VariationViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.variation_option_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_20) {
            OptionDataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.option_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_22) {
            ProductSpecificationsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.possibilities_item_cell,
                    parent,
                    false
                )
            )
        }else if (viewType == VIEW_TYPE_23) {
            ProductSpecificationsIngredientsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.possibilities_item_cell,
                    parent,
                    false
                )
            )
        }else{
            PoliicyPermissionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.emp_permissions_item_cell,
                    parent,
                    false
                )
            )
        }




    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (action=="DashboardViewHolder"){
            (holder as DashboardViewHolder).bind(position)

        }else if (action== "EmpPermissionsActivity"){
            (holder as EmpPermissionsViewHolder).bind(position)
        }
        else if (action== "PoliicyPermissionModel"){
            (holder as PoliicyPermissionViewHolder).bind(position)
        }else if (action== "categoryModel"){
            (holder as CategoryViewHolder).bind(position)
        }else if (action== "AddCategoryActivity"){
            (holder as IconViewHolder).bind(position)
        }else if (action== "productModel"){
            (holder as ProductViewHolder).bind(position)
        }else if (action== "productManagmentModel"){
            (holder as ProductManagmentModelViewHolder).bind(position)
        }else if (action== "mainCategoryModel"){
            (holder as MainCategoryViewHolder).bind(position)
        }else if (action== "currencyModel"){
            (holder as CurrencyViewModel).bind(position)
        }else if (action== "CashDrawer"){
            (holder as CashDrawerViewHolder).bind(position)
        }else if (action== "orderDashboardAdapter"){
            (holder as OrderManagmentModelViewHolder).bind(position)
        }else if (action== "Transactions"){
            (holder as TransactionViewHolder).bind(position)
        }else if (action== "items"){
            (holder as ItemsViewHolder).bind(position)
        }else if (action== "orderOn"){
            (holder as OrderOnHoldViewHolder).bind(position)
        }else if (action== "hallsModel"){
            (holder as HallsHoldViewHolder).bind(position)
        }else if (action== "hallsInfoModel"){
            (holder as HallsInfoHoldViewHolder).bind(position)
        }else if (action== "PaymentMethodsActivity"){
            (holder as PaymantViewModel).bind(position)
        }else if (action== "HallsViewHolder"){
            (holder as HallsViewHolder).bind(position)
        }else if (action== "IngredientsViewHolder"){
            (holder as IngredientsViewHolder).bind(position)
        }else if (action== "VariationViewHolder"){
            (holder as VariationViewHolder).bind(position)
        }else if (action== "optionDataViewHolder"){
            (holder as OptionDataViewHolder).bind(position)
        }else if (action== "ProductSpecificationsViewHolder"){
            (holder as ProductSpecificationsViewHolder).bind(position)
        }else if (action== "ProductSpecificationsIngredientsViewHolder"){
            (holder as ProductSpecificationsIngredientsViewHolder).bind(position)
        }


    }
    override fun getItemViewType(position: Int): Int {

        return if (action == "DashboardViewHolder") {
            VIEW_TYPE_ONE
        } else if (action == "PoliicyPermissionModel") {
            VIEW_TYPE_THREE
        } else if (action == "categoryModel") {
            VIEW_TYPE_4
        }else if (action == "AddCategoryActivity") {
            VIEW_TYPE_5
        }else if (action == "productModel") {
            VIEW_TYPE_6
        }else if (action == "productManagmentModel") {
            VIEW_TYPE_7
        }else if (action == "mainCategoryModel") {
            VIEW_TYPE_8
        } else if (action == "orderDashboardAdapter") {
            VIEW_TYPE_9
        } else if (action == "currencyModel") {
            VIEW_TYPE_10
        }  else if (action == "CashDrawer") {
            VIEW_TYPE_11
        }else if (action == "Transactions") {
            VIEW_TYPE_12
        }else if (action == "items") {
            VIEW_TYPE_13
        } else if (action == "orderOn") {
            VIEW_TYPE_14
        } else if (action == "hallsModel") {
            VIEW_TYPE_15
        } else if (action == "hallsInfoModel") {
            VIEW_TYPE_16
        } else if (action == "PaymentMethodsActivity") {
            VIEW_TYPE_17
        } else if (action == "HallsViewHolder") {
            VIEW_TYPE_18
        } else if (action == "IngredientsViewHolder" ) {
            VIEW_TYPE_19
        } else if (action == "optionDataViewHolder") {
            VIEW_TYPE_20
        } else if (action == "VariationViewHolder") {
            VIEW_TYPE_21
        }  else if (action == "ProductSpecificationsViewHolder") {
            VIEW_TYPE_22
        } else if (action == "ProductSpecificationsIngredientsViewHolder") {
            VIEW_TYPE_23
        } else {
            VIEW_TYPE_TWO
        }
    }
    override fun getItemCount(): Int {

        return data!!.size

    }
    fun getColors(): Int {
        val rnd = Random()
        var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f // value component
        color = Color.HSVToColor(hsv)
        return color
    }


    private inner class DashboardViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var txt: TextView = itemView.findViewById(R.id.txt)
        var img: ImageView = itemView.findViewById(R.id.image)
        var image_gradient: ConstraintLayout = itemView.findViewById(R.id.image_gradient)






        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is ServiesModel.Data -> {
                    val itemCat = data[position] as ServiesModel.Data
                    val color: Int =getColors()
                    txt.text=itemCat.name
                    img.setImageResource(itemCat.Image!!)



                    image_gradient.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }
                data!![position] is ServiceTypeModel.Data -> {
                    val itemCat = data[position] as ServiceTypeModel.Data
                    val color: Int =getColors()
                    txt.text=itemCat.name

                    if (itemCat.name=="Restaurant"){
                        img.setImageDrawable(context.resources.getDrawable(R.drawable.restaurant))
                    }else if (itemCat.name=="Coffe shop"){
                        img.setImageDrawable(context.resources.getDrawable(R.drawable.caffee))

                    }else{
                        img.setImageResource(itemCat.Image!!)
 
                    }

                    image_gradient.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }
                data!![position] is HomeModel-> {
                val itemCat = data[position] as HomeModel
                val color: Int =getColors()
                txt.text=itemCat.name
                img.setImageResource(itemCat.Image!!)



                image_gradient.setOnClickListener {
                    onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                }
            }
                data!![position] is UserEmployeeModel.Data -> {
                    val itemCat = data[position] as UserEmployeeModel.Data

                    if (itemCat.id==0){
                        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_add))
                    }else{
                        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_user))

                    }
                    txt.text=itemCat.firstName+" "+itemCat.lastName
//                    charName.text= itemCat.firstName!!.first().toString().toUpperCase()+itemCat.lastName!!.first().toString().toUpperCase()
//                    empEmail.text=itemCat.email
                    image_gradient.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
                    }
                }


            }

        }

    }
    private inner class IconViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var image: ImageView= itemView.findViewById(R.id.image)
        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CategoryModelNew.Data -> {
                    val itemCat = data[position] as CategoryModelNew.Data
                    if (itemCat.id==-2){
                        image.setImageResource(R.drawable.ic_addpro)
                    }else{
                        val cleanImage: String =
                                itemCat.imagePath!!.replace("data:image/png;base64,", "").replace(
                                        "data:image/jpeg;base64,",
                                        ""
                                )

                        val decodedString: ByteArray = Base64.decode(cleanImage, Base64.DEFAULT)
                        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)




//                        image. setImageBitmap(decodedByte)



                        Picasso.get().load(itemCat.imagePath).placeholder(R.drawable.ic_sandweshes).into(image)

                    }
//                    myTextView.text=itemCat.categoryName
                    if(!itemCat.isSelected){
                        container.setBackgroundColor(context.resources.getColor(R.color.border))
//                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
                        image.setColorFilter(context.resources.getColor(R.color.red))
//                        myTextView.setTextColor(context.resources.getColor(R.color.red))


                    }else{

                        container.setBackgroundColor(context.resources.getColor(R.color.red))
//                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        image.setColorFilter(context.resources.getColor(R.color.white))

//                        myTextView.setTextColor(context.resources.getColor(R.color.white))

                    }


                    container.setOnClickListener {
                        Log.d("EEEEEEEEEEEEEEEEEEEEE",position.toString())
                        onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }



            }

        }

    }
    private inner class CashDrawerViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


//        var image: ImageView= itemView.findViewById(R.id.image)
//        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CashDrawerModel -> {
//                    val itemCat = data[position] as IconModel
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.ic_sandweshes)
//
////                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)
//
//                    }
////                    myTextView.text=itemCat.categoryName
//                    if(!itemCat.isSelected){
//                        container.setBackgroundColor(context.resources.getColor(R.color.border))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
//                        image.setColorFilter(context.resources.getColor(R.color.red))
////                        myTextView.setTextColor(context.resources.getColor(R.color.red))
//
//
//                    }else{
//
//                        container.setBackgroundColor(context.resources.getColor(R.color.red))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
//                        image.setColorFilter(context.resources.getColor(R.color.white))
//
////                        myTextView.setTextColor(context.resources.getColor(R.color.white))
//
//                    }
//
//
//                    image.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }
//
//
//
            }
//
        }

    }


    private inner class TransactionViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


//        var image: ImageView= itemView.findViewById(R.id.image)
//        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CashDrawerModel -> {
//                    val itemCat = data[position] as IconModel
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.ic_sandweshes)
//
////                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)
//
//                    }
////                    myTextView.text=itemCat.categoryName
//                    if(!itemCat.isSelected){
//                        container.setBackgroundColor(context.resources.getColor(R.color.border))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
//                        image.setColorFilter(context.resources.getColor(R.color.red))
////                        myTextView.setTextColor(context.resources.getColor(R.color.red))
//
//
//                    }else{
//
//                        container.setBackgroundColor(context.resources.getColor(R.color.red))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
//                        image.setColorFilter(context.resources.getColor(R.color.white))
//
////                        myTextView.setTextColor(context.resources.getColor(R.color.white))
//
//                    }
//
//
//                    image.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }
//
//
//
            }
//
        }

    }

    private inner class ItemsViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


//        var image: ImageView= itemView.findViewById(R.id.image)
//        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CashDrawerModel -> {
//                    val itemCat = data[position] as IconModel
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.ic_sandweshes)
//
////                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)
//
//                    }
////                    myTextView.text=itemCat.categoryName
//                    if(!itemCat.isSelected){
//                        container.setBackgroundColor(context.resources.getColor(R.color.border))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
//                        image.setColorFilter(context.resources.getColor(R.color.red))
////                        myTextView.setTextColor(context.resources.getColor(R.color.red))
//
//
//                    }else{
//
//                        container.setBackgroundColor(context.resources.getColor(R.color.red))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
//                        image.setColorFilter(context.resources.getColor(R.color.white))
//
////                        myTextView.setTextColor(context.resources.getColor(R.color.white))
//
//                    }
//
//
//                    image.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }
//
//
//
            }
//
        }

    }

    private inner class OrderOnHoldViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


//        var image: ImageView= itemView.findViewById(R.id.image)
//        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CashDrawerModel -> {
//                    val itemCat = data[position] as IconModel
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.ic_sandweshes)
//
////                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)
//
//                    }
////                    myTextView.text=itemCat.categoryName
//                    if(!itemCat.isSelected){
//                        container.setBackgroundColor(context.resources.getColor(R.color.border))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
//                        image.setColorFilter(context.resources.getColor(R.color.red))
////                        myTextView.setTextColor(context.resources.getColor(R.color.red))
//
//
//                    }else{
//
//                        container.setBackgroundColor(context.resources.getColor(R.color.red))
////                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
//                        image.setColorFilter(context.resources.getColor(R.color.white))
//
////                        myTextView.setTextColor(context.resources.getColor(R.color.white))
//
//                    }
//
//
//                    image.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }
//
//
//
            }
//
        }

    }

    private inner class HallsHoldViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var tableId: TextView= itemView.findViewById(R.id.tableId)
        var table: ImageView= itemView.findViewById(R.id.table)
        var imageAdd: ImageView= itemView.findViewById(R.id.imageAdd)

        var tablecon: ConstraintLayout= itemView.findViewById(R.id.tablecon)
        var add: ConstraintLayout= itemView.findViewById(R.id.add)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is TableModel.Data -> {
                    val itemCat = data[position] as TableModel.Data

                    if (itemCat.id==-2){
                        add.visibility=View.VISIBLE
                        tablecon.visibility=View.GONE
                        imageAdd.setImageResource(R.drawable.ic_addpro)
                    }else{
                        tablecon.visibility=View.VISIBLE
                        add.visibility=View.GONE
                    }
                    if (itemCat.status==1){
                        table.setImageResource(R.drawable.ic_free)
                    }else if (itemCat.status==2){
                        table.setImageResource(R.drawable.ic_reserved)

                    }
                    else if (itemCat.status==3){
                        table.setImageResource(R.drawable.ic_checkedin)

                    }
                    else if (itemCat.status==4){
                        table.setImageResource(R.drawable.ic_ordered)

                    }
                    else if (itemCat.status==5){
                        table.setImageResource(R.drawable.ic_blocked)

                    }else if (itemCat.status==6){
                        table.setImageResource(R.drawable.ic_delivered)

                    }
                    tableId.text=itemCat.id.toString()


                    add.setOnClickListener {
                        onClick!!.onSelectItemProduct(position,"table")
                    }
                }

            }

        }

    }
    private inner class HallsInfoHoldViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        var image: ImageView = itemView.findViewById(R.id.imageView11)
        var count: TextView = itemView.findViewById(R.id.count)
        var hallsName: TextView = itemView.findViewById(R.id.hallsName)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is HallsInfoModel -> {
                    val itemCat = data[position] as HallsInfoModel

                    image.setImageResource(itemCat.Image)

                    count.text = itemCat.count.toString()
                    hallsName.text = itemCat.name

                }
//
            }

        }
    }
    private inner class CategoryViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.categoryName)
        var image: ImageView= itemView.findViewById(R.id.image)
        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CategoryModelNew.Data -> {
                    val itemCat = data[position] as CategoryModelNew.Data

                    Log.d("EEEEEEEEEEEEEEEEEEEEE",itemCat.name.toString())

                    if (itemCat.id==-2){
                        image.setImageResource(R.drawable.ic_add)
                        if(!itemCat.isSelected) {
                            image.setColorFilter(context.resources.getColor(R.color.red))
                        }else{
                            image.setColorFilter(context.resources.getColor(R.color.white))
                        }
                    }else{
//                        val cleanImage: String =
//                            itemCat.imagePath!!.replace("data:image/png;base64,", "").replace(
//                                "data:image/jpeg;base64,",
//                                ""
//                            )
//
//                        val decodedString: ByteArray = Base64.decode(cleanImage, Base64.DEFAULT)
//                        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

//        Picasso.get().load(decodedByte).error(R.drawable.user).placeholder(R.drawable.user)
//        .into(avatar_img)


//                        image. setImageBitmap(decodedByte)

                        Picasso.get().load(itemCat.imagePath).placeholder(R.drawable.ic_sandweshes).into(image)

                    }
                    myTextView.text=itemCat.name
                    if(!itemCat.isSelected){
                        container.setBackgroundColor(context.resources.getColor(R.color.border))
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
//                        image.setColorFilter(context.resources.getColor(R.color.red))
                        myTextView.setTextColor(context.resources.getColor(R.color.red))


                    }else{

                        container.setBackgroundColor(context.resources.getColor(R.color.red))
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.red))
//                        image.setColorFilter(context.resources.getColor(R.color.white))

                        myTextView.setTextColor(context.resources.getColor(R.color.white))

                    }


                    myTextView.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }



            }

        }

    }

    private inner class HallsViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.permissionsText)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is MainHallsModel.Data -> {
                    val itemCat = data[position] as MainHallsModel.Data

                    myTextView.text=itemCat.name
                    if(!itemCat.isSelected){
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
                        myTextView.setTextColor(context.resources.getColor(R.color.appMainColor))

                    }else{
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        myTextView.setTextColor(context.resources.getColor(R.color.white))
                    }






                    myTextView.setOnClickListener {
                        if(itemCat.id==-2){
                            onClick!!.onSelectItemProduct(position,"add")
                        }else{
                            onClick!!.onSelectItemCategory(position)
                        }

//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }

                data!![position] is ProductModel.Data.Data -> {
                      val itemCat = data[position] as ProductModel.Data.Data

                    myTextView.text=itemCat.name
                    if(!itemCat.isSelected){
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
                        myTextView.setTextColor(context.resources.getColor(R.color.appMainColor))

                    }else{
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        myTextView.setTextColor(context.resources.getColor(R.color.white))
                    }






                    myTextView.setOnClickListener {

                            onClick!!.onSelectItemCategory(position)
                        }
                }



            }

        }

    }
    private inner class MainCategoryViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.categoryName)
        var image: ImageView= itemView.findViewById(R.id.image)
        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CategoryModelNew.Data -> {
                    val itemCat = data[position] as CategoryModelNew.Data


//                    val cleanImage: String =
//                            itemCat.imagePath!!.replace("data:image/png;base64,", "").replace(
//                                    "data:image/jpeg;base64,",
//                                    ""
//                            )
//
//                    val decodedString: ByteArray = Base64.decode(cleanImage, Base64.DEFAULT)
//                    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)


//                    image. setImageBitmap(decodedByte)

                    Picasso.get().load(itemCat.imagePath).placeholder(R.drawable.ic_sandweshes).into(image)
                    myTextView.text=itemCat.name



                    myTextView.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }



            }

        }

    }
    private inner class ProductViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.productsName)
        var txt: TextView= itemView.findViewById(R.id.txt)
        var image: ImageView= itemView.findViewById(R.id.image)
        var imageAdd: ImageView= itemView.findViewById(R.id.imageAdd)
        var image_gradient: ImageView= itemView.findViewById(R.id.image_gradient)
        var container: ConstraintLayout= itemView.findViewById(R.id.container)
        var pro: ConstraintLayout= itemView.findViewById(R.id.pro)
        var add: ConstraintLayout= itemView.findViewById(R.id.add)
        var delete_layer: CardView= itemView.findViewById(R.id.delete_layer)
        var update_layer: CardView= itemView.findViewById(R.id.update_layer)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is ProductModel.Data.Data -> {
                    val itemCat = data[position] as ProductModel.Data.Data
                    if (itemCat.id==-2){
                        imageAdd.setImageResource(R.drawable.ic_addpro)
                        txt.text=itemCat.name
                        add.visibility=View.VISIBLE
                        pro.visibility=View.GONE
                    }else{
                        image.setImageResource(R.drawable.test)
                        add.visibility=View.GONE
                        pro.visibility=View.VISIBLE
                        myTextView.text=itemCat.name
                        Picasso.get().load(itemCat.imagePath).placeholder(R.drawable.ic_sandweshes).into(image)

                    }
                    add.setOnClickListener {
                        onClick!!.onSelectItemProduct(position,"")
                    }

                    update_layer.setOnClickListener {

                        onClick!!.onSelectItemProduct(position,"edit")
                    }


                }



            }

        }

    }


    private inner class ProductManagmentModelViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var productsName: TextView= itemView.findViewById(R.id.productsName)
        var priceText: TextView= itemView.findViewById(R.id.priceText)
        var discountText: TextView= itemView.findViewById(R.id.discountText)
//        var stock: TextView= itemView.findViewById(R.id.stock)
        var image: ImageView= itemView.findViewById(R.id.image)
        var permissionsCard: CardView= itemView.findViewById(R.id.permissionsCard)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is ProductModel.Data.Data -> {
                    val itemCat = data[position] as ProductModel.Data.Data
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.test)

                        Picasso.get().load(itemCat.imagePath).placeholder(R.drawable.test).into(image)
                    priceText.text=itemCat.price.toString()+" sar"
                    discountText.text="10%"

//                    }
                    productsName.text=itemCat.name



                    permissionsCard.setOnClickListener {
                        onClick!!.onSelectItemProduct(position,"productManagmentModel")
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }



            }

        }

    }
    private inner class OrderManagmentModelViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

//        var productsName: TextView= itemView.findViewById(R.id.productsName)
        var category: TextView= itemView.findViewById(R.id.category)
        var productName: TextView= itemView.findViewById(R.id.productName)
        var priceAfterDiscount: TextView= itemView.findViewById(R.id.priceAfterDiscount)
        var discount: TextView= itemView.findViewById(R.id.discount)
        var total: TextView= itemView.findViewById(R.id.total)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is productManagmentModel -> {
                    val itemCat = data[position] as productManagmentModel
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.test)

                    category.text=itemCat.category
                    productName.text=itemCat.name
                    priceAfterDiscount.text="USD. 07.30"
                    discount.text="11.50 10%OFF"
                    total.text="USD. 28.50"

//                    }
//                    productsName.text=itemCat.name



//                    myTextView.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }



            }

        }

    }
    private inner class CurrencyViewModel   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

//        var productsName: TextView= itemView.findViewById(R.id.productsName)
        var titlec: TextView= itemView.findViewById(R.id.titlec)



        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CurrencyTypeModel.Data -> {
                    val itemCat = data[position] as  CurrencyTypeModel.Data
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.test)

                    titlec.text=itemCat.currenciesCode


//                    }
//                    productsName.text=itemCat.name



//                    myTextView.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }



            }

        }

    }
    private inner class PaymantViewModel   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

//        var productsName: TextView= itemView.findViewById(R.id.productsName)
        var titlec: TextView= itemView.findViewById(R.id.txt)
        var textBack: TextView= itemView.findViewById(R.id.textBack)



        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is CurrencyTypeModel.Data -> {
                    val itemCat = data[position] as  CurrencyTypeModel.Data
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.test)

                    titlec.text=itemCat.currenciesCode
                    textBack.text=itemCat.currenciesName


//                    }
//                    productsName.text=itemCat.name



//                    myTextView.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }
                data!![position] is PaymentMethoodModel.Data -> {
                    val itemCat = data[position] as  PaymentMethoodModel.Data
//                    if (itemCat.id==-2){
//                        image.setImageResource(R.drawable.ic_add)
//                    }else{
//                        image.setImageResource(R.drawable.test)

                    titlec.text=itemCat.name
                    textBack.text=itemCat.name

//                    }
//                    productsName.text=itemCat.name



//                    myTextView.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }


            }

        }

    }
    private inner class EmpPermissionsViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.permissionsText)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is PoliicyModel.Data -> {
                    val itemCat = data[position] as PoliicyModel.Data

                    myTextView.text=itemCat.name
                    if(!itemCat.isSelected){
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
                        myTextView.setTextColor(context.resources.getColor(R.color.appMainColor))


                    }else{

                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        myTextView.setTextColor(context.resources.getColor(R.color.white))

                    }


                    myTextView.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
//                        val intent = Intent(
//                            context,
//                            ShowImages::class.java
//                        )
//                        intent.putExtra("image", image)
//                        context.startActivity(intent)
                    }
                }



            }

        }

    }

    private inner class PoliicyPermissionViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var empName: TextView= itemView.findViewById(R.id.empName)
        var switch1: Switch= itemView.findViewById(R.id.switch1)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is PoliicyPermissionModel.Data -> {
                    val itemCat = data[position] as PoliicyPermissionModel.Data

                    empName.text=itemCat.name

                        switch1.setChecked(false)




                    switch1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                        // do something, the isChecked will be
                        // true if the switch is in the On position
                        if (isChecked){
                            common.prermtion.add(itemCat.id!!)
                        }else{
                            common.prermtion.remove(itemCat.id!!)

                        }
                    })



//
//                    myTextView.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
                }



            }

        }

    }

    private inner class ProductSpecificationsViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var possible: TextView= itemView.findViewById(R.id.possible)
        var priceAdjustment: EditText= itemView.findViewById(R.id.priceAdjustment)
        var preSelcted: CheckBox= itemView.findViewById(R.id.preSelcted)
        var trackable: CheckBox= itemView.findViewById(R.id.trackable)
        var delete: ImageView= itemView.findViewById(R.id.delete)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is ProductSpecificationsModel -> {
                    val itemCat = data[position] as ProductSpecificationsModel
Log.d("WWWWWWWWWWWWWWW",itemCat.itemIds.toString())
                    possible.text=itemCat.itemIds!![0] +"/" +itemCat.itemIds!![1]
                    priceAdjustment.text=Editable.Factory.getInstance().newEditable(itemCat.priceAdjustment.toString())
                    preSelcted.isChecked=false
                    trackable.isChecked=false
                }



            }

        }

    }

    private inner class ProductSpecificationsIngredientsViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var possible: TextView= itemView.findViewById(R.id.possible)
        var priceAdjustment: EditText= itemView.findViewById(R.id.priceAdjustment)
        var preSelcted: CheckBox= itemView.findViewById(R.id.preSelcted)
        var trackable: CheckBox= itemView.findViewById(R.id.trackable)
        var delete: ImageView= itemView.findViewById(R.id.delete)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is ProductSpecificationsModel -> {
                    val itemCat = data[position] as ProductSpecificationsModel
                    Log.d("WWWWWWWWWWWWWWW",itemCat.itemIds.toString())
                    possible.text=itemCat.itemIds!![0]
                    priceAdjustment.text=Editable.Factory.getInstance().newEditable(itemCat.priceAdjustment.toString())
                    preSelcted.isChecked=false
                    trackable.isChecked=false
                }



            }

        }

    }
    private inner class VariationViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var NumberOfChoice: TextView= itemView.findViewById(R.id.NumberOfChoice)
        var AttributeName: TextView= itemView.findViewById(R.id.AttributeName)

        var controlType: Spinner= itemView.findViewById(R.id.controlType)
        var required: CheckBox= itemView.findViewById(R.id.required)
        var attribute: CheckBox= itemView.findViewById(R.id.attribute)
        var OptionsData: RecyclerView= itemView.findViewById(R.id.OptionsData)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is VariationModel -> {
                    val itemCat = data[position] as VariationModel

                    NumberOfChoice.text=itemCat.NumberOfChoice
                    AttributeName.text= itemCat.AttributeName


                    required.setChecked(itemCat.required)
                    attribute.setChecked(itemCat.ShowAttribute)


                    val mAdapter = DashboardAdapter(context, itemCat.Options,"optionDataViewHolder")

                    val linearVertical = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    OptionsData!!.layoutManager = linearVertical
                    OptionsData.setHasFixedSize(true)
                    OptionsData.setAdapter(mAdapter)






                    val dataCreateByStrings = Array<String>(itemCat.ControlType!!.size) { "" }
                    for (i in itemCat.ControlType!!.indices) {
                        dataCreateByStrings[i]=itemCat.ControlType!![i].name.toString()
                    }
                    val aa =
                        ArrayAdapter<Any>(context, android.R.layout.simple_spinner_dropdown_item, dataCreateByStrings)

                    aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    controlType.adapter = aa

                    controlType.setSelection(itemCat.selectedControlTypePosition, true)
                    val v: View = controlType.selectedView
                    (v as TextView).setTextColor(context.resources.getColor(R.color.appMainColor))

                    controlType.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View,
                            position: Int,
                            id: Long
                        ) {

                            itemCat.selectedControlType = itemCat.ControlType!![position].id!!

                            (parent!!.getChildAt(0) as TextView).setTextColor(context.resources.getColor(R.color.appMainColor))

                            Log.d("DDDDDDDDDDDDDDDD", itemCat.ControlType!![position].name!!)
                            controlType.setSelection(position)


                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }


//                    common.variationDataModel.add(VariationModel( AttributeName.text.toString(),NumberOfChoice.text.toString(),null,,required.isChecked(),attribute.isChecked(),itemCat.selectedControlType))


                }





            }

        }

    }


    private inner class IngredientsViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var NumberOfChoice: TextView = itemView.findViewById(R.id.NumberOfChoice)
        var AttributeName: TextView = itemView.findViewById(R.id.AttributeName)
        var controlType: Spinner = itemView.findViewById(R.id.controlType)
        var required: CheckBox = itemView.findViewById(R.id.required)
        var attribute: CheckBox = itemView.findViewById(R.id.attribute)
        var OptionsData: RecyclerView = itemView.findViewById(R.id.OptionsData)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is VariationModel -> {
                    val itemCat = data[position] as VariationModel

                    NumberOfChoice.text = itemCat.NumberOfChoice
                    AttributeName.text = itemCat.AttributeName


                    required.setChecked(itemCat.required)
                    attribute.setChecked(itemCat.ShowAttribute)


                    val mAdapter =
                        DashboardAdapter(context, itemCat.Options, "optionDataViewHolder")

                    val linearVertical =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    OptionsData!!.layoutManager = linearVertical
                    OptionsData.setHasFixedSize(true)
                    OptionsData.setAdapter(mAdapter)


                    val dataCreateByStrings = Array<String>(itemCat.ControlType!!.size) { "" }
                    for (i in itemCat.ControlType!!.indices) {
                        dataCreateByStrings[i] = itemCat.ControlType!![i].name.toString()
                    }
                    val aa =
                        ArrayAdapter<Any>(
                            context,
                            android.R.layout.simple_spinner_dropdown_item,
                            dataCreateByStrings
                        )

                    aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    controlType.adapter = aa

                    controlType.setSelection(itemCat.selectedControlTypePosition, true)
                    val v: View = controlType.selectedView
                    (v as TextView).setTextColor(context.resources.getColor(R.color.appMainColor))

                    controlType.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View,
                            position: Int,
                            id: Long
                        ) {

                            itemCat.selectedControlType = itemCat.ControlType!![position].id!!

                            (parent!!.getChildAt(0) as TextView).setTextColor(
                                context.resources.getColor(
                                    R.color.appMainColor
                                )
                            )

                            Log.d("DDDDDDDDDDDDDDDD", itemCat.ControlType!![position].name!!)
                            controlType.setSelection(position)


                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}


//                    common.variationDataModel.add(VariationModel( AttributeName.text.toString(),NumberOfChoice.text.toString(),null,,required.isChecked(),attribute.isChecked(),itemCat.selectedControlType))


                    }
                }
            }
        }
    }

    private inner class OptionDataViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var OptionName: TextView= itemView.findViewById(R.id.OptionName)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data!![position] is OptionDataModel -> {
                    val itemCat = data[position] as OptionDataModel

                    OptionName.text=itemCat.name


                }



            }

        }

    }
    private var onClick: OnItemselectedDelegate? = null

    //make interface like this
    interface OnItemselectedDelegate {


        fun onSelectItemCategory(position: Int)
        fun onSelectItemProduct(position: Int,action:String)




    }

    fun setOnClickItemCategory(onClick: OnItemselectedDelegate) {
        this.onClick = onClick
    }


}