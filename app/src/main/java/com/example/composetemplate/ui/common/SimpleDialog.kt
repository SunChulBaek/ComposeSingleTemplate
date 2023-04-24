package com.example.composetemplate.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SimpleDialog(
    title: String? = null,
    message: String = "",
    okText: String = "확인",
    cancelText: String? = null,
    onDismissRequest: () -> Unit = { },
    onOkClick: () -> Unit = { },
    onCancelClick: () -> Unit = { }
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = if (title != null) {
            { Text(text = title) }
        } else null,
        text = { Text(text = message) },
        confirmButton = {
            TextButton(onClick = onOkClick) {
                Text(text = okText)
            }
        },
        dismissButton = if (cancelText != null) {
            {
                TextButton(onClick = onCancelClick) {
                    Text(text = cancelText)
                }
            }
        } else null
    )
}