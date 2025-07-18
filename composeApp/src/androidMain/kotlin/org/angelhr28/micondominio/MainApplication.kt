package org.angelhr28.micondominio

import android.app.Application
import org.angelhr28.micondominio.data.DatabaseDriverFactory
import org.angelhr28.micondominio.db.AppDatabase
import org.angelhr28.micondominio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule(AppDatabase.invoke(DatabaseDriverFactory(this@MainApplication).createDriver())))
        }
    }
}