package com.geekbrains.listofemployees.data.local

import com.geekbrains.listofemployees.domain.Company
import com.geekbrains.listofemployees.domain.Employee
import com.geekbrains.listofemployees.domain.EmployeesEntity
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import io.reactivex.rxjava3.core.Single

class LocalRequestImpl : RepositoryEmployees {

    override fun observerListUser(): Single<List<EmployeesEntity>> {
        val localList = listOf(
            EmployeesEntity(
                Company(
                    "0", "100",
                    competences = listOf(),
                    employees = listOf(Employee("name", "9999999", skills = listOf())),
                    )
            )
        )
        return Single.just(localList)
    }
}