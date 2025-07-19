package org.angelhr28.micondominio.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.angelhr28.micondominio.domain.repository.ReportsRepository
import org.angelhr28.micondominio.model.Report

class GetAllReportsUseCaseImpl(
    val repository: ReportsRepository
) : GetAllReportsUseCase {
    override suspend fun invoke(): Flow<List<Report>> {
        return repository.getAllReports()
    }
}