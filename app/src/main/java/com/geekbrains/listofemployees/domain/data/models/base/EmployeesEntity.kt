package com.geekbrains.listofemployees.domain.data.models.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeesEntity(
    val company: Company
) : Parcelable
