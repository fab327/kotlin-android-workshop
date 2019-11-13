package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Success
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepository

// TODO: Write logic for MainPresenter to pass all tests in MainPresenterTest. Use cases:
// * When onCreate, loads and displays announcements
// * Refresh is displayed during onCreate loading
// * When repository returns error, it is shown on view
// * When different data are served after refresh, new data are displayed
// * During refresh, swipeRefresh is displayed and loading is not

class MainPresenter(
        private val view: MainView,
        private val announcementsRepository: AnnouncementsRepository
) {

    fun onCreate() {
        view.loading = true
        loadData { view.loading = false }
    }

    fun onRefresh() {
        view.swipeRefresh = true
        loadData { view.swipeRefresh = false }
    }

    private fun loadData(finishingBlock: () -> Unit) {
        announcementsRepository.getAnnouncements { response ->
            when (response) {
                is Success -> view.showAnnouncements(response.value)
                is ErrorResponse -> view.showError(response.error)
            }
            finishingBlock()
        }
    }
}
