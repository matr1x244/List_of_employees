package com.geekbrains.listofemployees.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.listofemployees.data.web.data.EmployeesAPI
import com.geekbrains.listofemployees.data.web.data.RetrofitRequestImpl
import com.geekbrains.listofemployees.domain.Company
import com.geekbrains.listofemployees.domain.Employee
import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class EmployersViewModels(private val getRepository: RepositoryEmployees) : ViewModel() {

    private val _repos = MutableLiveData<EmployeesEntity>()
    val repos: LiveData<EmployeesEntity> = _repos

//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

//    fun onShowList() {
//        compositeDisposable.add(
//            getRepository
//                .observerListUser()
//                .subscribeBy(
//                    onSuccess = {
//                        _repos.postValue(it)
//                    },
//                    onError = {
//                        Timber.e("observerListUser", "observerListUser")
//                    }
//                )
//        )
//    }

   fun onShowList() {
       viewModelScope.launch(Dispatchers.IO) {
           val result = getRepository.observerListUser()
           withContext(Dispatchers.Main) {
               _repos.postValue(result)
           }
        }
    }

}
