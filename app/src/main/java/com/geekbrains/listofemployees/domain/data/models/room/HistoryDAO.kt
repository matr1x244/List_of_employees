package com.geekbrains.listofemployees.domain.data.models.room

import androidx.room.*

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM employee_table")
    fun allHistory(): List<HistoryEntity>

    @Query("SELECT * FROM employee_table WHERE name LIKE :nameEmployee")
    fun getDataByWord(nameEmployee: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM employee_table WHERE name = :nameEmployee")
    fun deleteByEmployeeName(nameEmployee: String?)

    @Query("DELETE FROM employee_table")
    fun deleteAll()

}
