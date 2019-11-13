package com.workshop.universityannouncementsboard.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepositoryImpl
import com.workshop.universityannouncementsboard.repositiories.StudentsRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), MainView {

    // override var loading: Boolean by bindToVisibility(R.id.progressView)
    override var loading: Boolean by Delegates.observable(false) { _, _, newValue ->
        progressView.visibility = if (newValue) View.VISIBLE else View.GONE
    }

    // override var swipeRefresh: Boolean by bindToSwipeRefresh(R.id.swipeRefreshView)
    override var swipeRefresh: Boolean by Delegates.observable(false) { _, _, newValue ->
        swipeRefreshView.isRefreshing = newValue
    }

    private val studentsRepository by lazy { StudentsRepositoryImpl() }
    private val announcementsRepository by lazy { AnnouncementsRepositoryImpl(studentsRepository) }
    private val presenter by lazy { MainPresenter(this, announcementsRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onActivityCreated()
    }

    fun onActivityCreated() {
        listView.layoutManager = LinearLayoutManager(this)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        presenter.onCreate()
    }

    override fun showAnnouncements(announcements: List<Announcement>) {
        val titleItems = listOf(TitleItemAdapter("Announcements"))
        val announcementsItems = announcements.map { AnnouncementItemAdapter(it) }

        listView.adapter = AnnouncementsListAdapter(titleItems + announcementsItems)
    }

    override fun showError(error: Throwable) {
        val myAdapter = AnnouncementsListAdapter(listOf(TitleItemAdapter("Keep refreshing")))

        if (listView.adapter == null) {
            listView.adapter = myAdapter
        }
    }
}