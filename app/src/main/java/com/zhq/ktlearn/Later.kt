package com.zhq.ktlearn

import kotlin.reflect.KProperty

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/10 10:59
 * Description
 */

fun <T> later(block: () -> T) = Later(block)

class Later<T>(val block: () -> T) {
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}