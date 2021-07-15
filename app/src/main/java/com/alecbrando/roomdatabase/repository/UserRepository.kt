package com.alecbrando.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.alecbrando.roomdatabase.data.UserDao
import com.alecbrando.roomdatabase.fragments.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}