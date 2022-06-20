package com.geekbrains.listofemployees.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.listofemployees.R
import com.geekbrains.listofemployees.databinding.FragmentEmployersBinding
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom
import com.geekbrains.listofemployees.domain.models.EmployersViewModels
import com.geekbrains.listofemployees.ui.main.recyclerview.RecyclerViewAdapter
import com.geekbrains.listofemployees.ui.room.FragmentRoomEmployers
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentEmployers : Fragment() {

    companion object {
        fun newInstance() = FragmentEmployers()
    }

    private var _binding: FragmentEmployersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployersViewModels by viewModel()

    private val adapter = RecyclerViewAdapter {
        Toast.makeText(context, "${it.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployersBinding.inflate(inflater, container, false)
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
        buttonRoom()
    }

    private fun recyclerViewMain() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setHasStableIds(true)
        binding.recyclerView.adapter = adapter
    }

    private fun buttonRoom() {
        binding.buttonFragmentRoom.setOnClickListener {
            activity?.supportFragmentManager?.let { fragment ->
                fragment.beginTransaction()
                    .add(R.id.container_main_activity, FragmentRoomEmployers.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
        buttonSaveRoom()
    }

    private fun buttonSaveRoom() {

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