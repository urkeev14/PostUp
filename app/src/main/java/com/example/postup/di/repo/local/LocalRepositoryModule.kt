package com.example.postup.di.repo.local

import androidx.room.Room
import com.example.postup.app.PostUp
import com.example.postup.repo.local.LocalRepository
import com.example.postup.util.constants.LOCAL_REPOSITORY_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class LocalRepositoryModule {

    @Singleton
    @Provides
    fun provideScreenBindgerDatabase(): LocalRepository {
        return Room.databaseBuilder(
            PostUp.context,
            LocalRepository::class.java,
            LOCAL_REPOSITORY_NAME
        ).fallbackToDestructiveMigration().build()
    }

}