package com.zhq.ktlearn.ui.fragment.home.viewmodel

import com.zhq.commonlib.base.BaseViewModel
import com.zhq.commonlib.net.RespStateData
import com.zhq.ktlearn.ui.fragment.home.bean.Article
import com.zhq.ktlearn.ui.fragment.home.repo.HomeRepo

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:36
 * Description
 */
class HomeViewModel(private val repo: HomeRepo):BaseViewModel() {

    var article = RespStateData<Article>()

    fun getArticle(currentPage: Int) = launch {
        repo.getArticle(currentPage, article)
    }
}