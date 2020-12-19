package com.example.postup.util.recyclerview.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemPostRecyclerViewDecorator constructor(
    val marginHorizontal : Int,
    val marginVertical: Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if(parent.getChildLayoutPosition(view) == 0){
            outRect.top = marginVertical
        }

        outRect.bottom = marginVertical
        outRect.left = marginHorizontal
        outRect.right = marginHorizontal

    }
}