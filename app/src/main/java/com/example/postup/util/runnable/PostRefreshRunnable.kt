package com.example.postup.util.runnable

import android.os.Handler
import android.os.Looper
import com.example.postup.ui.fragment.posts.PostsViewModel
import com.example.postup.util.constants.REFRESH_IMMEDIATELY
import com.example.postup.util.constants.REFRESH_INTERVAL_STANDARD
import java.lang.ref.WeakReference

class PostRefreshRunnable(
    val listener: WeakReference<OnPostRefreshListener>,
    val viewModel: PostsViewModel
) : Runnable {

    val handler = Handler(Looper.getMainLooper())

    //TODO: Set list of posts from PostsViewModel.class as
    //  as argument to this class so it can observe data from local database
    //  and accordingly refresh the list.
    fun refresh(millis: Long = REFRESH_INTERVAL_STANDARD) {
        handler.postDelayed({
            if (millis == REFRESH_IMMEDIATELY ||
                (viewModel.isCacheModified() && millis == REFRESH_INTERVAL_STANDARD)
            ) {
                run()
            }
        }, millis)
    }

    override fun run() {
        listener.get()?.onRefresh()
    }
}