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
import kotlinx.android.synthetic.main.item_guess_goods.view.*

/**
 * 首页猜你喜欢
 */
class HomeGuessAdapter(private val mContext: Context, private var mGoodsList: ArrayList<Goods>) : RecyclerView.Adapter<HomeGuessAdapter.GuessViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GuessViewHolder {
        return GuessViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_guess_goods, null))
    }

    override fun getItemCount(): Int {
        if (null == mGoodsList)
            mGoodsList = ArrayList()
        return mGoodsList.size
    }

    override fun onBindViewHolder(holder: GuessViewHolder?, position: Int) {
        val goods = mGoodsList[position]
        val width = (ScreenUtil.getScreenWidth(mContext) - 30.dip2px(mContext)) / 2
        val layoutParams = LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT)
        val lpPic = LinearLayout.LayoutParams(width, width)
        holder!!.itemView.mLlGuess.layoutParams = layoutParams
        holder.itemView.mIvGoodsPic.layoutParams = lpPic
        Glide.with(mContext).load(goods.showPic).into(holder.itemView.mIvGoodsPic)
        holder.itemView.mTvGoodsName.text = goods.goodsName
    }

    class GuessViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}