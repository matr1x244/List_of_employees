package com.geekbrains.listofemployees.domain

import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface RepositoryEmployees {

   suspend fun observerListUser(): EmployeesEntity
}