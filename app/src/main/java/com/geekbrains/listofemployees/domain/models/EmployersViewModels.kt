package com.geekbrains.listofemployees.domain.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.RepositoryEmployees
import kotlinx.coroutines.*

class EmployersViewModels(private val getRepository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<EmployeesEntity>()
    val repos: LiveData<EmployeesEntity> = _repos

//    val historyLiveData = MutableLiveData<Unit>()

    fun onShowList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.v("@@@", "No success $throwable")
        }
        var newStart: Job? = null
        newStart?.cancel()
        newStart = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = getRepository.observerListUser()
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }


//    fun addEmployee(employee: Employee) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val result = getRepository.saveEntity(employee)
//            withContext(Dispatchers.Main) {
//                historyLiveData.postValue(result)
//            }
//        }
//    }

}
