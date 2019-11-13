package com.workshop.universityannouncementsboard.extra

class Pizza(
    val olives: Int = 0,
    val pineapple: Int = 0,
    val tomato: Int = 0,
    val mushrooms: Int = 0,
    val ham: Int = 0,
    val cheese: Int = 0
) {
    companion object {
        fun hawaiian() = Pizza(pineapple = 1, ham = 1, cheese = 1)
    }
}

object PizzaFactory {
    fun hawaiian() = Pizza(pineapple = 1, ham = 1, cheese = 1)
}

fun main() {
    val hawaiian = Pizza.hawaiian()
    val hawaiian2 = PizzaFactory.hawaiian()
}