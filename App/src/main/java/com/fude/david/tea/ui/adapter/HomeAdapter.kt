package com.fude.david.tea.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fude.david.base.ext.dip2px
import com.fude.david.base.utils.ScreenUtil
import com.fude.david.tea.R
import com.fude.david.tea.bean.Goods
import com.fude.david.tea.common.GlideImageLoader
import kotlinx.android.synthetic.main.item_home_activity.view.*
import kotlinx.android.synthetic.main.item_home_banner.view.*
import kotlinx.android.synthetic.main.item_home_guess.view.*
import kotlinx.android.synthetic.main.item_home_new.view.*
import kotlinx.android.synthetic.main.item_home_shop.view.*
import org.jetbrains.anko.toast

class HomeAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523290253856&di=3fb1155c436c60b31229812a6b84e7c2&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019ec8578f03a20000012e7e6e27c1.jpg"
    val images: List<String> = arrayListOf(url, url, url, url, url, url)
    val newGoodsList: ArrayList<Goods> = arrayListOf(Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url))
    val guessGoodsList: ArrayList<Goods> = arrayListOf(Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url), Goods(goodsName = "新商品1", showPic = url))

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> BannerViewHolder(View.inflate(mContext, R.layout.item_home_banner, null))
            1 -> ShopViewHolder(View.inflate(mContext, R.layout.item_home_shop, null))
            2 -> NewHolder(View.inflate(mContext, R.layout.item_home_new, null))
            3 -> ActivityHolder(View.inflate(mContext, R.layout.item_home_activity, null))
            else -> GuessLikeHolder(View.inflate(mContext, R.layout.item_home_guess, null))
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val layoutParams = RecyclerView.LayoutParams(ScreenUtil.getScreenWidth(mContext), RecyclerView.LayoutParams.WRAP_CONTENT)
        when (position) {
            0 -> if (holder is BannerViewHolder) {
                holder.itemView.mHomeBanner.setImageLoader(GlideImageLoader())
                        .setImages(images)
                        //设置自动轮播，默认为true
                        .isAutoPlay(true)
                        //设置轮播时间
                        .setDelayTime(3000)
                        .setOnBannerListener {
                            mContext.toast("点击了第${it}个")
                        }.start()

            }
            1 -> if (holder is ShopViewHolder) {
                holder.itemView.mLlShop.layoutParams = layoutParams
            }
            2 -> if (holder is NewHolder) {
                holder.itemView.mLlNew.layoutParams = layoutParams
                holder.itemView.mRlvNew.layoutManager = LinearLayoutManager(mContext, HORIZONTAL, false)
                holder.itemView.mRlvNew.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                        super.getItemOffsets(outRect, view, parent, state)
                        outRect!!.left = 10.dip2px(mContext)
                    }
                })
                holder.itemView.mRlvNew.adapter = HomeNewAdapter(mContext, newGoodsList)
            }
            3 -> if (holder is ActivityHolder) {
                holder.itemView.mLlActivity.layoutParams = layoutParams
            }
            4 -> if (holder is GuessLikeHolder) {
                holder.itemView.mLlGuess.layoutParams = layoutParams
                holder.itemView.mRlvGuess.layoutManager = GridLayoutManager(mContext, 2)
                holder.itemView.mRlvGuess.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val position = parent!!.getChildAdapterPosition(view)
                        outRect!!.left = 10.dip2px(mContext)
                        if (position % 2 == 0) {
                            outRect.right = 0
                        } else {
                            outRect.right = 10.dip2px(mContext)
                        }
                        outRect.bottom = 10.dip2px(mContext)
                    }
                })
                holder.itemView.mRlvGuess.adapter = HomeGuessAdapter(mContext, guessGoodsList)
            }
        }
    }


    //banner
    class BannerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    //商品商品推荐
    class ShopViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    //新品首发
    class NewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    //活动商品
    class ActivityHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    //猜你喜欢
    class GuessLikeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}