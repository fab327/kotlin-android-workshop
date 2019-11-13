package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.AnnouncementsList
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Response
import com.workshop.universityannouncementsboard.model.Success
import com.workshop.universityannouncementsboard.util.uiThread
import kotlin.concurrent.thread
import kotlin.random.Random

class AnnouncementsRepositoryImpl(val studentsRepository: StudentsRepository) : AnnouncementsRepository {

    override fun getAnnouncements(): Response<List<Announcement>, Throwable> {
        Thread.sleep(2000) // Nobody trust university system that works too fast
        if (Random.nextBoolean()) return ErrorResponse(Error("Random error")) // Nobody trust university system that is fully reliable
        val announcements = AnnouncementsList.getAnnouncements(
                passingStudentsListText = makePassingStudentsListText(),
                bestStudentsListText = makeBestStudentsList()
        )
        return Success(announcements)
    }

    override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
        thread {
            val resp = getAnnouncements()
            uiThread {
                callback(resp)
            }
        }
    }

    /*
     * Displays a list of students who have got more than 15 points in the semester and a result of
     * at least 50, in alphabetical order (surname then name), in the format: “{name} {surname}, {result}”
     */
    fun makePassingStudentsListText(): String = studentsRepository.getStudents()
            .filter { it.pointsInSemester > 15 && it.result >= 50 }
            .sortedWith(compareBy({ it.surname }, { it.name }))
            .joinToString(separator = "\n") { "${it.name}, ${it.surname}, ${it.result}" }

    /*
     * Displays the best 10 students so they can get an internship. Comparing students by result
     * (higher is better). To get an internship, students need to have got at least 30 points
     * in the semester and a result of at least 80. The best student gets $5000, the next 3 get
     * $3000 and the next 6 get $1000. Display students in alphabetical order (compare first surname
     * then name) in the format “{name} {surname}, ${internship size}”
     */
    fun makeBestStudentsList(): String = studentsRepository.getStudents()
            .filter { it.pointsInSemester >= 30 && it.result >= 80 }
            .sortedByDescending { it.result }
            .take(10)
            .zip(listOf(5000) + List(3) { 3000 } + List(6) { 1000 })
            .sortedWith(compareBy({ it.first.surname }, { it.first.name }))
            .joinToString(separator = "\n") { (student, internship) -> "${student.name} ${student.surname}, $${internship}" }
}