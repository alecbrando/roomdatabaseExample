package com.alecbrando.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.alecbrando.roomdatabase.data.User
import com.alecbrando.roomdatabase.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}