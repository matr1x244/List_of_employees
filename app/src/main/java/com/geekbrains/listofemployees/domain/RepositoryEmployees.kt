package com.geekbrains.listofemployees.domain

import io.reactivex.rxjava3.core.Single

interface RepositoryEmployees {

    fun observerListUser(): Single<EmployeesEntity>
}