package com.workshop.universityannouncementsboard.extra

// Check out unit tests at PlusAtTest class.
fun <T> List<T>.plusAt(index: Int, element: T): List<T> {
    require(index in 0..size)

    return take(index) + element + drop(index)

    //Other option_1
    //this.toMutableList().add(index, element)

    //Other option_2
    //val before = take(index)  -> returns the first n elements
    //val after = drop(index)   -> returns all elements except the first n elements
    //return before + element + after
}
