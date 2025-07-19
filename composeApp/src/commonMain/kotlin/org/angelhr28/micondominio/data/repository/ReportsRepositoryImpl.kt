package org.angelhr28.micondominio.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.angelhr28.micondominio.db.AppDatabase
import org.angelhr28.micondominio.domain.repository.ReportsRepository
import org.angelhr28.micondominio.model.Report
import org.angelhr28.micondominio.model.ReportStatus

class ReportsRepositoryImpl(
    appDatabase: AppDatabase,
    private val httpClient: HttpClient
) : ReportsRepository {

    private val BASE_URL = "http://192.168.0.102:8080"
    private val queries = appDatabase.reportDBQueries

    override suspend fun getAllReports(): Flow<List<Report>> {
        return queries.selectAll().asFlow()
            .mapToList(Dispatchers.IO)
            .map { reportList ->
                reportList.map { row ->
                    Report(
                        id = row.id,
                        title = row.title,
                        description = row.description,
                        status = ReportStatus.findFromKey(row.status),
                        response = row.response,
                        createAt = row.createAt,
                        updateAt = row.updateAt
                    )
                }
            }
    }

    override suspend fun addReport(report: Report) {
        queries.transaction {
            queries.insert(
                title = report.title,
                description = report.description,
                status = report.status.name,
                response = report.response.orEmpty(),
                createAt = report.createAt,
                updateAt = report.updateAt
            )
        }
    }

    override suspend fun editReport(report: Report) {

        queries.transaction {
            queries.update(
                id = report.id,
                title = report.title,
                description = report.description,
                updateAt = Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                    .toString()
            )
        }
    }

    override suspend fun deleteReport(id: Long) {
        queries.transaction {
            queries.delete(id = id)
        }
    }
}