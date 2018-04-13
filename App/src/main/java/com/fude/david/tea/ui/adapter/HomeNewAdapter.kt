package com.fude.david.tea.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.fude.david.base.ext.dip2px
import com.fude.david.base.utils.ScreenUtil
import com.fude.david.tea.R
import com.fude.david.tea.bean.Goods
import kotlinx.android.synthetic.main.item_new_goods.view.*
import kotlinx.android.synthetic.main.item_new_show_all.view.*

class HomeNewAdapter(private val mContext: Context, private var goodsList: ArrayList<Goods>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> GoodsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_new_goods, null))
            1 -> ShowAllHolder(LayoutInflater.from(mContext).inflate(R.layout.item_new_show_all, null))
            else -> GoodsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_new_goods, null))
        }
    }

    override fun getItemCount(): Int {
        if (goodsList == null)
            goodsList = ArrayList()
        return goodsList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val width = (ScreenUtil.getScreenWidth(mContext) - 40.dip2px(mContext)) / 2
        if (position == goodsList.size) {
            if (holder is ShowAllHolder) {
                val layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, width)
                holder.itemView.mLlShowAll.layoutParams = layoutParams
            }
        } else {
            val goods = goodsList[position]
            if (holder is GoodsHolder) {
                Glide.with(mContext).load(goods.showPic).into(holder.itemView.mIvGoodsPic)
                val layoutParams = LinearLayout.LayoutParams(width, width)
                holder.itemView.mIvGoodsPic.layoutParams = layoutParams
                holder.itemView.mTvGoodsName.text = goods.goodsName
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == goodsList.size) 1 else 0
    }

    //商品Holder
    class GoodsHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    //查看全部
    class ShowAllHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}