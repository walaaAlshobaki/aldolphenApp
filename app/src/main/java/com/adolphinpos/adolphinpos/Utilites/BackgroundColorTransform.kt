package com.adolphinpos.adolphinpos.Utilites

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.squareup.picasso.Transformation

 class BackgroundColorTransform(@param:ColorInt @field:ColorInt internal var mFillColor: Int) :
    Transformation {

    override fun transform(bitmap: Bitmap): Bitmap {
        // Create another bitmap that will hold the results of the filter.
        val width = bitmap.width
        val height = bitmap.height
        val out = Bitmap.createBitmap(width, height, bitmap.config)
        val canvas = Canvas(out)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawColor(mFillColor)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        bitmap.recycle()
        return out
    }

    override fun key(): String {
        return "BackgroundColorTransform:$mFillColor"
    }

}