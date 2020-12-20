package com.example.postup.repo.remote.service.user

import com.example.postup.model.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{userId}")
    suspend fun fetchUser(@Path("userId") userId: Int): UserEntity
}