package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticalEntity::class], version = 1)
abstract class ArticalDB : RoomDatabase() {

    abstract fun articalDao(): ArticalDao


}