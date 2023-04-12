package com.zhq.ktlearn.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 15:41
 * Description
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var mContext: Context
    lateinit var mBinding: T

    companion object {
         val TAG: String = this.javaClass.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()


    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    fun showLoading() {

    }

    fun dismissLoading() {

    }
}