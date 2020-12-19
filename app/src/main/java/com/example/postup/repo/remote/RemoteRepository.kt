package com.example.postup.repo.remote

import com.example.postup.repo.remote.service.posts.PostService
import javax.inject.Inject

class RemoteRepository
@Inject constructor(
    val postService: PostService
) {
}