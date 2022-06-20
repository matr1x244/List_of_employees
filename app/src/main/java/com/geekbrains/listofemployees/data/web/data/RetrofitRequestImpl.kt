package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.base.RepositoryEmployees
import com.geekbrains.listofemployees.domain.data.models.room.DataBaseEmployee
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom

class RetrofitRequestImpl(private val api: EmployeesAPI) : RepositoryEmployees {

    override suspend fun observerListUser(): EmployeesEntity {
        return api.listUsers()
    }

    override suspend fun saveEntity(employee: EmployeeEntityRoom) {
        DataBaseEmployee.db.employeeDao().insert(convertToEntity(employee))
    }

    private fun convertToEntity(employee: EmployeeEntityRoom): EmployeeEntityRoom {
        return EmployeeEntityRoom(
            0,
            employee.name,
            employee.phoneNumber
        )
    }
}