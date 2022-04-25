package com.geekbrains.listofemployees.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeesEntity(
    val company: Company
) : Parcelable
