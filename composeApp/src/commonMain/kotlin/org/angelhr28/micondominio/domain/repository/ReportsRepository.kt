package org.angelhr28.micondominio.domain.repository

import kotlinx.coroutines.flow.Flow
import org.angelhr28.micondominio.model.Report

interface ReportsRepository {
    suspend fun getAllReports(): Flow<List<Report>>
    suspend fun addReport(report: Report)
    suspend fun editReport(report: Report)
    suspend fun deleteReport(id: Long)
}