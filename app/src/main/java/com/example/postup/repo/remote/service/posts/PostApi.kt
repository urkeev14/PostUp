package com.example.postup.repo.remote.service.posts

import com.example.postup.model.PostEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostEntity>>

    @GET("posts/{postId}")
    suspend fun fetchPost(@Path("postId") postId: Int): PostEntity?
}