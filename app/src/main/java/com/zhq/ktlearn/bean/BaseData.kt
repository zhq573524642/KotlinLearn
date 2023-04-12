package com.zhq.ktlearn.bean

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/11 11:28
 * Description
 */
open class BaseData<T> {
    var errorCode: Int = -1
    var errorMsg: String = ""
    var data: T? = null
}