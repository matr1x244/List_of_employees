package com.geekbrains.listofemployees.domain.data.models

import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom

interface RepositoryEmployees {

    suspend fun observerListUser(): EmployeesEntity

    /**
     * Для базы данных
     */
    suspend fun getAllHistory(): List<EmployeeEntityRoom>

    suspend fun saveEntity(employee: EmployeesEntity)

}