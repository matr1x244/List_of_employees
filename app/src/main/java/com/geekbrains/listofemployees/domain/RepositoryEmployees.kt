package com.geekbrains.listofemployees.domain

import io.reactivex.rxjava3.core.Single
import retrofit2.Call

interface RepositoryEmployees {

    fun observerListUser(): Single<List<EmployeesEntity>>
}