package com.fude.david.base.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fude.david.base.R
import com.jaeger.library.StatusBarUtil

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        setContentView(setLayoutView())
        initView()
    }

    abstract fun setLayoutView(): Int
    abstract fun initView()

    open fun setStatusBar() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.color_bg_titlelay), 0)
    }
}