package com.adolphinpos.adolphinpos.Adapters

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.adolphinpos.adolphinpos.R
import com.squareup.picasso.Picasso


class SlidingImagemain_Adapter(private val context: Context, private val urls: ArrayList<Int>) : PagerAdapter() {
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return urls.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slidingimagesfit_layout, view, false)!!

        val imageView = imageLayout
                .findViewById(R.id.image) as ImageView
        imageView.setImageResource(urls[position])
//        Picasso.get()
//                .load(urls[position])
//                .error(R.drawable.right_logo)
//
//                .into(imageView)


        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }


}