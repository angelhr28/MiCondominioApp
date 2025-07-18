package org.angelhr28.micondominio

import androidx.compose.ui.window.ComposeUIViewController
import org.angelhr28.micondominio.data.DatabaseDriverFactory
import org.angelhr28.micondominio.db.AppDatabase
import org.angelhr28.micondominio.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(appModule(AppDatabase.invoke(DatabaseDriverFactory().createDriver())))
    }.koin
}