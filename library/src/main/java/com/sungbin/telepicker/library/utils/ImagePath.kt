package com.sungbin.telepicker.library.utils

import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log


object ImagePath {

    fun getAllPath(activity: Activity): MutableList<Uri> {
        Log.d("getAllPath", "start")
        val uriExternal: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val columnIndexId: Int
        val list: MutableList<Uri> = mutableListOf()
        val projection = arrayOf(MediaStore.Images.Media._ID)
        var imageId: Long
        cursor = activity.contentResolver.query(uriExternal, projection, null, null, null)
        if (cursor != null) {
            columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                imageId = cursor.getLong(columnIndexId)
                val uriImage = Uri.withAppendedPath(uriExternal, "" + imageId)
                list.add(uriImage)
            }
            cursor.close()
        }
        Log.d("getAllPath", "end")
        return list
    }

}