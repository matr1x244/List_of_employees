package com.geekbrains.listofemployees.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployersViewModels(private val getRepository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<EmployeesEntity>()
    val repos: LiveData<EmployeesEntity> = _repos

    fun onShowList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getRepository.observerListUser()
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }

}
