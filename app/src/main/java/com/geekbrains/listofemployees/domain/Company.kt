package com.geekbrains.listofemployees.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Company(
    val name: String,
    val age: String,
    val competences: List<String>,
    val employees: List<Employee>
): Parcelable
