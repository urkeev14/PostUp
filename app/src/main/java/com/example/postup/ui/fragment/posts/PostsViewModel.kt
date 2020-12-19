package com.example.postup.ui.fragment.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postup.model.PostEntity
import com.example.postup.repo.local.LocalRepository
import com.example.postup.repo.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PostsViewModel
@ViewModelInject constructor(
    val repoLocal: LocalRepository,
    val repoRemote: RemoteRepository
) : ViewModel() {

    val list: MutableLiveData<List<PostEntity>?> = MutableLiveData(null)

    fun getPosts() {
        CoroutineScope(IO).launch {
            repoRemote.getPosts().also { response ->
                if (response.isSuccessful)
                    list.postValue(response.body())
            }
        }
    }
}