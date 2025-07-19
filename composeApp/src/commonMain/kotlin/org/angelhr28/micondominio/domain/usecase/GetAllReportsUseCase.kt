package org.angelhr28.micondominio.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.angelhr28.micondominio.model.Report

interface GetAllReportsUseCase {
    suspend fun invoke(): Flow<List<Report>>
}