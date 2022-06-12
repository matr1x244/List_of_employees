package com.geekbrains.listofemployees.data.web.data

import com.geekbrains.listofemployees.domain.EmployeesEntity
import retrofit2.http.GET

interface EmployeesAPI {

    @GET("5ddcd3673400005800eae483")
    suspend fun listUsers(): EmployeesEntity

}