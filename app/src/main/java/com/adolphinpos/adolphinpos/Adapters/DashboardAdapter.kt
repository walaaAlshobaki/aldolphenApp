package com.adolphinpos.adolphinpos.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.home.HomeModel

import java.util.*

class DashboardAdapter(
    private val context: Context,
    private val data: List<*>
):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vv: View?

        vv =  LayoutInflater.from(context).inflate(R.layout.home_page_item_cell, parent, false)

//        val Width = parent.measuredWidth
//        val Height = parent.measuredHeight
//
//        vv.minimumWidth = (Width * 0.5).toInt()
//        vv.minimumHeight=  (Height/ 3.5).toInt()
//
//
//
//        vv.setPadding(10, 10, 10, 10)





//        val display: Display = (context as Activity).windowManager.defaultDisplay
//        val stageWidth = display.width-100
//        val stageHeight = display.height-300
//
//        val width: Int = stageWidth
//        val params: ViewGroup.LayoutParams = vv.layoutParams
//        params.height = (stageHeight  /4.5).toInt()
//        params.width = (stageWidth /2)
//
//        vv.layoutParams = params



        return DashboardViewHolder(vv)






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
        (holder as DashboardViewHolder).bind(position)
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
    private var onClick: OnItemselectedDelegate? = null

    //make interface like this
    interface OnItemselectedDelegate {


        fun onSelectItemCategory(position: Int)



    }

    fun setOnClickItemCategory(onClick: OnItemselectedDelegate) {
        this.onClick = onClick
    }


}