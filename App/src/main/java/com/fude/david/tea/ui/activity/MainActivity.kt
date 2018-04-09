package com.fude.david.tea.ui.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.fude.david.base.ext.onClick
import com.fude.david.base.ui.activity.BaseActivity
import com.fude.david.provider.common.afterLogin
import com.fude.david.provider.router.RouterPath
import com.fude.david.tea.R
import com.fude.david.tea.ui.fragment.ClassifyFragment
import com.fude.david.tea.ui.fragment.HomeFragment
import com.fude.david.tea.ui.fragment.MineFragment
import com.fude.david.tea.view.TagBottomView
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 * 主界面
 */
@Route(path = RouterPath.App.PATH_MAIN)
class MainActivity : BaseActivity() {

    lateinit var lsFragment: ArrayList<Fragment>
    lateinit var lsTagView: ArrayList<TagBottomView>
    lateinit var adapter: MyAdapter

    override fun setLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        lsTagView = ArrayList()
        lsFragment = ArrayList()
        lsFragment.add(HomeFragment.getInstance())
        lsFragment.add(ClassifyFragment.getInstance())
        lsFragment.add(MineFragment.getInstance())
        adapter = MyAdapter(supportFragmentManager)
        mVpMainPager.adapter = adapter
        mVpMainPager.offscreenPageLimit = lsFragment.size
        mVpMainPager.currentItem = 0
        initTagView()
        bottom1.onClick {
            selected(0, true)
        }
        bottom2.onClick {
            selected(1, true)
        }
        bottom3.onClick {
            selected(2, true)
        }
    }

    override fun setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null)
    }

    /**
     * 初始化TagBottom
     */
    private fun initTagView() {
        val count = llll.childCount
        for (i in 0 until count) {
            if (llll.getChildAt(i) is TagBottomView) {
                val view = llll.getChildAt(i) as TagBottomView
                lsTagView.add(view)
            }
        }
        bottom1.setCancelAnimation(true)
        bottom1.setMySelected(true)
        bottom1.isEnabled = false
    }

    private fun selected(flag: Int, cancelAnimation: Boolean) {
        if (lsTagView != null) {
            for (i in lsTagView.indices) {
                lsTagView[i].setCancelAnimation(cancelAnimation)
                lsTagView[i].setMySelected(false)
                lsTagView[i].isEnabled = true
            }
            lsTagView[flag].setCancelAnimation(cancelAnimation)
            lsTagView[flag].setMySelected(true)
            lsTagView[flag].isEnabled = false
        }
        mVpMainPager.currentItem = flag
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return lsFragment[position]
        }

        override fun getCount(): Int {
            return lsFragment.size
        }
    }
}
