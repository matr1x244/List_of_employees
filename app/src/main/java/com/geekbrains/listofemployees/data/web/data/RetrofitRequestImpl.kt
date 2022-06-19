package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.data.models.base.Employee
import com.geekbrains.listofemployees.domain.data.models.base.EmployeesEntity
import com.geekbrains.listofemployees.domain.data.models.RepositoryEmployees
import com.geekbrains.listofemployees.domain.data.models.room.DataBaseEmployee
import com.geekbrains.listofemployees.domain.data.models.room.EmployeeEntityRoom

class RetrofitRequestImpl(private val api: EmployeesAPI) : RepositoryEmployees {

    override suspend fun observerListUser(): EmployeesEntity {
        return api.listUsers()
    }

    /**
     * Для базы данных
     */
    override suspend fun getAllHistory(): List<EmployeeEntityRoom> {
        return convertHistoryEntity(DataBaseEmployee.db.employeeDao().all())
    }

    override suspend fun saveEntity(employee: EmployeesEntity) {
        DataBaseEmployee.db.employeeDao().insert(convertToEntity(employee))
    }

    private fun convertHistoryEntity(entityList: List<EmployeeEntityRoom>): List<EmployeeEntityRoom> {
        return entityList.map {
            EmployeeEntityRoom(
                it.id,
                it.name,
                it.phoneNumber)
        }
    }

    private fun convertToEntity(employee: EmployeesEntity): EmployeeEntityRoom {
        return EmployeeEntityRoom(
            0,
            employee.company.name,
            employee.company.employees.toString())
    }

}