package com.fude.david.base.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.fude.david.base.BuildConfig

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        //ARouter初始化
        if (BuildConfig.IS_DEBUG) {
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    /*
        全局伴生对象
     */
    companion object {
        lateinit var context: Context
    }
}