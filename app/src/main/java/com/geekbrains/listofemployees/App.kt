package com.geekbrains.listofemployees

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.listofemployees.data.web.data.RetrofitRequestImpl
import com.geekbrains.listofemployees.domain.RepositoryEmployees

class App : Application() {

    val getRepository: RepositoryEmployees by lazy { RetrofitRequestImpl() }

}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
