package com.geekbrains.listofemployees.domain.data.models.room

import androidx.room.*

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM employee_table")
    fun all(): List<EmployeeEntityRoom>

    @Query("SELECT * FROM employee_table WHERE name LIKE :nameEmployee")
    fun getDataByWord(nameEmployee: String): List<EmployeeEntityRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: EmployeeEntityRoom)

    @Update
    fun update(entity: EmployeeEntityRoom)

    @Delete
    fun delete(entity: EmployeeEntityRoom)

    @Query("DELETE FROM employee_table WHERE name = :nameEmployee")
    fun deleteByEmployeeName(nameEmployee: String?)

    @Query("DELETE FROM employee_table")
    fun deleteAll()

}
