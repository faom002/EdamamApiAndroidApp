package com.example.recipeapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class MyCustomRecipeViewHolder

    (context: Context) : LinearLayout(context) {

   private var label: TextView? = null
   private var image: ImageView? = null
   private var dietLabel: TextView? = null


    init {
        this.setLayoutParams(
            LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        this.weightSum = 1f
        image = ImageView(context)
        label = TextView(context)
        dietLabel = TextView(context)
        addView(image)
        addView(label)
        addView(dietLabel)
        this.setBackgroundResource(R.drawable.edit_text_border)
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        paint.color = Color.BLACK
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint)

        this.image?.setLayoutParams(
            LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.5f
            )
        )
        this.dietLabel!!.setBackgroundResource(R.drawable.edit_text_border)
        this.dietLabel?.setPadding(4, 4, 4, 4)
        this.dietLabel?.setLayoutParams(
            LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.5f
            )
        )
        this.label?.setPadding(4, 4, 4, 4)
        this.label?.setLayoutParams(
            LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.5f
            )
        )
    }


    fun setDietLabels(dietLabels: String?) {
        this.dietLabel?.setText(dietLabels)
    }


    fun setImageView(imageView: Bitmap?) {
        this.image?.setImageBitmap(imageView)
    }

    fun setLabelName(labelName: String?) {
        this.label?.setText(labelName)

    }


}