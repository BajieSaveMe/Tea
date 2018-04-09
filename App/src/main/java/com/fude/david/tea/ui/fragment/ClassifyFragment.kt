package com.fude.david.tea.ui.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.LinearLayout
import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.base.utils.ScreenUtil
import com.fude.david.tea.R
import kotlinx.android.synthetic.main.fragment_classify.*

/**
 * 分类
 */
class ClassifyFragment : LazyFragment() {

    lateinit var tabList: ArrayList<String>

    companion object {
        fun getInstance(): ClassifyFragment {
            val fragment = ClassifyFragment()
            return fragment
        }
    }

    override fun setLayoutView(): Int {
        return R.layout.fragment_classify
    }

    override fun initView() {
        stateBar.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ScreenUtil.getStatusBarHeight(mContext!!))

        tabList = ArrayList()
        tabList.add("乌龙茶")
        tabList.add("红茶")
        tabList.add("绿茶")
        tabList.add("白茶")
        tabList.add("黑茶")
        tabList.add("黄茶")
        vtbvp.adapter = MyAdapter(activity!!.supportFragmentManager)
        vtb.setupWithViewPager(vtbvp)
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getPageTitle(position: Int): CharSequence? {
            return tabList[position]
        }

        override fun getItem(position: Int): Fragment {
            return ClassifyListFragment.getInstance(tabList[position])
        }

        override fun getCount(): Int {
            return tabList.size
        }

    }
}