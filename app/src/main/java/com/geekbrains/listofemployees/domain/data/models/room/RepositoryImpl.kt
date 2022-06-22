package com.geekbrains.listofemployees.domain.data.models.room

import com.geekbrains.listofemployees.domain.data.models.base.Employee

class RepositoryImpl : RepositoryRoom {

    override suspend fun getAllHistory(): List<Employee> {
        return convertHistoryEntityToUser(DataBaseEmployee.db.employeeDao().allHistory())
    }

    private fun convertHistoryEntityToUser(entityList: List<HistoryEntity>): List<Employee> {
        return entityList.map {
            Employee(
                it.name,
                it.phoneNumber
            )
        }
    }

    override fun deleteEntity(employee: Employee) {
        DataBaseEmployee.db.employeeDao().deleteByEmployeeName(convertHistoryEntityToUser(employee).name)
    }

    private fun convertHistoryEntityToUser(employee: Employee): HistoryEntity {
        return HistoryEntity(0,
            employee.name,
            employee.phoneNumber
        )
    }
}