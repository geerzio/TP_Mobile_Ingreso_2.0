package com.example.application_3.repository

import androidx.lifecycle.LiveData
import com.example.application_3.data.UserDB
import com.example.application_3.model.User



class UserRepository {

    private val userDao = UserDB.getDatabase().userDao()

    val seeUsers: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user = user)
    }

    suspend fun editUser(user: User) {
        userDao.updateUser(user)
    }


    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }


    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}