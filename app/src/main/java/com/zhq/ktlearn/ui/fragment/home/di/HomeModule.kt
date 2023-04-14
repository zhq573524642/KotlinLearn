package com.zhq.ktlearn.ui.fragment.home.di

import com.zhq.commonlib.net.RetrofitManager
import com.zhq.ktlearn.ui.fragment.home.api.HomeApi
import com.zhq.ktlearn.ui.fragment.home.repo.HomeRepo
import com.zhq.ktlearn.ui.fragment.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 13:31
 * Description
 */
val homeModule = module {
    single { RetrofitManager.getApiService(HomeApi::class.java) }
    single { HomeRepo(get()) }
    viewModel { HomeViewModel(get()) }
}