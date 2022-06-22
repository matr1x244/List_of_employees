package com.geekbrains.listofemployees.domain.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.base.RepositoryEmployees
import kotlinx.coroutines.*

class EmployersViewModels(private val repository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<EmployeesEntity>()
    val repos: LiveData<EmployeesEntity> = _repos

    private val _history = MutableLiveData<Employee>()
    val history: LiveData<Employee> = _history

    fun onShowList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.v("@@@", "No success $throwable")
        }
        var newStart: Job? = null
        newStart?.cancel()
        newStart = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.observerListUser()
//            repository.saveEntity(result) ??
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

    fun onSaveUser(employee: Employee){
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.saveEntity(employee) //
            withContext(Dispatchers.Main){
                _history.value
            }
        }
    }


}

