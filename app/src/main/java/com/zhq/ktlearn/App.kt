package com.zhq.ktlearn

import android.app.Application
import android.content.Context
import com.zhq.ktlearn.utils.SpUtils

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/11 14:43
 * Description
 */
class App : Application() {
    lateinit var mContext: Context
    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        SpUtils.init(this)
    }
}