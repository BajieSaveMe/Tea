package com.fude.david.usercenter.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.fude.david.base.ui.activity.BaseActivity
import com.fude.david.provider.router.RouterPath
import com.fude.david.usercenter.R

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseActivity() {
    override fun setLayoutView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
    }
}