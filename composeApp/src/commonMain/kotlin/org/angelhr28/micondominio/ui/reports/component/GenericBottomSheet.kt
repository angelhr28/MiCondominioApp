package org.angelhr28.micondominio.ui.reports.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericBottomSheet(
    sheetState: SheetState,
    title: String,
    onDismissRequest: () -> Unit,
    // Primary button
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    isPrimaryEnabled: Boolean = true,
    primaryButtonIcon: ImageVector? = null,
    // Secondary button
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    secondaryButtonIcon: ImageVector? = null,
    // Content slot
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .imePadding()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(20.dp))

            content()

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (secondaryButtonText != null && onSecondaryClick != null) {
                    OutlinedButton(
                        onClick = onSecondaryClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                    ) {
                        if (secondaryButtonIcon != null) {
                            Icon(secondaryButtonIcon, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                        }
                        Text(secondaryButtonText)
                    }
                }

                Button(
                    onClick = onPrimaryClick,
                    enabled = isPrimaryEnabled,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                ) {
                    if (primaryButtonIcon != null) {
                        Icon(primaryButtonIcon, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(primaryButtonText)
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}
