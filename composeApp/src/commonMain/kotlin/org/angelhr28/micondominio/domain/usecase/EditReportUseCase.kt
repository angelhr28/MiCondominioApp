package org.angelhr28.micondominio.domain.usecase

import org.angelhr28.micondominio.model.Report

interface EditReportUseCase {
    suspend fun invoke(report: Report)
}