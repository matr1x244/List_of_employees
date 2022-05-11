package com.geekbrains.listofemployees.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
    val name: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    val skills: List<String>
) : Parcelable
