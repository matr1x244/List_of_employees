package com.geekbrains.listofemployees.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.geekbrains.listofemployees.R
import com.geekbrains.listofemployees.databinding.FragmentEmployersBinding
import com.geekbrains.listofemployees.domain.viewModels.EmployersViewModels
import com.geekbrains.listofemployees.ui.main.recyclerview.RecyclerViewAdapter
import com.geekbrains.listofemployees.ui.room.FragmentRoomEmployers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.LinkedList
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class FragmentEmployers : Fragment() {

    companion object {
        fun newInstance() = FragmentEmployers()
    }

    private var _binding: FragmentEmployersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployersViewModels by viewModel()

    private val adapter = RecyclerViewAdapter {
        Toast.makeText(context, "${it.name}", Toast.LENGTH_SHORT).show()
        viewModel.onSaveUser(it)
    }

    private lateinit var flow : Flow <String>
    private var jobForCancel : Job? = null

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

//        coldFlow() //flow
//        hotFlow() // SharedFlow
        hotFLow2() // StateFlow

    }
    //------------------
    private fun createStateFlow(): Flow<String> {
        val statesFlow = MutableStateFlow("")

        CoroutineScope(Dispatchers.IO).launch {
            listOf("1", "2", "3", "4","5","6").forEach{
                statesFlow.value = it // записвает данные в лист
                delay(100L) // задеркжка
            }
        }

        return statesFlow
    }
    private fun hotFLow2() {
        CoroutineScope(Dispatchers.Main).launch {
            createStateFlow().collect {
                delay(200L) // подписываемся с задержкой получения данных
                binding.tvFlow.append(it.toString())
                println(it)
            }
        }
    }
    //------------------
    private fun createSharedFlow(): Flow<Int> {
        val sharedFlow = MutableSharedFlow<Int>(replay = 5)

        CoroutineScope(Dispatchers.Default).launch {
            for (i in 0 until 7) { //
                sharedFlow.emit(Random.nextInt(1, 33))
                delay(250)
            }
        }

        return sharedFlow
    }

    private fun hotFlow() {
        val flow = createSharedFlow()

        CoroutineScope(Dispatchers.Main).launch {
            flow.collect {
                binding.tvFlow.append(it.toString())
                println("Подписчик # 1: $it")
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000L) // останавливаем на 1 сек получение данных
            flow.collect {
                binding.tvFlow.append(it.toString())
                println("Подписчик # 2: $it")
            }
        }
    }
    //------------------
    private fun coldFlow() {
        flow = flow {
            for(i in 1..100000) {
                delay(100) // кидаем данные с задержкой
                emit(i.toString()) // кидаем в поток значение.
            }
        }.flowOn(Dispatchers.Default)
        text()
    }

    private fun text() {
        binding.tvFlow.text = ""
        jobForCancel = CoroutineScope(Dispatchers.Main).launch {
            flow.buffer().collect() { //вначале кидаем проверяем в buffer данные тут через collet() сетим данные
                delay(400) // получаем данные с задержкой
                binding.tvFlow.append(it)

                binding.tvFlow.setTextColor(Color.CYAN) // задаем цвет

                if(binding.tvFlow.text.length < 10){ // меняем цвет после условия
                    binding.tvFlow.setTextColor(Color.YELLOW)
                    }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        jobForCancel?.cancel() // останавливаем поток данных
    }

    private fun initViews() {
        viewModel.onShowList()
        recyclerViewMain()
        buttonRoom()
        imageTittle()
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

    private fun buttonRoom() {
        binding.buttonFragmentRoom.setOnClickListener {
            activity?.supportFragmentManager?.let { fragment ->
                fragment.beginTransaction()
                    .add(R.id.container_main_activity, FragmentRoomEmployers.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun imageTittle() {
        val url = "https://img.hhcdn.ru/employer-logo/3241525.png"

        Glide.with(requireActivity())
            .load(url)
            .into(binding.imageTittle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}