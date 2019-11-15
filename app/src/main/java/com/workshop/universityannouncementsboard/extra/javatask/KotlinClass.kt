package com.workshop.universityannouncementsboard.extra.javatask

class KotlinClass {

    //Getting objects from java
    var person: JavaPerson = JavaPerson("", 0)

    //Nothing example - slide 95
    fun calculateUserAge(): Int = 2

    companion object {
        @JvmStatic
        fun staticFunction() {
            print("This is staticFunction")
        }
    }
}