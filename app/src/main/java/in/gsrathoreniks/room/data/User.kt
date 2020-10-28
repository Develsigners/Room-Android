package `in`.gsrathoreniks.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Create the Entity for the database this will be the Table in database
* with tableName - "user_table"
* and
* PrimaryKey will be auto-generated
* and there will be 4 columns
*
 */

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)