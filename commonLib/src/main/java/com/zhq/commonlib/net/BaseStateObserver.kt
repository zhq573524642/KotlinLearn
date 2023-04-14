package com.zhq.commonlib.net

import android.util.Log
import androidx.lifecycle.Observer

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:26
 * Description
 */
open class BaseStateObserver <T>(var t: Boolean?) : Observer<BaseResp<T>> {

    override fun onChanged(t: BaseResp<T>) {

        when (t.responseState) {
            BaseResp.ResponseState.REQUEST_START -> {
                Log.d("BaseStateObserver", "Observer: start")
                getRespDataStart()
            }
            BaseResp.ResponseState.REQUEST_SUCCESS -> {
                Log.d("BaseStateObserver", "Observer: success")
                if(t.data==null){
                    getRespSuccess()
                }else{
                    getRespDataSuccess(t.data!!)
                }

            }
            BaseResp.ResponseState.REQUEST_FAILED -> {
                Log.d("BaseStateObserver", "Observer: failed")
                getRespDataEnd()
            }
            BaseResp.ResponseState.REQUEST_ERROR -> {
                Log.d("BaseStateObserver", "Observer: error")
                getRespDataEnd()
            }
        }
    }

    open fun getRespDataStart() {}
    open fun getRespDataSuccess(it: T) {}
    open fun getRespSuccess() {}
    open fun getRespDataEnd() {}
}