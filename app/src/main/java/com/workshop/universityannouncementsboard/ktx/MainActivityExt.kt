@file:Suppress("PackageDirectoryMismatch")

package kotlinx.android.synthetic.main.activity_main

import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ProgressBar
import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.presentation.MainActivity

val MainActivity.progressView: ProgressBar
    get() = findViewById(R.id.progressView)

val MainActivity.swipeRefreshView: SwipeRefreshLayout
    get() = findViewById(R.id.swipeRefreshView)