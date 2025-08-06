package org.angelhr28.micondominio.ui.feature.reports.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.angelhr28.micondominio.model.Report
import org.angelhr28.micondominio.model.ReportStatus

@Composable
fun ReportCard(
    report: Report,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = report.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = report.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Row {
                Text(
                    text = report.createAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = when (report.status) {
                        ReportStatus.PENDING -> "⏳ Pendiente"
                        ReportStatus.DONE -> "✅ Atendido"
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = if (report.status == ReportStatus.PENDING)
                        MaterialTheme.colorScheme.tertiary
                    else
                        MaterialTheme.colorScheme.secondary
                )
            }

            if (!report.response.isNullOrBlank()) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "✉️ Respuesta del administrador:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = report.response,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(12.dp))
            Row {
                OutlinedButton(onClick = onEdit) {
                    Text("✏️ Editar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(Modifier.width(8.dp))
                OutlinedButton(onClick = onDelete) {
                    Text("🗑️ Eliminar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}
