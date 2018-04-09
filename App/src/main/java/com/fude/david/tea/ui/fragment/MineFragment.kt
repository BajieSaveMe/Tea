package com.fude.david.tea.ui.fragment

import android.widget.LinearLayout
import com.fude.david.base.ext.dip2px
import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.base.utils.ScreenUtil
import com.fude.david.tea.R
import kotlinx.android.synthetic.main.fragment_mine.*

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
        rLayout_head.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                242.dip2px(mContext!!) + ScreenUtil.getStatusBarHeight(mContext!!))
    }
}