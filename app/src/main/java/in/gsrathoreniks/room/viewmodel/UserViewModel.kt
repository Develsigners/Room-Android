package `in`.gsrathoreniks.room.viewmodel

import `in`.gsrathoreniks.room.data.UserDatabase
import `in`.gsrathoreniks.room.data.repository.UserRepository
import `in`.gsrathoreniks.room.model.User
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//ViewModel provide data to the UI and survive configuration changes
class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

//    init block is executed FIRST when ViewModel is created
    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
//    initializing repository and passing userDao
        repository = UserRepository(userDao)
//    get all data from user repository in the variable readAllData
        readAllData = repository.readAllData
    }

    //    function to add user to the repository
    fun addUser(user: User){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    //    function to update user to the repository
    fun updateUser(user: User){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    //    function to delete user
    fun deleteUser(user: User){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    //    function to delete all users
    fun deleteAllUsers(){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
}