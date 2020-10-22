package com.stud.awra.tesktaskuserapi.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stud.awra.tesktaskuserapi.model_data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getDbService(): UserDao
}