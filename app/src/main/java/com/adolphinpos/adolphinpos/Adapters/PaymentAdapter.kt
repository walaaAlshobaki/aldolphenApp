package com.adolphinpos.adolphinpos.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodModel

class PaymentAdapter (private val mModelList: List<PaymentMethoodModel.Data>?, val context: Context) :
    RecyclerView.Adapter<PaymentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentAdapter.MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.payment_item_cell, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: PaymentAdapter.MyViewHolder, position: Int) {
        val model = mModelList!![position]
        holder.titlec.setText(model.name)
        holder.textBack.setText(model.name)
        if (model.isSelected){
            holder.image_gradient.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
            holder.titlec.setTextColor(context.resources.getColor(R.color.white))
            holder.textBack.setTextColor(context.resources.getColor(R.color.card2))
            holder.check.setImageResource(R.drawable.ic_check2)
            holder.check.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        }else{
            holder.image_gradient.setBackgroundColor(context.resources.getColor(R.color.layer))
            holder.titlec.setTextColor(context.resources.getColor(R.color.appMainColor))
            holder.textBack.setTextColor(context.resources.getColor(R.color.appMainColoro2))
            holder.check.setImageResource(R.drawable.ic_checked)
            holder.check.setColorFilter(ContextCompat.getColor(context, R.color.appMainColor), android.graphics.PorterDuff.Mode.MULTIPLY)


        }
//        holder.permissionsText.setBackgroundColor(
//            if (model.isSelected) context.resources.getColor(R.color.appMainColor) else context.resources.getColor(R.color.border))
//        holder.permissionsText.setTextColor(
//            if (model.isSelected) context.resources.getColor(R.color.white) else context.resources.getColor(R.color.appMainColor))
        holder.image_gradient.setOnClickListener {
            model.isSelected=!model.isSelected!!
            common.userPayment.add(model.id!!)
            if (model.isSelected){
                holder.image_gradient.setBackgroundColor(context.resources.getColor(R.color.appMainColor))
                holder.titlec.setTextColor(context.resources.getColor(R.color.white))
                holder.textBack.setTextColor(context.resources.getColor(R.color.card2))
                holder.check.setImageResource(R.drawable.ic_check2)

                holder.check.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            }else{
                holder.image_gradient.setBackgroundColor(context.resources.getColor(R.color.layer))
                holder.titlec.setTextColor(context.resources.getColor(R.color.appMainColor))
                holder.textBack.setTextColor(context.resources.getColor(R.color.appMainColoro2))
                holder.check.setImageResource(R.drawable.ic_checked)
                holder.check.setColorFilter(ContextCompat.getColor(context, R.color.appMainColor), android.graphics.PorterDuff.Mode.MULTIPLY);

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
        var titlec: TextView= itemView.findViewById(R.id.txt)
        var textBack: TextView= itemView.findViewById(R.id.textBack)
        var image_gradient: ConstraintLayout= itemView.findViewById(R.id.image_gradient)
        var check: ImageView= itemView.findViewById(R.id.check)

        init {
            view = itemView
            titlec = itemView.findViewById(R.id.txt)
            textBack = itemView.findViewById(R.id.textBack)
            image_gradient = itemView.findViewById(R.id.image_gradient)
            check = itemView.findViewById(R.id.check)
        }
    }
}