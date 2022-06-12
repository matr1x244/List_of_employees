package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees

class RetrofitRequestImpl(private val api: EmployeesAPI) : RepositoryEmployees {

    override suspend fun observerListUser(): EmployeesEntity {
        return api.listUsers()
    }
}