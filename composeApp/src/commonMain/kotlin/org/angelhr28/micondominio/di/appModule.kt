package org.angelhr28.micondominio.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.angelhr28.micondominio.db.AppDatabase
import org.koin.dsl.module

fun appModule(appDatabase: AppDatabase) = module {
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } } }
//    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase, get()) }
//    factory { ExpensesViewModel(get()) }
}
