package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class RetrofitRequestImpl(private val api: EmployeesAPI) : RepositoryEmployees {

    override suspend fun observerListUser(): EmployeesEntity {
        return api.listUsers()
    }
}