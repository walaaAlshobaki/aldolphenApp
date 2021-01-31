package com.adolphinpos.adolphinpos.Adapters


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel


class RecyclerViewAdapter(private val mModelList: List<PoliicyModel.Data>?,val context:Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.permission_item_cell, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = mModelList!![position]
        holder.permissionsText.setText(model.name)
        if (model.isSelected){
            holder.permissionsText.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
            holder.permissionsText.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.permissionsText.setBackgroundColor(context.resources.getColor(R.color.border))
            holder.permissionsText.setTextColor(context.resources.getColor(R.color.appMainColor))

        }
//        holder.permissionsText.setBackgroundColor(
//            if (model.isSelected) context.resources.getColor(R.color.appMainColor) else context.resources.getColor(R.color.border))
//        holder.permissionsText.setTextColor(
//            if (model.isSelected) context.resources.getColor(R.color.white) else context.resources.getColor(R.color.appMainColor))
        holder.permissionsText.setOnClickListener {
            model.isSelected=!model.isSelected!!
            common.userPrermtion.add(model.id!!)
            if (model.isSelected){

                holder.permissionsText.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                holder.permissionsText.setTextColor(context.resources.getColor(R.color.white))
            }else{
                holder.permissionsText.setBackgroundColor(context.resources.getColor(R.color.border))
                holder.permissionsText.setTextColor(context.resources.getColor(R.color.appMainColor))

            }
//            holder.view.setBackgroundColor(if (model.isSelected!!) Color.CYAN else Color.WHITE)
        }

    }

    override fun getItemCount(): Int {
        return mModelList?.size ?: 0
    }

    inner class MyViewHolder  constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
         val view: View
        val permissionsText: TextView

        init {
            view = itemView
            permissionsText = itemView.findViewById(R.id.permissionsText)
        }
    }
}