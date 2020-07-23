package com.sungbin.telepicker.library

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.lang.Exception


/**
 * Created by SungBin on 2020-07-23.
 */

class AttachmentElement constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.AttachmentElementStyle
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_attachment_element, this, false)
        val ivIcon = view.findViewById<ImageView>(R.id.iv_icon)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)

        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AttachmentElement,
            defStyleAttr,
            0
        )
        try {
            val icon = typedArray.getResourceId(
                R.styleable.AttachmentElement_ae_icon,
                R.drawable.ic_baseline_photo_24
            )
            val color = typedArray.getColor(
                R.styleable.AttachmentElement_ae_color,
                ContextCompat.getColor(context, R.color.colorPrimary)
            )
            val title = typedArray.getString(R.styleable.AttachmentElement_ae_text)

            Log.d("AAAA", title.toString())

            ivIcon.setImageResource(icon)
            ivIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN)

            tvTitle.text = title
            tvTitle.setTextColor(color)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            typedArray.recycle()
        }

        addView(view)
    }

}