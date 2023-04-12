package com.zhq.ktlearn.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 17:56
 * Description
 */
class HomeBannerAdapter(val dataList:List<String>):BannerAdapter<String,HomeBannerAdapter.BannerViewHolder>(dataList) {

    inner class BannerViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageView:ImageView=view as ImageView
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent!!.context)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.layoutParams = params
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: String?, position: Int, size: Int) {
        val s = dataList[position]

    }
}