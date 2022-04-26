package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequestImpl : RepositoryEmployees {

    private val baseUrl = ("http://www.mocky.io/v2/")

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: EmployeesAPI = retrofit.create(EmployeesAPI::class.java)

    override fun observerListUser(): Single<EmployeesEntity> {
        return api.listUsers()
    }
}