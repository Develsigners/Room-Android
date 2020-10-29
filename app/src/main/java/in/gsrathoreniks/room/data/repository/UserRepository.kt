package `in`.gsrathoreniks.room.data.repository

import `in`.gsrathoreniks.room.data.UserDao
import `in`.gsrathoreniks.room.model.User
import androidx.lifecycle.LiveData

/*
* A repository class abstracts access to multiple data sources
 */

class UserRepository (private val userDao: UserDao){

//    variable to readAllData that we'll be getting from userDao
    val readAllData: LiveData<List<User>> = userDao.readAllData()


//    a suspend function to add new User to our database which will
//    call UserDao and add new User
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}