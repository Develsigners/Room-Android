package `in`.gsrathoreniks.room.fragments.add

import `in`.gsrathoreniks.room.R
import `in`.gsrathoreniks.room.data.User
import `in`.gsrathoreniks.room.data.UserViewModel
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProvider


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var age: EditText
    private lateinit var btnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        age = view.findViewById(R.id.age)
        btnAdd = view.findViewById(R.id.btnAdd)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val fName = firstName.text.toString()
        val lName = lastName.text.toString()
        val ag = age.text

        if(inputCheck(fName,lName, ag)){
//            Create User object
        val user = User(0,fName,lName,Integer.parseInt(ag.toString()))
//            Add data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(context,"Successfully added",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String,lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}