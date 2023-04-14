package com.zhq.commonlib.net

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:23
 * Description
 */
class BaseResp<T> {
    var errorCode: Int = -1
    var errorMsg: String = ""
    var data: T? = null
    var responseState: ResponseState? = null
    enum class ResponseState {
        REQUEST_START,
        REQUEST_SUCCESS,
        REQUEST_FAILED,
        REQUEST_ERROR
    }
}