package com.zhq.ktlearn.ui.fragment.home.api

import com.zhq.commonlib.net.BaseResp
import com.zhq.ktlearn.ui.fragment.home.bean.Article
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 13:32
 * Description
 */
interface HomeApi {

    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int, @Query("page_size") page_size: Int): BaseResp<Article>
}