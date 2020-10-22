package com.stud.awra.tesktaskuserapi.repository.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stud.awra.tesktaskuserapi.model_data.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getUsers(): DataSource.Factory<Int, User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUsers(users: List<User>)

    @Query("DELETE  FROM  User")
    fun deleteAll()
}