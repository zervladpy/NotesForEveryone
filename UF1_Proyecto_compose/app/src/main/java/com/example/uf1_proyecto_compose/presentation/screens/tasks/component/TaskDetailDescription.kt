package com.example.uf1_proyecto_compose.presentation.screens.tasks.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.domain.model.Task

@Composable
fun TaskDetailDescription(
    modifier: Modifier = Modifier,
    task: Task
) {

    Column(modifier.padding(15.dp)) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(10.dp),
                    width = 1.dp
                )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                text = task.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center
            )
        }

    }

}