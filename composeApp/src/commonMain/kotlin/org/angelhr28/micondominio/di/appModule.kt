package org.angelhr28.micondominio.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.angelhr28.micondominio.data.repository.ReportsRepositoryImpl
import org.angelhr28.micondominio.db.AppDatabase
import org.angelhr28.micondominio.domain.repository.ReportsRepository
import org.angelhr28.micondominio.domain.usecase.AddReportUseCase
import org.angelhr28.micondominio.domain.usecase.AddReportUseCaseImpl
import org.angelhr28.micondominio.domain.usecase.DeleteReportUseCase
import org.angelhr28.micondominio.domain.usecase.DeleteReportUseCaseImpl
import org.angelhr28.micondominio.domain.usecase.EditReportUseCase
import org.angelhr28.micondominio.domain.usecase.EditReportUseCaseImpl
import org.angelhr28.micondominio.domain.usecase.GetAllReportsUseCase
import org.angelhr28.micondominio.domain.usecase.GetAllReportsUseCaseImpl
import org.angelhr28.micondominio.ui.reports.ReportsViewModel
import org.koin.dsl.module

fun appModule(appDatabase: AppDatabase) = module {
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } } }

    // Repository
    single<ReportsRepository> { ReportsRepositoryImpl(appDatabase, get()) }

    // UseCase
    single<GetAllReportsUseCase> { GetAllReportsUseCaseImpl(get()) }
    single<AddReportUseCase> { AddReportUseCaseImpl(get()) }
    single<EditReportUseCase> { EditReportUseCaseImpl(get()) }
    single<DeleteReportUseCase> { DeleteReportUseCaseImpl(get()) }

    //Viewmodel
    factory {
        ReportsViewModel(
            getAllReportsUseCase = get(),
            addReportUseCase = get(),
            editReportUseCase = get(),
            deleteReportUseCase = get()
        )
    }
}
