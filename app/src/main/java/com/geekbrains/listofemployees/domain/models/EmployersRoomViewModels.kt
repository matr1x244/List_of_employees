package com.geekbrains.listofemployees.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.data.models.room.RepositoryRoom
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployersRoomViewModels(private val repository: RepositoryRoom) : ViewModel() {

    private val _repos = MutableLiveData<List<EmployeeEntityRoom>>()
    val repos: LiveData<List<EmployeeEntityRoom>> = _repos

    fun onShowListRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getAllHistory()
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

}
