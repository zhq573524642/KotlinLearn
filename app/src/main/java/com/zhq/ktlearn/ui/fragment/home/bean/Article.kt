package com.zhq.ktlearn.ui.fragment.home.bean


typealias a = Article.ArticleDetail

data class Article(
    val datas: List<ArticleDetail>,
    val over: Boolean,
    val curPage: Int
) {
    data class ArticleDetail(
        val author: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        var niceDate: String,
        val shareUser: String,
        val title: String,
        val superChapterId: Int,
        val superChapterName: String,
        var collect: Boolean
    )
}

