package com.fude.david.tea.ui.fragment

import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.tea.R

/**
 * 首页
 */
class HomeFragment : LazyFragment() {

    companion object {
        fun getInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

    override fun setLayoutView(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
    }
}