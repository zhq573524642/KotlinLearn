package com.zhq.commonlib.base

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.zhq.commonlib.net.BaseResp
import com.zhq.commonlib.net.RespStateData
import com.zhq.commonlib.utils.Constants
import com.zhq.commonlib.utils.ToastUtils.showToast
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:27
 * Description
 */
open class BaseRepository {

    suspend fun <T> dealResp(
        block: suspend () -> BaseResp<T>,
        liveData: RespStateData<T>,
    ) {

        var result = BaseResp<T>()
        result.responseState = BaseResp.ResponseState.REQUEST_START
        liveData.value = result

        try {

            //---------------------//
            result = block.invoke()
            //---------------------//

            Log.d("BaseRepository", result.errorCode.toString() + "/" + result.errorMsg)
            when (result.errorCode) {
                Constants.HTTP_SUCCESS -> {
                    result.responseState = BaseResp.ResponseState.REQUEST_SUCCESS
                }
                Constants.HTTP_AUTH_INVALID -> {
                    result.responseState = BaseResp.ResponseState.REQUEST_FAILED
                    "认证过期，请重新登录！".showToast()
//                    ARouter.getInstance().build(Constants.PATH_LOGIN).navigation()
                }
                else -> {
                    result.responseState = BaseResp.ResponseState.REQUEST_FAILED
                    ("code:" + result.errorCode.toString() + " / msg:" + result.errorMsg).showToast()
                }
            }

        } catch (e: Exception) {
            Log.d("BaseRepository", "dealResp: Exception$e")
            when (e) {
                is UnknownHostException,
                is HttpException,
                is ConnectException,
                -> {
                    //网络error
                    "网络错误！".showToast()
                }
                else -> {
                    "未知错误！".showToast()
                }
            }
            result.responseState = BaseResp.ResponseState.REQUEST_ERROR
        } finally {
            liveData.value = result
        }
    }
}