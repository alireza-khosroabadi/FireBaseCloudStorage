package com.example.firebasecloudstorage.ui.base

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppAlertDialog(
    onConfirmation: () -> Unit,
    onDismiss: (() -> Unit)? = null,
    confirmBtnText: String,
    dismissBtnText: String?,
    title: String,
    body: String,
    icon: ImageVector? = null
) {
    AlertDialog(icon = {
        icon?.let { Icon(imageVector = icon, contentDescription = "Dialog Icon") }
    },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = body)
        },
        onDismissRequest = {
            onDismiss?.let { onDismiss.invoke() }
        },
        dismissButton = {
            dismissBtnText?.let {
                TextButton(onClick = { onDismiss?.invoke() }) {
                    Text(text = dismissBtnText)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text(text = confirmBtnText)
            }
        })

}