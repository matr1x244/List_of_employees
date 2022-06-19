package com.geekbrains.listofemployees.domain.data.models.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekbrains.listofemployees.App

@androidx.room.Database(
    entities = [
        EmployeeEntityRoom::class
    ],
    version = 1,
    exportSchema = false
)

abstract class DataBaseEmployee : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDAO

    companion object {
        private const val DB_NAME = "add_database_employee.db"

        val db: DataBaseEmployee by lazy {
            Room.databaseBuilder(
                App.appInstance,
                DataBaseEmployee::class.java,
                DB_NAME
            ).build()
        }
    }
}