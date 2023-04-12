package com.zhq.ktlearn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhq.ktlearn.R
import com.zhq.ktlearn.bean.DataBean

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 17:03
 * Description
 */
class HomeAdapter(val data: List<DataBean>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.iv_icon)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvDesc: TextView = view.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = data[position]
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher_round)
        holder.tvName.text = bean.name
        holder.tvDesc.text = bean.desc
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnRecyclerViewItemClickListener {
        fun onItemClick(position: Int)
    }


}