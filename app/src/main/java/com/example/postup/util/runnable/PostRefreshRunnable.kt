package com.example.postup.util.runnable

import android.os.Handler
import android.os.Looper
import com.example.postup.repo.local.LocalRepositoryObserver
import com.example.postup.util.constants.REFRESH_IMMEDIATELY
import com.example.postup.util.constants.REFRESH_INTERVAL_STANDARD
import java.lang.ref.WeakReference

class PostRefreshRunnable(
    val listener: WeakReference<OnPostRefreshListener>
) : Runnable {

    val handler = Handler(Looper.getMainLooper())

    fun refresh(millis: Long = REFRESH_INTERVAL_STANDARD) {
        val isLocalRepoModified = LocalRepositoryObserver._isModified.value!!
        handler.postDelayed({
            if (millis == REFRESH_IMMEDIATELY ||
                isLocalRepoModified ||
                (isLocalRepoModified && millis == REFRESH_INTERVAL_STANDARD)
            ) {
                run()
            }
        }, millis)
    }

    override fun run() {
        listener.get()?.onRefresh()
    }
}