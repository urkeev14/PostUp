package com.example.postup.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postup.model.PostEntity
import com.example.postup.repo.local.dao.PostDao

@Database(entities = [PostEntity::class], version = 4, exportSchema = false)
abstract class LocalRepository : RoomDatabase(){

    abstract fun postDao(): PostDao
}