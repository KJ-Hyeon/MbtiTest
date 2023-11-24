package com.example.mbtitest

import android.util.Log
import kotlin.math.sign
import kotlin.math.sqrt

fun main(args: Array<String>) {

    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0
        var count : Int = 0
        for (i in left..right) {
            for (j in 1..i) {
                if (i % j == 0) count++
            }
            if (count % 2 == 0) answer += i else answer -= i
            count = 0
        }
        return answer
    }


    val array: IntArray = intArrayOf(1,2,3,4)
    val array2: IntArray = intArrayOf(-3,-1,0,2)
//    val signs: BooleanArray = booleanArrayOf(true,false,true)
    println(solution(13, 17))
}

// a[0]*b[0] + a[1]*b[1] + ... + a[n-1]*b[n-1]