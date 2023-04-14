package com.zhq.ktlearn

import android.app.Application
import android.content.Context
import com.zhq.commonlib.utils.ToastUtils
import com.zhq.ktlearn.ui.fragment.home.di.homeModule
import com.zhq.ktlearn.utils.SpUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/11 14:43
 * Description
 */
class App : Application() {
    private val modules = mutableListOf(homeModule)
    lateinit var mContext: Context
    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        initKoin()
        SpUtils.init(this)
        ToastUtils.init(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(modules)
        }
    }
}