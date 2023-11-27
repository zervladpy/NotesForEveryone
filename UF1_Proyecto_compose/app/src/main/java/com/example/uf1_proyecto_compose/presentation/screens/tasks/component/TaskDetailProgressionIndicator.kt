package com.example.uf1_proyecto_compose.presentation.screens.tasks.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TaskProgressionIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    onProgressChange: (Float) -> Unit = {}
) {

    Column(
        modifier = Modifier,
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = progress > 0F,
                onClick = { },
                enabled = false,
            )
            Text(text = "In Progress")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(5.dp),
                value = progress,
                onValueChange = onProgressChange,

                )

            Text(
                text = "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }

}