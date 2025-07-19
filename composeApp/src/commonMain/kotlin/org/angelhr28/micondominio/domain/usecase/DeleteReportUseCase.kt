package org.angelhr28.micondominio.domain.usecase

interface DeleteReportUseCase {
    suspend fun invoke(id: Long)
}