package com.zhq.ktlearn.ui.fragment.home.repo

import com.zhq.commonlib.base.BaseRepository
import com.zhq.commonlib.net.RespStateData
import com.zhq.ktlearn.ui.fragment.home.api.HomeApi
import com.zhq.ktlearn.ui.fragment.home.bean.Article

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:35
 * Description
 */
class HomeRepo(private val api: HomeApi) :BaseRepository(){

    suspend fun getArticle(currentPage: Int, data: RespStateData<Article>) = dealResp(
        { api.getArticleList(currentPage, 10) }, data
    )
}