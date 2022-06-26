package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.base.RepositoryEmployees
import com.geekbrains.listofemployees.domain.data.models.room.DataBaseEmployee
import com.geekbrains.listofemployees.domain.data.models.room.HistoryEntity

class RetrofitRequestImpl(private val api: EmployeesAPI) : RepositoryEmployees {

    override suspend fun observerListUser(): EmployeesEntity {
        return api.listUsers()
    }

    override suspend fun saveEntity(employee: Employee) {
        DataBaseEmployee.db.employeeDao().insert(convertToEntity(employee))
    }

    private fun convertToEntity(employee: Employee): HistoryEntity {
        return HistoryEntity(0,
            employee.name,
            employee.phoneNumber
        )
    }
}