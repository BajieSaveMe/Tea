package com.fude.david.tea.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fude.david.base.ext.dip2px
import com.fude.david.base.ui.fragment.LazyFragment
import com.fude.david.tea.R
import com.fude.david.tea.bean.Goods
import com.fude.david.tea.ui.adapter.ClassifyListAdapter
import kotlinx.android.synthetic.main.fragment_classify_list.*

class ClassifyListFragment : LazyFragment() {

    private lateinit var adapter: ClassifyListAdapter
    private lateinit var goodsList: ArrayList<Goods>

    companion object {

        private const val INTENT_KEY_CLASSIFY_ID = "classifyId"

        fun getInstance(classifyId: String): ClassifyListFragment {
            val fragment = ClassifyListFragment()
            val bundle = Bundle()
            bundle.putString(INTENT_KEY_CLASSIFY_ID, classifyId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutView(): Int {
        return R.layout.fragment_classify_list
    }

    override fun initView() {
        adapter = ClassifyListAdapter(mContext!!)
        goodsList = ArrayList()
        for (i in 0 until 12) {
            goodsList.add(Goods(goodsName = "测试$i", showPic = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523263794247&di=2f00707b0b1abe0151bc705a41af58f8&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D6ade238c064f78f0800b9afb49300a83%2F6cbb60d0f703918f31df8012543d269758eec47c.jpg"))
        }
        adapter.setData(goodsList)
        val manager = GridLayoutManager(mContext, 3)
        mlvClassify.layoutManager = manager
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) manager.spanCount else 1
            }
        }
        mlvClassify.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                super.getItemOffsets(outRect, view, parent, state)
                val position = parent!!.layoutManager.getPosition(view)
                if (position == 0)
                    return
                outRect!!.top = 20.dip2px(mContext!!)
            }
        })
        mlvClassify.adapter = adapter
    }
}