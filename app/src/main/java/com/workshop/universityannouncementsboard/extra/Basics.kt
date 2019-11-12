package com.workshop.universityannouncementsboard.extra

// fizzBuzz function that returns String that represents what should be said in the FizzBuzz game for each number between 1 and 100.
// We list all this numbers in new lines, but we replace some of them with:
// “Fizz” if number is divisible by 3
// “Buzz” if number is divisible by 5
// “FizzBuzz” if number is divisible both by 3 and 5 (by 15)
// Print elements using `console.println`
fun fizzBuzz(console: Console) {
    for (i in 1..100) {
        when {
            i % 15 == 0 -> console.println("FizzBuzz")
            i % 5 == 0 -> console.println("Buzz")
            i % 3 == 0 -> console.println("Fizz")
            else -> console.println("$i")
        }
    }
}

class Console {
    fun println(a: Any?) {
        kotlin.io.println(a)
    }
}

fun printlnWrap(any: Any?) {
    print(any)
}

// Fibonacci number that starts from 1 and 1 (fib(0) == 1, fib(1) == 1, fib(2) == 2, fib(3) == 3, fib(4) == 5, fib(5) == 8)
// https://en.wikipedia.org/wiki/Fibonacci_number
fun fib(n: Int): Int {
    if (n == 0 || n == 1) return 1

    return fib(n - 1) + fib(n - 2)
}

/**  @see [com.workshop.universityannouncementsboard.extra.fib] */
fun main() {
    println("FizzBuzz")
    fizzBuzz(Console())

    println("\nfib")
    fib(5).also { println(it) }

    println()
    val user = User(0, "marcinmoskala@gmail.com")
    println(user.id) // 0
    println(user.email) // marcinmoskala@gmail.com
    println(user) // User with id $id and email $email
}

class User(val id: Int, val email: String) {
    override fun toString(): String {
        return "User with id $id and email $email"
    }
}