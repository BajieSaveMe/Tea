package com.fude.david.tea.ui.fragment

import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.tea.R

/**
 * 我的
 */
class MineFragment : LazyFragment() {

    companion object {
        fun getInstance(): MineFragment {
            val fragment = MineFragment()
            return fragment
        }
    }

    override fun setLayoutView(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
    }
}