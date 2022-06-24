package com.geekbrains.listofemployees.domain.data.models.base

interface RepositoryEmployees {

    suspend fun observerListUser(): EmployeesEntity

    suspend fun saveEntity(employee: Employee)

}