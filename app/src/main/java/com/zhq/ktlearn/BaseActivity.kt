package com.zhq.ktlearn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 15:49
 * Description
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var mBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity", javaClass.simpleName)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initView()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

}