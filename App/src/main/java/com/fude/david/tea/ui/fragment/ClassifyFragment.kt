package com.fude.david.tea.ui.fragment

import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.tea.R

/**
 * 分类
 */
class ClassifyFragment : LazyFragment() {

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
    }
}