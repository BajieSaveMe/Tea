package com.fude.david.base.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class NoScrollViewPager : ViewPager {
    private var isCanScroll = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return if (this.isCanScroll) super.onTouchEvent(arg0) else false
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return if (this.isCanScroll) super.onInterceptTouchEvent(arg0) else false
    }

    fun setScanScroll(isCanScroll: Boolean) {
        this.isCanScroll = isCanScroll
    }
}