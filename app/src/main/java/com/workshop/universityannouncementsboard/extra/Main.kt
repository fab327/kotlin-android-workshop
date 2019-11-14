package com.workshop.universityannouncementsboard.extra

fun main() {
    //if, else if, else

    val temp = 40
    var result: String

    if (temp < 0) {
        result = "cold"
    } else if (temp < 23) {
        //do something
        result = "warm"
    } else {
        //do something
        result = "hot"
    }.also { println(result) }

    //First vs firstOrNull
    val nums = listOf(3, 5, 7)
    val output = nums.first { it == 3 }
    println(output)


    //Spread operator
    //withIndex()
    fun printAll(vararg anys: Any) {
        for (a in anys) {
            println(a)
        }
    }

    val list1 = listOf(3, 4, 5)
    val list2 = arrayOf(6, 7, 8)

    printAll(list1, list2)
}