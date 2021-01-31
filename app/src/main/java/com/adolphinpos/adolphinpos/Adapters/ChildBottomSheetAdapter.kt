package com.adolphinpos.adolphinpos.Adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Utilites.BackgroundColorTransform
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.home.ChildModel
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class ChildBottomSheetAdapter(var itemsData: ArrayList<ChildModel>, var context: Context) :
    RecyclerView.Adapter<ChildBottomSheetAdapter.ViewHolder>() {


    var thisClass = this

    private val TYPE_EPMPY = 0
    private val TYPE_ITEM = 1
    private val TYPE_LOADER = 2


    override fun getItemViewType(position: Int): Int {

        Log.d("EEEEEEEEEEEEE",itemsData.toString())
        val itemCat = itemsData.get(position)

        var posType = TYPE_ITEM



        if (itemCat.type == "empty") {

            posType = TYPE_EPMPY

        }


        if (itemCat.type == "loader") {

            posType = TYPE_LOADER

        }

        return posType
    }


    //declare listner interface
    private var onClick: OnItemClickedDelegate? = null

    //make interface like this
    interface OnItemClickedDelegate {

        fun onChildSelect(position: Int)
        fun onGraphSelect(index: Int)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater






        when (viewType) {

            TYPE_EPMPY -> {

                val vv = inflater.inflate(R.layout.empty_cell, parent, false)

                val Width = parent.measuredWidth
                val Height = parent.measuredHeight

                vv.minimumWidth = Width
                vv.minimumHeight = Height

                return EmptyHolder(vv)
            }

            TYPE_LOADER -> {

                val vv = inflater.inflate(R.layout.loading_cell, parent, false)

                val Width = parent.measuredWidth
                val Height = parent.measuredHeight

                vv.minimumWidth = Width
                vv.minimumHeight = Height

                return LoaderHolder(vv)
            }


            else -> {

                val vv = inflater.inflate(R.layout.fragment_user_option_dialog_item, parent, false)

//                val Width = parent.measuredWidth - 50
//
//                vv.setPadding(10,10,10,10)
//
//
//
//                vv.minimumWidth= Width
//                vv.minimumHeight= context.resources.getDimension(R.dimen.schedule_item_size).toInt()


                return ItemHolder(vv)
            }

        }


    }


    override fun getItemCount(): Int {
        return itemsData.size
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {

            when (holder.itemViewType) {


                TYPE_EPMPY -> {




                    val emptyHolder = holder as EmptyHolder


                    emptyHolder.txtMessage.text = context.resources.getString(R.string.noresult)


                }


                TYPE_ITEM -> {


                    val itemCat = itemsData[position]
                    val itemHolder = holder as ItemHolder

                    itemHolder.childName_txt.text = itemCat.fullname
                    itemHolder.avatar.setImageResource(itemCat.avatar)
                    if (itemCat.id==2){
                        itemHolder.container.setBackgroundColor(context.resources.getColor(R.color.red) )

                        itemHolder.childName_txt.setTextColor(context.resources.getColor( R.color.white))
                        ImageViewCompat.setImageTintList(
                            itemHolder.avatar, ColorStateList.valueOf(
                                context.resources.getColor( R.color.white)

                            )
                        )
                    }
//                    Picasso.get()
//                        .load(itemCat.avatar)
//                        .placeholder(R.drawable.ic_user)
//                        .transform(
//                            BackgroundColorTransform(
//                                ContextCompat.getColor(
//                                    context,
//                                    R.color.layer
//                                )
//                            )
//                        )
//                        .transform(CircleTransform())
//
//                        .into(itemHolder.avatar)


                    itemHolder.container.setOnClickListener {
                        onClick!!.onChildSelect(
                            position
                        )
                    }


//                    itemHolder.graph_btn.setOnClickListener {
//                        onClick!!.onGraphSelect(
//                            position
//                        )
//                    }




//                    if (common.selectedChild == 0) {
//
//                        itemHolder.graph_btn.visibility = View.INVISIBLE
//
//
//
//                    }else{
//                        itemHolder.graph_btn.visibility = View.VISIBLE
//
//                    }




                }
            }


        } catch (ex: Exception) {
            //Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    inner class EmptyHolder(view: View) : ViewHolder(view) {

        var txtMessage: TextView = view.findViewById(R.id.empty_txt)

    }


    inner class LoaderHolder(view: View) : ViewHolder(view)


    inner class ItemHolder(view: View) : ViewHolder(view) {

        var childName_txt: TextView = view.findViewById(R.id.text)
        var container: ConstraintLayout = view.findViewById(R.id.container)
        var avatar: ImageView = view.findViewById(R.id.icon)


    }


    fun setOnClickChild(onClick: OnItemClickedDelegate) {
        this.onClick = onClick
    }


}

