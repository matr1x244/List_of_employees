package com.geekbrains.listofemployees.domain.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.room.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployersRoomViewModels(private val repository: RepositoryRoom) : ViewModel() {

    private val _repos = MutableLiveData<List<Employee>>()
    val repos: LiveData<List<Employee>> = _repos

    val historyLiveDataDelete = MutableLiveData<Unit>()

    fun onShowListRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getAllHistory()
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            historyLiveDataDelete.postValue(repository.deleteEntity(employee))
        }
    }

}
