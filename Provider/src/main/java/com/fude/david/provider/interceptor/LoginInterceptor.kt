package com.fude.david.provider.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

@Interceptor(priority = 10)
class LoginInterceptor : IInterceptor {
    lateinit var mContext: Context
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
    }

    override fun init(context: Context?) {
        mContext = context!!
    }
}