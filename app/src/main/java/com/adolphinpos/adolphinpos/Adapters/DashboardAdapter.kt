package com.adolphinpos.adolphinpos.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.addCategory.IconModel
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.employee_permissions.PoliicyPermissionModel
import com.adolphinpos.adolphinpos.home.HomeModel
import com.adolphinpos.adolphinpos.product.ProductModel
import com.squareup.picasso.Picasso
import java.util.*


class DashboardAdapter(
    private val context: Context,
    private val data: List<*>,
    val action: String
):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_TYPE_ONE = 1
    val VIEW_TYPE_TWO = 2
    val VIEW_TYPE_THREE = 3
    val VIEW_TYPE_4 = 4
    val VIEW_TYPE_5 = 5
    val VIEW_TYPE_6 = 6

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
//        val params = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        val Width = params.width
//        val Height = params.height
//
//        params.weight =  (Width /2f)
//        params.height=  (Height/ 4.5).toInt()
//
//
//        holder.itemView.layoutParams = params
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
        } else {
            VIEW_TYPE_TWO
        }
    }
    override fun getItemCount(): Int {
        return data.size
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
                data[position] is HomeModel -> {
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
                data[position] is UserEmployeeModel.Data -> {
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
                data[position] is IconModel -> {
                    val itemCat = data[position] as IconModel
                    if (itemCat.id==-2){
                        image.setImageResource(R.drawable.ic_add)
                    }else{
                        image.setImageResource(R.drawable.ic_sandweshes)

//                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)

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


                    image.setOnClickListener {
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
    private inner class CategoryViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.categoryName)
        var image: ImageView= itemView.findViewById(R.id.image)
        var container: ConstraintLayout= itemView.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data[position] is CategoryModel -> {
                    val itemCat = data[position] as CategoryModel
                    if (itemCat.id==-2){
                        image.setImageResource(R.drawable.ic_add)
                    }else{
                        image.setImageResource(R.drawable.ic_sandweshes)

//                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)

                    }
                    myTextView.text=itemCat.categoryName
                    if(!itemCat.isSelected){
                        container.setBackgroundColor(context.resources.getColor(R.color.border))
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
                        image.setColorFilter(context.resources.getColor(R.color.red))
                        myTextView.setTextColor(context.resources.getColor(R.color.red))


                    }else{

                        container.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        image.setColorFilter(context.resources.getColor(R.color.white))

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
    private inner class ProductViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var myTextView: TextView= itemView.findViewById(R.id.productsName)
        var image: ImageView= itemView.findViewById(R.id.image)
        var container: ConstraintLayout= itemView.findViewById(R.id.container)
        var delete_layer: CardView= itemView.findViewById(R.id.delete_layer)
        var update_layer: CardView= itemView.findViewById(R.id.update_layer)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data[position] is ProductModel -> {
                    val itemCat = data[position] as ProductModel
                    if (itemCat.id==-2){
                        image.setImageResource(R.drawable.ic_add)
                    }else{
                        image.setImageResource(R.drawable.test)

//                        Picasso.get().load(itemCat.profilePicturePath).placeholder(R.drawable.ic_sandweshes).into(image)

                    }
                    myTextView.text=itemCat.productName
                    if(!itemCat.isSelected){
                        container.setBackgroundColor(context.resources.getColor(R.color.border))
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.border))
                        image.setColorFilter(context.resources.getColor(R.color.red))
                        myTextView.setTextColor(context.resources.getColor(R.color.red))


                    }else{

                        container.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        myTextView.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                        image.setColorFilter(context.resources.getColor(R.color.white))

                        myTextView.setTextColor(context.resources.getColor(R.color.white))

                    }


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
                data[position] is PoliicyModel.Data -> {
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
                data[position] is PoliicyPermissionModel.Data -> {
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
    private var onClick: OnItemselectedDelegate? = null

    //make interface like this
    interface OnItemselectedDelegate {


        fun onSelectItemCategory(position: Int)



    }

    fun setOnClickItemCategory(onClick: OnItemselectedDelegate) {
        this.onClick = onClick
    }


}