package com.geekbrains.listofemployees.domain.models

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployersViewModels(private val getRepository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<List<EmployeesEntity>>() // закидываем событие
    val repos: LiveData<List<EmployeesEntity>> = _repos // читаем событие

    private val compositeDisposable: CompositeDisposable = CompositeDisposable() // метод отписки RX

    fun onShowList() {
        compositeDisposable.add(
            getRepository
                .observerListUser()
                .subscribeBy(
                    onSuccess = {
                        _repos.postValue(it)
                    },
                    onError = {
                        Log.e("onError", "onError")
                    }
                )
        )
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}