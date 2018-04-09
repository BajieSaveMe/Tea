package com.fude.david.base.ext

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.Toast

/**
 * 点击事件
 */
fun View.onClick(method: (v: View) -> Unit) {
    this.setOnClickListener { method(it) }
}

/**
 * Toast
 */
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * dip转换px
 */
fun Number.dip2px(context: Context): Int {
    var scale = context.resources.displayMetrics.density
    return (this.toFloat() * scale + 0.5f).toInt()
}

/**
 * sp转换px
 */
fun Number.sp2px(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), context.resources.displayMetrics).toInt()
}