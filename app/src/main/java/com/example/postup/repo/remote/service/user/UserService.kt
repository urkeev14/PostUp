package com.example.postup.repo.remote.service.user

import com.example.postup.model.UserEntity
import javax.inject.Inject

class UserService
@Inject constructor(
    val api: UserApi
){
    suspend fun fetchUser(userId: Int): UserEntity?{
        return api.fetchUser(userId)
    }
}