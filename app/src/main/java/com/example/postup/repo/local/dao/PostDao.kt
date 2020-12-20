package com.example.postup.repo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postup.model.PostEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PostEntity>)

    @Query("DELETE FROM PostEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM PostEntity WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM PostEntity")
    suspend fun getAllPosts(): List<PostEntity>?
}