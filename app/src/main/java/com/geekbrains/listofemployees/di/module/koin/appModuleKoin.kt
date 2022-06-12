package com.geekbrains.listofemployees.di.module.koin

import com.geekbrains.listofemployees.data.web.data.EmployeesAPI
import com.geekbrains.listofemployees.data.web.data.RetrofitRequestImpl
import com.geekbrains.listofemployees.domain.RepositoryEmployees
import com.geekbrains.listofemployees.domain.models.EmployersViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModuleKoin = module {

    val apiUrl = "http://www.mocky.io/v2/"
    single<RepositoryEmployees> { RetrofitRequestImpl(get()) }
    single<EmployeesAPI> { get<Retrofit>().create(EmployeesAPI::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
    }

    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }


    viewModel { EmployersViewModels(get()) }
}