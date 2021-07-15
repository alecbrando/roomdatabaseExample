package com.alecbrando.roomdatabase.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alecbrando.roomdatabase.R
import com.alecbrando.roomdatabase.databinding.FragmentListBinding
import com.alecbrando.roomdatabase.viewmodel.MainViewModel


class ListFragment : Fragment() {

    private lateinit var _binding : FragmentListBinding
    private val binding : FragmentListBinding
        get() = _binding

    private lateinit var mUserViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val users = mUserViewModel.readAllData
        val recyclerView = binding.recyclerView
        mUserViewModel.readAllData.observe(viewLifecycleOwner, { users ->
        recyclerView.adapter = ListAdapter(users)
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

}