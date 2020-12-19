package com.example.postup.repo.remote.service.posts

import com.example.postup.model.PostEntity
import retrofit2.Response
import javax.inject.Inject

class PostService
@Inject constructor(
    val api: PostApi
){
    suspend fun getPosts(): Response<List<PostEntity>>{
        return api.getPosts()
    }
}