package com.geekbrains.listofemployees.domain.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class EmployeeEntityRoom(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "employee_name") val name: String?,
    @ColumnInfo(name = "employee_phoneNumber") val phoneNumber: String?
)
