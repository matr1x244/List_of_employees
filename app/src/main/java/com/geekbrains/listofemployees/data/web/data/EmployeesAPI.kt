package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.Employee
import com.geekbrains.listofemployees.domain.EmployeesEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeesAPI {

    @GET("5ddcd3673400005800eae483")
    fun listUsers(): Single<List<EmployeesEntity>>

}