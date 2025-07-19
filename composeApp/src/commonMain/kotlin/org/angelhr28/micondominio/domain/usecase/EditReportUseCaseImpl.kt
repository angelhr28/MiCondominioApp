package org.angelhr28.micondominio.domain.usecase

import org.angelhr28.micondominio.domain.repository.ReportsRepository
import org.angelhr28.micondominio.model.Report

class EditReportUseCaseImpl(
    val repository: ReportsRepository
) : EditReportUseCase {
    override suspend fun invoke(report: Report) {
        repository.editReport(report)
    }
}