package com.zhq.ktlearn.utils

import android.app.Activity

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 16:00
 * Description
 */
object ActivityCollector {

    private val activities = ArrayList<Activity>()
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }
    fun removeActivity(activity:Activity){
        activities.remove(activity)
    }

    fun finishAllActivity(){
        for (activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }

        activities.clear()
    }
}