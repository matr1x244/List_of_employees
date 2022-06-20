package com.geekbrains.listofemployees.domain.data.models.base

import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom

interface RepositoryEmployees {

    suspend fun observerListUser(): EmployeesEntity

    suspend fun saveEntity(employee: EmployeeEntityRoom)

}