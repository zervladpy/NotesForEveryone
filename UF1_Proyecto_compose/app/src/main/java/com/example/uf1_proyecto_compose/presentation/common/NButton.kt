package com.example.uf1_proyecto_compose.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NButton(
    label: String,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(7.dp)
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NButtonPreview() {
    NButton(label = "Send", onClick = {})
}

@Composable
fun NTextButton(
    label: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NTextButtonPreview() {
    NTextButton(label = "Send", onClick = {})
}
