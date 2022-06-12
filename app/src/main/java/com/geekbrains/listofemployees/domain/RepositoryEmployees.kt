package com.geekbrains.listofemployees.domain

interface RepositoryEmployees {

   suspend fun observerListUser(): EmployeesEntity
}