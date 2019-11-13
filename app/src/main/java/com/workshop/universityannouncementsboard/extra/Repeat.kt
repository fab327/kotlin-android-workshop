package com.workshop.universityannouncementsboard.extra

fun repeat(times: Int, block: () -> Unit) {
    for (i in 1..times) {
        block()
    }
}