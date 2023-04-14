package com.zhq.ktlearn.ui.activity

import com.zhq.commonlib.base.BaseActivity
import com.zhq.ktlearn.MainActivity
import com.zhq.ktlearn.R
import com.zhq.ktlearn.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        startActivity<MainActivity>(this)
        startActivityForParams<MainActivity>(this){
            putExtra("param1",123)
            putExtra("param2","111")
        }
    }
}