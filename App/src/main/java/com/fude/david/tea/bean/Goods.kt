package com.fude.david.tea.bean

data class Goods(val goodsId: String = "",//商品ID
                 val goodsName: String = "",//商品名称
                 val shopId: String = "",//店铺ID
                 val shopName: String = "",//店铺名称
                 val classifyId: String = "",//分类ID
                 val classifyName: String = "",//分类名称
                 val price: String = "",//商品价格
                 val unit: String = "",//价格单位
                 val state: Int = 0,//商品状态（0 原价|1 打折扣|2 活动价）
                 val stateName: String = "",//状态名称「限时购、特惠、尝鲜价···」
                 val discount: String = "",//折扣（0.1-9.9）字符串
                 val sale: String = "",//减价后的价格
                 val countDown: String = "",//活动倒计时201808182359
                 val sketch: String = "",//商品简单介绍
                 val detail: String = "",//商品详细介绍
                 val showPic: String = ""//展示在列表用的商品图片
)
