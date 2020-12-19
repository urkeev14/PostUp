package com.example.postup.util.runnable

import android.os.Handler
import android.os.Looper
import com.example.postup.util.constants.REFRESH_INTERVAL_STANDARD
import java.lang.ref.WeakReference

class PostRefreshRunnable(
    val listener: WeakReference<OnPostRefreshListener>
) : Runnable {

    val handler = Handler(Looper.getMainLooper())

    fun refresh(millis: Long = REFRESH_INTERVAL_STANDARD) {
        handler.postDelayed({
            run()
        }, millis)
    }

    override fun run() {
        listener.get()?.onRefresh()
    }
}