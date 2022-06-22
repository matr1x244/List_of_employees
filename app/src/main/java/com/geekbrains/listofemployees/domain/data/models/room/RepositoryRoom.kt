package com.geekbrains.listofemployees.domain.data.models.room

import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity

interface RepositoryRoom {

    suspend fun getAllHistory(): List<Employee>

    suspend fun deleteEntity(employee: Employee)

}