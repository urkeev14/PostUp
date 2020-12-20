package com.example.postup.ui.fragment.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postup.model.PostEntity
import com.example.postup.model.UserEntity
import com.example.postup.repo.local.LocalRepository
import com.example.postup.repo.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PostDetailsViewModel
@ViewModelInject constructor(
    val repoRemote: RemoteRepository,
    val repoLocal: LocalRepository
): ViewModel(){

    var user: MutableLiveData<UserEntity?> = MutableLiveData(null)
    var post: MutableLiveData<PostEntity?> = MutableLiveData(null)

    fun loadData(userId: Int, postId: Int){
        fetchPost(postId)
        fetchUser(userId)
    }

    private fun fetchPost(postId: Int){
        CoroutineScope(IO).launch {
            post.postValue(repoRemote.fetchPost(postId))
        }
    }

    private fun fetchUser(userId: Int){
        CoroutineScope(IO).launch {
            user.postValue(repoRemote.fetchUser(userId))
        }
    }

    fun deletePost(postId: Int){
        CoroutineScope(IO).launch {
            repoLocal.deletePost(postId)
        }
    }

}