package org.angelhr28.micondominio.domain.usecase

import org.angelhr28.micondominio.domain.repository.ReportsRepository

class DeleteReportUseCaseImpl(
    val repository: ReportsRepository
) : DeleteReportUseCase {
    override suspend fun invoke(id: Long) {
        repository.deleteReport(id = id)
    }
}