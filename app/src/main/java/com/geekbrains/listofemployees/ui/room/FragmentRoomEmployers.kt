package com.geekbrains.listofemployees.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.listofemployees.R
import com.geekbrains.listofemployees.databinding.FragmentRoomEmployersBinding
import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.models.EmployersRoomViewModels
import com.geekbrains.listofemployees.domain.models.EmployersViewModels
import com.geekbrains.listofemployees.ui.room.recyclerview.RecyclerViewAdapterRoom
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentRoomEmployers : Fragment() {

    companion object {
        fun newInstance() = FragmentRoomEmployers()
    }

    private var _binding: FragmentRoomEmployersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployersRoomViewModels by viewModel()

    private val adapter = RecyclerViewAdapterRoom {
        Toast.makeText(context, "${it.name} in Room", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomEmployersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initIncomingEvents()
    }

    private fun initViews() {
        viewModel.onShowList()
        recyclerViewMain()
    }

    private fun recyclerViewMain() {
        binding.recyclerViewRoom.layoutManager = LinearLayoutManager(context)
        adapter.setHasStableIds(true)
        binding.recyclerViewRoom.adapter = adapter
    }

    private fun initIncomingEvents() {
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.setData(it.company.employees)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}