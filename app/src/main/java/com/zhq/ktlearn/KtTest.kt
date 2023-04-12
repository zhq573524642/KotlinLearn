package com.zhq.ktlearn

import kotlinx.coroutines.*
import kotlin.math.log

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 14:33
 * Description
 */

fun main() {
    runBlocking(Dispatchers.Default) {
        val start = System.currentTimeMillis()
        val r1 = async {
            delay(1000)
            100 * 100
        }.await()
        val r2 = async {
            delay(2000)
            100 * 100
        }.await()
        val r3 = async {
            delay(500)
            100 * 100
        }.await()
        val r4 = async {
            100 * 100
        }.await()
        val r5 = withContext(Dispatchers.Default) {
            66 * 66
        }
        println("r1+r2+r3+r4=${r1 + r2 + r3 + r4}")
        println("r5结果=${r5}")
        val end = System.currentTimeMillis()
        println(end - start)
    }

}







