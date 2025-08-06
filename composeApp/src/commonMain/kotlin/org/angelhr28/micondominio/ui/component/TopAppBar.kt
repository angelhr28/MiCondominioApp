package org.angelhr28.micondominio.ui.component


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: ImageVector? = Icons.Default.ArrowBackIosNew,
    onBackClick: (() -> Unit)? = null,
    menuIcon: ImageVector? = null,
    onMenuClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = if (onBackClick != null || navigationIcon != null) 0.dp else 8.dp)
            )
        },
        navigationIcon = {
            if (onBackClick != null && navigationIcon != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Volver"
                    )
                }
            }
        },
        actions = {
            if (onMenuClick != null && menuIcon != null) {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = menuIcon,
                        contentDescription = "Menú"
                    )
                }
            }
        }
    )
}
