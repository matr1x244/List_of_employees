package com.geekbrains.listofemployees.domain.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.base.RepositoryEmployees
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom
import kotlinx.coroutines.*

class EmployersViewModels(private val repository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<EmployeesEntity>()
    val repos: LiveData<EmployeesEntity> = _repos

//    private val _history = MutableLiveData<EmployeesEntity>()
//    val history: LiveData<EmployeesEntity> = _history

    private val history = MutableLiveData<Unit>()

    fun onShowList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.v("@@@", "No success $throwable")
        }
        var newStart: Job? = null
        newStart?.cancel()
        newStart = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.observerListUser()
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

    fun saveEntity(employee: EmployeeEntityRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.saveEntity(employee)
            withContext(Dispatchers.Main) {
                history.postValue(result)
            }
        }
    }

}
