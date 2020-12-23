package com.example.postup.repo.local

import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postup.model.PostEntity
import com.example.postup.repo.local.dao.PostDao

@Database(entities = [PostEntity::class], version = 5, exportSchema = false)
abstract class LocalRepository : RoomDatabase(){

    abstract fun postDao(): PostDao

    suspend fun getCachedPosts(): List<PostEntity>?{
        return postDao().getAllPosts()
    }

    suspend fun cachePosts(list: List<PostEntity>){
        postDao().insertAll(list)
        LocalRepositoryObserver._isModified.postValue(false)
    }

    suspend fun deleteAllPosts(){
        postDao().deleteAll()
    }

    suspend fun deletePost(id: Int) {
        postDao().delete(id)
        LocalRepositoryObserver._isModified.postValue(true)
    }
}