package com.example.postup.di.repo.remote

import com.example.postup.repo.remote.RemoteRepository
import com.example.postup.repo.remote.service.posts.PostApi
import com.example.postup.repo.remote.service.posts.PostService
import com.example.postup.repo.remote.service.user.UserApi
import com.example.postup.repo.remote.service.user.UserService
import com.example.postup.util.constants.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteRepositoryModule {


    @Singleton
    @Provides
    fun provideRemoteRepository(
        postService: PostService,
        userService: UserService
    ): RemoteRepository {
        return RemoteRepository(
            postService,
            userService
        )
    }

    @Singleton
    @Provides
    fun providePostService(
        api: PostApi
    ): PostService {
        return PostService(api)
    }

    @Singleton
    @Provides
    fun providePostApi(
        retrofit: Retrofit
    ): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(
        api: UserApi
    ): UserService {
        return UserService(api)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}