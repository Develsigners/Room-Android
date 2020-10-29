package `in`.gsrathoreniks.room.data

import `in`.gsrathoreniks.room.model.User
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

//    Function to add a new user to the database, here onConflict is called when there
//    will be an existing user with same parameters then we've set it to ignore
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

//    Query to return all the user, the list will be wrapped in the LiveData
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

//    Function to update the existing user
    @Update
    suspend fun updateUser(user: User)
}