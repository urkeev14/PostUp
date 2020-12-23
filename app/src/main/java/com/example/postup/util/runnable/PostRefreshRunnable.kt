package com.example.postup.util.runnable

import com.example.postup.app.PostUp
import com.example.postup.repo.local.LocalRepositoryObserver
import com.example.postup.ui.fragment.posts.PostsViewModel
import com.example.postup.util.constants.REFRESH_IMMEDIATELY
import com.example.postup.util.constants.REFRESH_INTERVAL_STANDARD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostRefreshRunnable(
    val viewModel: PostsViewModel
) {
    var millis: Long = REFRESH_INTERVAL_STANDARD

    init {
        execute(REFRESH_INTERVAL_STANDARD)
    }

    fun execute(interval: Long = REFRESH_INTERVAL_STANDARD) {
        millis = interval

        CoroutineScope(IO).launch {
            delay(interval)
            refreshIfNeed()
            execute(REFRESH_INTERVAL_STANDARD)
        }
    }

    private fun refreshIfNeed() {

        if (refreshConditionsMet()) {
            viewModel.fetchPostsFromAPI()
        }
    }

    private fun refreshConditionsMet(): Boolean {
        val isLocalRepoModified = LocalRepositoryObserver.isCacheModified()

        return millis == REFRESH_IMMEDIATELY ||
                isLocalRepoModified ||
                (isLocalRepoModified && millis == REFRESH_INTERVAL_STANDARD)
    }

}