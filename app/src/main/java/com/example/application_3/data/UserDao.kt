package com.example.application_3.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.application_3.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)


    @Query("SELECT * FROM user_table ORDER BY dni ASC")
    fun readAllData(): LiveData<List<User>>


    @Update
    suspend fun updateUser(user: User)


    @Delete
    suspend fun deleteUser(user: User)


    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}