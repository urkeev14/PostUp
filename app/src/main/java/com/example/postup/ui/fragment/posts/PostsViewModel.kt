package com.example.postup.ui.fragment.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.postup.repo.local.LocalRepository
import com.example.postup.repo.remote.RemoteRepository

class PostsViewModel
@ViewModelInject constructor(
    val repoLocal: LocalRepository,
    val repoRemote: RemoteRepository
) : ViewModel(){
}