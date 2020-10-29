package `in`.gsrathoreniks.room.fragments.update

import `in`.gsrathoreniks.room.R
import `in`.gsrathoreniks.room.model.User
import `in`.gsrathoreniks.room.viewmodel.UserViewModel
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_first_name.setText(args.currentUser.firstName)
        view.update_last_name.setText(args.currentUser.lastName)
        view.update_age.setText(args.currentUser.age.toString())

        view.update_btnAdd.setOnClickListener {
            updateItem()
        }

//        Add menu to the fragment
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstName = update_first_name.text.toString()
        val lastName = update_last_name.text.toString()
        val age = Integer.parseInt(update_age.text.toString())

        if(inputCheck(firstName,lastName,update_age.text)){
//            Create User Object
            val updatedUser =  User(args.currentUser.id,firstName, lastName, age)

//            Update User Object
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"Updated successfully",Toast.LENGTH_LONG).show()

//            Navigate back to list after updating data
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields..",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String,lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Deleted: ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }

}