package com.example.postup.repo.remote

import com.example.postup.model.PostEntity
import com.example.postup.repo.remote.service.posts.PostService
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository
@Inject constructor(
    val postService: PostService
) {

    suspend fun fetchPosts(): Response<List<PostEntity>>{
        return postService.getPosts()
    }
}