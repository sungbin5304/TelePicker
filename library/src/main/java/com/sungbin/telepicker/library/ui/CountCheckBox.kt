package com.sungbin.telepicker.library.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sungbin.telepicker.library.R
import com.sungbin.telepicker.library.ui.data.CountCheckData
import com.sungbin.telepicker.library.utils.Animator


/**
 * Created by SungBin on 2020-07-26.
 */

class CountCheckBox : FrameLayout {

    private lateinit var flLayout: FrameLayout
    private lateinit var tvCount: TextView

    constructor(context: Context) : super(context) {
        AttachmentElement(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_round_checkbox, this, false)

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CountCheckBox,
            0,
            0
        )
        val color = typedArray.getColor(
            R.styleable.CountCheckBox_ccb_color,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )

        flLayout = view.findViewById(R.id.fl_main)
        tvCount = view.findViewById(R.id.tv_count)

        tvCount.tag = CountCheckData.maxCount + 1
        CountCheckData.setChecked(tvCount.gt(), false)
        CountCheckData.setView(tvCount.gt(), arrayListOf(flLayout, tvCount))

        flLayout.setOnClickListener {
            if (!CountCheckData.getChecked(tvCount.gt())) { //체크 실행
                Animator.fadeIn(tvCount, context)
                flLayout.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorGray)) //테두리
                tvCount.backgroundTintList = ColorStateList.valueOf(color) //텍뷰 배경
                tvCount.text = (++CountCheckData.count).toString()
            } else { //체크 해제
                Animator.fadeOut(tvCount, context) {
                    CountCheckData.getView(tvCount.gt()).removeCheck()
                    val number = textview(tvCount.gt()).getNumber()
                    Log.d("AAA", "number: $number / count: ${CountCheckData.count}")
                    when {
                        CountCheckData.count == number -> { //마지막 클릭
                        }
                        number == 1 -> { //첫번째 클릭
                            for (i in 0..CountCheckData.maxCount) {
                                try {
                                    if (getNumber(i)!! == 1) continue
                                    textview(i).text = (getNumber(i)!! - 1).toString()
                                } catch (ignored: Exception) {
                                    continue
                                }
                            }
                        }
                        else -> for (i in tvCount.gt()..CountCheckData.maxCount) { //중간 클릭
                            try {
                                if (getNumber(i)!! == 1) continue
                                textview(i).text = (getNumber(i)!! - 1).toString()
                            } catch (ignored: Exception) {
                                continue
                            }
                        }
                    }
                }
                CountCheckData.count--
            }
            CountCheckData.setToggle(tvCount.gt())
        }

        typedArray.recycle()
        addView(view)
        invalidate()
    }

    private val textview = { index: Int -> (CountCheckData.getView(index)[1] as TextView) }
    private fun TextView.gt() = this.tag as Int

    private fun TextView.getNumber(): Int? {
        return try {
            this.text.toString().toInt()
        }
        catch (e: Exception){
            null
        }

    }

    private fun getNumber(index: Int): Int? {
        return try {
            textview(index).text.toString().toInt()
        } catch (e: Exception){
            null
        }
    }

    private fun ArrayList<View>.removeCheck() {
        (this[0] as FrameLayout).backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    R.color.colorWhite
                )
            ) //테두리
        (this[1] as TextView).backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                android.R.color.transparent
            )
        ) //텍뷰 배경
        (this[1] as TextView).text = "" //글씨 제거
    }

}