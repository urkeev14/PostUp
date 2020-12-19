package com.example.postup.ui.fragment.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.postup.repo.remote.RemoteRepository

class PostDetailsViewModel
@ViewModelInject constructor(
    val repoRemote: RemoteRepository
): ViewModel(){

}