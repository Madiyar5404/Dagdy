package com.kz.dagdy.network.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager (context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    companion object {
        var isPageable = false
    }

    private var enabled = true
    private var childId = 0

    fun setChildId(childId: Int) {
        this.childId = childId
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        setPagingEnabled(isPageable)
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (enabled) {
            super.onTouchEvent(event)
        } else {
            false
        }
    }

    fun setPagingEnabled(enabled: Boolean) {
        this.enabled = enabled
    }
}