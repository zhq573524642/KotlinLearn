package com.zhq.ktlearn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.zhq.ktlearn.R
import com.zhq.ktlearn.bean.DataBean

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 16:35
 * Description
 */
class ListViewAdapter(context: Context, val layoutResId: Int, data: List<DataBean>) :
    ArrayAdapter<DataBean>(context, layoutResId, data) {

    inner class ViewHolder(val ivIcon: ImageView, val tvName: TextView, val tvDesc: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layoutResId, null)
            val ivIcon: ImageView = view.findViewById(R.id.iv_icon)
            val tvName: TextView = view.findViewById(R.id.tv_name)
            val tvDesc: TextView = view.findViewById(R.id.tv_desc)
            viewHolder = ViewHolder(ivIcon, tvName, tvDesc)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val item = getItem(position)
        if (item != null) {
            viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher)
            viewHolder.tvName.text = item.name
            viewHolder.tvDesc.text = item.desc
        }

        return view
    }

}