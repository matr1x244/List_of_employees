package com.geekbrains.listofemployees.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

class EmployersViewModels(private val getRepository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<EmployeesEntity>()
    val repos: LiveData<EmployeesEntity> = _repos

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowList() {
        compositeDisposable.add(
            getRepository
                .observerListUser()
                .subscribeBy(
                    onSuccess = {
                        _repos.postValue(it)
                    },
                    onError = {
                        Timber.e("observerListUser", "observerListUser")
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}