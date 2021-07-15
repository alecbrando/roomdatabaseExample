package com.alecbrando.roomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alecbrando.roomdatabase.R
import com.alecbrando.roomdatabase.data.User
import com.alecbrando.roomdatabase.databinding.FragmentAddBinding
import com.alecbrando.roomdatabase.viewmodel.MainViewModel


class AddFragment : Fragment() {

    private lateinit var _binding : FragmentAddBinding
    private val binding get() = _binding

    private lateinit var myUserViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        myUserViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.addItem.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = binding.editTextPersonFirstName.text.toString()
        val lastName = binding.editTextPersonLastName.text.toString()
        val age = binding.editAge.text

        if(inputCheck(firstName, lastName, age)){
            val user = User(0, firstName, lastName, age.toString().toInt())
            myUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added ${user.firstName}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}