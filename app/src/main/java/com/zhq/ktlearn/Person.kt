package com.zhq.ktlearn

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 14:41
 * Description
 */
open class Person(val name: String, val age: Int) {

    constructor(width: Long, height: Long) : this("", 0) {

    }

    constructor() : this(0L, 0L) {

    }
}