package `in`.gsrathoreniks.room.fragments.list

import `in`.gsrathoreniks.room.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        view.findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

}