package com.zhq.ktlearn

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 14:33
 * Description
 */
fun main() {

    val s: String = "啦啦啦"
    val list = listOf("111", "222", "333")
    val list2 = mutableListOf<Int>(1, 2, 3)

    for (s in list) {

    }

    for (i in 0..10 step 2) {

    }

    for (i in 1 until 10 step 2) {

    }

    for (i in 10 downTo 0 step 3) {

    }

    val maxStr = list.maxByOrNull { s: String -> s.length }

    list.filter { it.length > 1 }
        .map { it.uppercase() }
    val person = Person()
    val student = Student()
    student.eat("吃好吃的")
    student.study()
    student.classNum = 102
    student.grade = 2
    println(student.toString())

    val result = with(student) {
        grade = 1
        classNum = 102
    }

    println("with函数返回值：$result")
    val result1 = student.run {
        grade = 222
        666
    }

    val result2 = student.apply {
        grade = 22
    }

    println("apply函数返回值：${result2 == student}")

    println("abcdefg".capitalize())

    fun addNum(i1: Int, i2: Int): Int {
        return i1 + i2
    }

    fun num1AndNum2(num1: Int, num2: Int, operate: (Int, Int) -> Int): Int {
        return operate(num1, num2)
    }

    val num1AndNum2 = num1AndNum2(20, 80, ::addNum)
    val num1AndNum21 = num1AndNum2(20, 40) { n1, n2 ->
        n1 + n2
    }
    s beginWith "sss"
    println("====$num1AndNum2")

    val oneClass = OneClass()
    oneClass.oneMethod("hhh")

}

class OneClass {
    fun <T> oneMethod(param: T): T {
        return param
    }
}