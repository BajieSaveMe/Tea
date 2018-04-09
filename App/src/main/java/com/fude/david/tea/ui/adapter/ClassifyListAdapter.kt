package com.fude.david.tea.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fude.david.base.ext.dip2px
import com.fude.david.base.ext.sp2px
import com.fude.david.base.utils.GlideCircleTransform
import com.fude.david.base.utils.ScreenUtil
import com.fude.david.tea.R
import com.fude.david.tea.bean.Goods
import kotlinx.android.synthetic.main.item_classify_goods.view.*
import kotlinx.android.synthetic.main.item_classify_head.view.*

class ClassifyListAdapter(private var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val options: RequestOptions by lazy {
        RequestOptions().transform(GlideCircleTransform())
    }
    private val url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523264832028&di=a1f020b72e92e2fa447fcf4b1783e296&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b6dc58bad6b5a801219c778054bb.jpg%40900w_1l_2o_100sh.jpg"

    lateinit var goodsList: List<Goods>
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            HeadViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify_head, parent, false))
        } else {
            ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify_goods, parent, false))
        }
    }

    /**
     * 赋值
     */
    fun setData(goods: List<Goods>) {
        goodsList = goods
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    override fun getItemCount(): Int {
        if (null == goodsList)
            goodsList = arrayListOf()
        return goodsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is HeadViewHolder) {
            Glide.with(mContext).load(url).into(holder.itemView.mIvClassify)
            holder.itemView.mTvClassify.text = "推荐精品"
        } else if (holder is ItemViewHolder) {
            val width = (ScreenUtil.getScreenWidth(mContext) - 80.dip2px(mContext)) / 3
            //图片宽度
            val picWidth = width - 40.dip2px(mContext)
            val height = width - 8.dip2px(mContext) + 14.sp2px(mContext)
            val layoutParams = LinearLayout.LayoutParams(width, height)
            layoutParams.gravity = CENTER
            holder.itemView.mLlGoods.layoutParams = layoutParams
            val goods = goodsList[position - 1]
            Glide.with(mContext).load(goods.showPic).apply(options).into(holder.itemView.mIvGoodsPic)
            val layoutParamsPic = LinearLayout.LayoutParams(picWidth, picWidth)
            layoutParamsPic.bottomMargin = 12.dip2px(mContext)
            holder.itemView.mIvGoodsPic.layoutParams = layoutParamsPic
            holder.itemView.mTvGoodsName.text = goods.goodsName
        }
    }

    /**
     * 头部图片展示布局
     */
    class HeadViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    /**
     * 商品展示布局
     */
    class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}