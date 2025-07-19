package org.angelhr28.micondominio.domain.usecase

import org.angelhr28.micondominio.domain.repository.ReportsRepository
import org.angelhr28.micondominio.model.Report

class AddReportUseCaseImpl(
    val repository: ReportsRepository
) : AddReportUseCase {
    override suspend fun invoke(report: Report) {
        return repository.addReport(report)
    }
}