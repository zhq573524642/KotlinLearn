package com.zhq.commonlib.base

import android.content.Context
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhq.commonlib.utils.LoadingUtil

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 15:49
 * Description
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    val TAG: String = this.javaClass.simpleName.toString()
    lateinit var mBinding: T
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initView()
    }

    private fun setGrayTheme() {
        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0F)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }

    inline fun <reified T> startActivity(context: Context) {
        val intent = Intent(context, T::class.java)
        context.startActivity(intent)
    }

    inline fun <reified T> startActivityForParams(context: Context, block: Intent.() -> Unit) {
        val intent = Intent(context, T::class.java)
        intent.block()
        context.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    fun showLoading() {
        LoadingUtil.showLoadingDialog(mContext, true)
    }

    fun dismissLoading() {
        LoadingUtil.dismissLoadingDialog()
    }

}