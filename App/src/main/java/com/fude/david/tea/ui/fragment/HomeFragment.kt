package com.fude.david.tea.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.tea.R
import com.fude.david.tea.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 首页
 */
class HomeFragment : LazyFragment() {
    lateinit var adapter: HomeAdapter

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
        adapter = HomeAdapter(mContext!!)
        mRlvHome.layoutManager = LinearLayoutManager(mContext)
        mRlvHome.adapter = adapter
    }
}