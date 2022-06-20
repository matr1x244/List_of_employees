package com.geekbrains.listofemployees.domain.data.models.room

interface RepositoryRoom {

    fun getAllHistory(): List<EmployeeEntityRoom>

}