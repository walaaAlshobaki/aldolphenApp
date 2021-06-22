package com.adolphinpos.adolphinpos.Adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.adolphinpos.adolphinpos.AddTaxCategory.SpinnerRow
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.ProductModel
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import kotlinx.android.synthetic.main.activity_company_profile.*


class MySpinnerAdapter(
    context: Context?, resource: Int,
    textViewResourceId: Int, objects: List<*>
) : ArrayAdapter<Any>(context!!, resource, textViewResourceId, objects) {
    private val rows: List<*>
    private val resource: Int

    internal class ViewHolder {
        var text: TextView? = null
        var icon: ImageView? = null
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        var holder = ViewHolder()
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                resource,
                parent, false
            )
            holder.text = convertView.findViewById(R.id.text) as TextView
            holder.icon = convertView.findViewById(R.id.icon) as ImageView
            convertView.setTag(holder)
        } else {
            holder = convertView.getTag() as ViewHolder
        }
        when {
            rows!![position] is CountryModel.Data -> {


                val currentRow: CountryModel.Data = rows[position] as CountryModel.Data
                holder.text!!.setText(currentRow.name)

                SvgLoader.pluck()
                    .with(context as Activity?)

                    .load(currentRow.flag, holder.icon)
            }
        }


        return convertView!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var convertView: View? = convertView
        var holder = ViewHolder()
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                resource,
                parent, false
            )
            holder.text = convertView.findViewById(R.id.text) as TextView
            holder.icon = convertView.findViewById(R.id.icon) as ImageView
            convertView.setTag(holder)
        } else {
            holder = convertView.getTag() as ViewHolder
        }

        when {
            rows!![position] is CountryModel.Data -> {


                val currentRow: CountryModel.Data = rows[position] as CountryModel.Data
                holder.text!!.setText(currentRow.name)
                holder.icon!!.visibility=View.GONE

//        SvgLoader.pluck()
//            .with(contex)
//            .setPlaceHolder(R.drawable.ca,R.drawable.ca)
//            .load(currentRow.image,  holder.icon!!)
            }
        }
        return convertView!!
    }



    init {
        rows = objects
        this.resource = resource
    }
}