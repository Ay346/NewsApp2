package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface ArticalDao {

    @Insert(onConflict = REPLACE)
    fun insertArtical(articalEntity: ArticalEntity)

    @Query("SELECT* FROM ARTICALENTITY")
    fun getAllArtical(): LiveData<List<ArticalEntity>>

    @Query("SELECT  url FROM ARTICALENTITY")
    fun getAllArticalUrl(): LiveData<List<String>>

    @Delete
    fun removeartical(articalEntity: ArticalEntity)
}