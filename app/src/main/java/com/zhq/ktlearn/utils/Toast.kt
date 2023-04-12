package com.zhq.ktlearn.utils

import android.content.Context
import android.widget.Toast
import java.time.Duration

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/12 15:46
 * Description
 */
fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}