package com.alecbrando.roomdatabase.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.alecbrando.roomdatabase.R
import com.alecbrando.roomdatabase.databinding.FragmentUpdateBinding
import com.alecbrando.roomdatabase.fragments.model.User
import com.alecbrando.roomdatabase.viewmodel.MainViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var _binding: FragmentUpdateBinding
    private val binding get() = _binding

    private lateinit var myUserViewModel : MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.updatePersonFirstName.setText(args.currentUser.firstName)
        binding.updatePersonLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())

        myUserViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.updateItem.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem(){
        val firstName = binding.updatePersonFirstName.text.toString()
        val lastName = binding.updatePersonLastName.text.toString()
        val age = binding.updateAge.text

        if(inputCheck(firstName, lastName, age)){
            val updateUser = User(args.currentUser.id, firstName, lastName, age.toString().toInt())
            myUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Successfully updated ${updateUser.firstName}!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        } else {
            Toast.makeText(requireContext(), "Please make sure fields are valid!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}