package com.geekbrains.listofemployees.domain.data.models.room

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.geekbrains.listofemployees.App

@androidx.room.Database(
    entities = [
        HistoryEntity::class
    ],
    version = 2,
    exportSchema = false
)

abstract class DataBaseEmployee : RoomDatabase() {

    abstract fun employeeDao(): HistoryDAO

    companion object {
        private const val DB_NAME = "add_database_employee.db"

        /**
         * Миграция из 1 версии таблицы во 2
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE employee_table ADD COLUMN skill TEXT NOT NULL DEFAULT ''")
            }
        }

        val db: DataBaseEmployee by lazy {
            Room.databaseBuilder(
                App.appInstance,
                DataBaseEmployee::class.java,
                DB_NAME
            ).addMigrations(MIGRATION_1_2)
                .build()
        }
    }

}