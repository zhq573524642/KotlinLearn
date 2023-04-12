package com.zhq.ktlearn

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 14:42
 * Description
 */
class Student(val sex: Int, name: String, age: Int) : Person(name, age), Skill {

    var grade: Int = 0
    var classNum: Int = 1
    override fun study() {
        println("实现了study")
    }

    override fun eat(food: String) {
        println("实现了eat:$food")
    }

    override fun play() {
        super.play()
    }

    constructor(sex: Int, name: String) : this(0, "", 0) {

    }

    constructor() : this(0, "hhh", 0) {

    }
}