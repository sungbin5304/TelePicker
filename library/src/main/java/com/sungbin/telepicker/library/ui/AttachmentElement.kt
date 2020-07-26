package com.sungbin.telepicker.library.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sungbin.telepicker.library.R


/**
 * Created by SungBin on 2020-07-23.
 */

class AttachmentElement : LinearLayout {

    constructor(context: Context) : super(context) {
        AttachmentElement(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_attachment_element, this, false)

        val ivIcon = view.findViewById<ImageView>(R.id.iv_icon)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.AttachmentElement,
            0,
            0
        )
        val icon = typedArray.getResourceId(
            R.styleable.AttachmentElement_ae_icon,
            R.drawable.ic_baseline_photo_24
        )

        val color = typedArray.getColor(
            R.styleable.AttachmentElement_ae_color,
            ContextCompat.getColor(context,
                R.color.colorPrimary
            )
        )

        val title = typedArray.getString(R.styleable.AttachmentElement_ae_text)

        ivIcon.setImageResource(icon)
        ivIcon.backgroundTintList = ColorStateList.valueOf(color)

        tvTitle.text = title
        tvTitle.setTextColor(color)

        typedArray.recycle()

        addView(view)
        invalidate()
    }

}