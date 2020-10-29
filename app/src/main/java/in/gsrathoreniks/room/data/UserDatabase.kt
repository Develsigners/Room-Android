package `in`.gsrathoreniks.room.data

import `in`.gsrathoreniks.room.model.User
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

//    Make UserDatabase a SINGLETON class as we want only a SINGLE instance of UserDatabase running


//    this function will return the UserDao which we created
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE

//            check if there is any existing instance is present for our room database
//            if there exist an existing instance then we'll return that instance
            if(tempInstance!=null){
                return tempInstance
            }

//            If there is no any instance present for our database then we'll create a new instance
//            WHY SYNCHRONIZED ?? --> Because everything inside the synchronized block will be protected
//            by concurrent execution on multiple threads
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                            UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}