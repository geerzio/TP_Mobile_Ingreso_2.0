package com.example.application_3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application_3.model.User
import com.example.application_3.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {

    private val userRepository = UserRepository()
    val readAllData: LiveData<List<User>> = userRepository.seeUsers

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user = user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.editUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch (Dispatchers.IO) {
            userRepository.deleteUser(user = user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch (Dispatchers.IO) {
            userRepository.deleteAllUsers()
        }
    }
}