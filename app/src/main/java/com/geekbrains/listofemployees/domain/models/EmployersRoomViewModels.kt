package com.geekbrains.listofemployees.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.data.models.RepositoryEmployees
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom
import kotlinx.coroutines.*

class EmployersRoomViewModels(private val getRepository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<List<EmployeeEntityRoom>>()
    val repos: LiveData<List<EmployeeEntityRoom>> = _repos

    fun onShowList() {
       viewModelScope.launch(Dispatchers.IO) {
                 var result = getRepository.getAllHistory()
                 withContext(Dispatchers.Main) {
                     _repos.postValue(result)
                 }
        }
    }

}
