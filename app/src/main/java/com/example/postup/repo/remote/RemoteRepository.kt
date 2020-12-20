package com.example.postup.repo.remote

import com.example.postup.model.PostEntity
import com.example.postup.model.UserEntity
import com.example.postup.repo.remote.service.posts.PostService
import com.example.postup.repo.remote.service.user.UserService
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository
@Inject constructor(
    val postService: PostService,
    val userService: UserService
) {

    suspend fun fetchPosts(): Response<List<PostEntity>>{
        return postService.getPosts()
    }

    suspend fun fetchUser(userId: Int): UserEntity? {
        return userService.fetchUser(userId)
    }

    suspend fun fetchPost(postId: Int): PostEntity? {
        return postService.fetchPost(postId)
    }
}