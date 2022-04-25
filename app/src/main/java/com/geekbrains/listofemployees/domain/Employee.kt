package com.geekbrains.listofemployees.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
    val name: String?,
    val phoneNumber: String?,
    val skills: List<String>
): Parcelable
