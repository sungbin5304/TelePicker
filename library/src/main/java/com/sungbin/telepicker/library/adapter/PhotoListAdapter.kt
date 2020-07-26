package com.sungbin.telepicker.library.adapter

import android.app.Activity
import android.graphics.Rect
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.telepicker.library.R
import com.sungbin.telepicker.library.ui.CountCheckBox
import com.sungbin.telepicker.library.utils.ImagePath


/**
 * Created by SungBin on 2020-07-26.
 */


class PhotoListAdapter constructor
    (
    private val activity: Activity
) :
    RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    private var imageList: MutableList<Uri> = ImagePath.getAllPath(activity)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)!!
        val cbCounter = view.findViewById<CountCheckBox>(R.id.ccb_counter)!!
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_photo_thumbnail, viewGroup, false)
        )

    override fun onBindViewHolder(@NonNull viewholder: ViewHolder, position: Int) {
        val uri = imageList[position]
        viewholder.ivPhoto.setImageURI(uri)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() { //아이템 간격
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount) {
                    outRect.set(0, 0, 0, 16)
                }
            }
        })
    }

    override fun getItemCount() = imageList.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
    fun getItem(position: Int) = imageList[position]
}