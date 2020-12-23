package com.example.postup.ui.fragment.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postup.model.PostEntity
import com.example.postup.repo.local.LocalRepository
import com.example.postup.repo.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostsViewModel
@ViewModelInject constructor(
    val repoLocal: LocalRepository,
    val repoRemote: RemoteRepository
) : ViewModel() {

    val list: MutableLiveData<List<PostEntity>?> = MutableLiveData(null)

    /**
     * Tries to load cached posts from local database if they exist.
     * If they do not exist, we load them from REST API.
     */
    fun loadPosts() {
        CoroutineScope(IO).launch {
            repoLocal.getCachedPosts().also { cachedPosts ->
                if (cachedPosts.isNullOrEmpty()) {
                    fetchPostsFromAPI()
                } else {
                    list.postValue(cachedPosts)
                }
            }
        }
    }

    /**
     * Fetches new posts from REST API and caches them in local repository
     */
    fun fetchPostsFromAPI() {
        CoroutineScope(IO).launch {
            deleteCachedPosts().join()
            repoRemote.fetchPosts().also { response ->
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    val fetchedPosts = response.body()
                    list.postValue(fetchedPosts)
                    cachePosts(fetchedPosts!!)
                }
            }
        }
    }

    fun deleteCachedPosts() : Job{
        return CoroutineScope(IO).launch {
            repoLocal.deleteAllPosts()
        }
    }

    private fun cachePosts(list: List<PostEntity>) {
        CoroutineScope(IO).launch {
            repoLocal.cachePosts(list)
        }
    }

    fun loadCachedPosts() {
        CoroutineScope(IO).launch {
            list.postValue(repoLocal.getCachedPosts())
        }
    }
}