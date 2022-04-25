package com.geekbrains.listofemployees.domain.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.listofemployees.domain.RepositoryEmployees

class ViewModelFactory(private val repo: RepositoryEmployees) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployersViewModels(repo) as T
    }
}