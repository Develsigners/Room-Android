package `in`.gsrathoreniks.room.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//ViewModel provide data to the UI and survive configuration changes
class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
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
}