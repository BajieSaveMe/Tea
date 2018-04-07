package com.fude.david.tea.ui.activity

import com.fude.david.base.ui.activity.BaseActivity
import com.fude.david.tea.R
import com.jaeger.library.StatusBarUtil
import io.reactivex.Observable
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

/**
 * 启动页面
 */
class SplashActivity : BaseActivity() {
    override fun setLayoutView(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe {
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun setStatusBar() {
        StatusBarUtil.setTranslucent(this)
    }
}
