package com.adolphinpos.adolphinpos.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Utilites.BackgroundColorTransform
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.squareup.picasso.Picasso

class CountryAdapter (
    var itemsData: ArrayList<CountryModel.Data>,
    var context: Context
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    var isGrid = false
    var thisClass = this
    private val TYPE_EPMPY = 0
    private val TYPE_ITEM = 1
    private val TYPE_LOADER = 2


    override fun getItemViewType(position: Int): Int {

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
    var onClick: OnItemClickedDelegate? = null

    //make interface like this
    interface OnItemClickedDelegate {
        fun setOnClickCountry(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        Log.d("WWWWWWWWWWW", itemsData[1].name)

        when (viewType) {

            TYPE_EPMPY -> {

                val vv = inflater.inflate(R.layout.empty_cell, parent, false)

                val Width = parent.measuredWidth
                val Height = parent.measuredHeight

                vv.minimumWidth = Width
                vv.minimumHeight = Height



                vv.layoutParams = RecyclerView.LayoutParams(Width, Height)

                return EmptyHolder(vv)
            }

            TYPE_LOADER -> {

                val vv = inflater.inflate(R.layout.loading_cell, parent, false)

                val Width = parent.measuredWidth
                val Height = parent.measuredHeight

                vv.minimumWidth = Width
                vv.minimumHeight = Height

                vv.layoutParams = RecyclerView.LayoutParams(Width, Height)

                return LoaderHolder(vv)
            }


            else -> {

                val vv = inflater.inflate(R.layout.cell_country_item, parent, false)

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
        Log.d("RRRRRRRRRRRRRRRRRRRRRRR", itemsData.size.toString())

        return itemsData.size
    }


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {

            when (holder.itemViewType) {


                TYPE_EPMPY -> {

                    val emptyHolder = holder as EmptyHolder
                    emptyHolder.txtMessage.text = context.resources.getString(R.string.noresult)
                }
                TYPE_ITEM -> {
                    Log.d("RRRRRRRRRRRRRRRRRRRRRRR",itemsData.get(position).name)
                    val itemCat = itemsData.get(position)
                    val itemHolder = holder as ItemHolder
                    itemHolder.container.setOnClickListener {
                        onClick!!.setOnClickCountry(
                            position
                        )
                    }

                    itemHolder.title_txt.text = itemCat.name
//                    itemHolder!!.category_txt.text = itemCat.category
                    Picasso.get()
                        .load(itemCat.flag)
                        .error(R.drawable.ca)
                        .transform(BackgroundColorTransform(ContextCompat.getColor(context,R.color.layer)))
                        .into(itemHolder.cover_img)
















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

        var title_txt: TextView = view.findViewById(R.id.title)
        var cover_img: ImageView = view.findViewById(R.id.cover)
        var container: LinearLayout = view.findViewById(R.id.container)

    }


    fun setOnClickCountry(onClick: OnItemClickedDelegate) {
        this.onClick = onClick
    }


}
