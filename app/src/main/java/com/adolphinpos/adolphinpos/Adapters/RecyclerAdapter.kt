package com.adolphinpos.adolphinpos.Adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import kotlin.system.exitProcess


class RecyclerAdapter internal constructor(context: Context, data: List<CountryModel.Data>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val mData: List<CountryModel.Data>
    private val context: Context
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.cell_country_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("mData[position]",mData[position].toString())
        val animal = mData[position]
        holder.myTextView.text = animal.alpha3code
        holder.container.setOnClickListener {
            onClick!!.onSelectCountry(position)

        }
//        Picasso.get().load(animal.flag).placeholder(R.drawable.ca).into(holder.myImage)
        SvgLoader.pluck()
            .with(context as Activity?)
            .setPlaceHolder(R.drawable.empty,R.drawable.empty)
            .load(animal.flag, holder.myImage)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
         {
        var myTextView: TextView
        var myImage: ImageView
        var container: LinearLayout


        init {
            myTextView = itemView.findViewById(R.id.titlec)
            myImage = itemView.findViewById(R.id.cover)
            container = itemView.findViewById(R.id.container)


        }
    }

    // convenience method for getting data at click position


    // allows clicks events to be caught


    // parent activity will implement this method to respond to click events


    private var onClick: ItemClickListener? = null

    //make interface like this
    interface ItemClickListener {


        fun onSelectCountry(position: Int)



    }

    fun setOnClickItemCategory(onClick: ItemClickListener) {
        this.onClick = onClick
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
        this.context=context
    }
}