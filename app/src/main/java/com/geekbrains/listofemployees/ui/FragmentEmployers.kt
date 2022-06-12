package com.geekbrains.listofemployees.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.listofemployees.databinding.FragmentEmployersBinding
import com.geekbrains.listofemployees.domain.models.EmployersViewModels
import com.geekbrains.listofemployees.ui.recyclerview.RecyclerViewAdapter
import kotlinx.coroutines.*
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
        coroutine()
    }

    private fun coroutine() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5_000)
            withContext(Dispatchers.Main) {
                Toast.makeText(activity, "start scope: fun coroutine", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViews() {
        viewModel.onShowList()
        recyclerViewMain()
    }

    private fun recyclerViewMain() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setHasStableIds(true)
        binding.recyclerView.adapter = adapter
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