package com.example.uf1_proyecto_compose.presentation.common.cards

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.domain.models.Task
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskPreviewCard(
    task: Task,
    onClick: (Task) -> Unit = {},
    onTaskComplete: (Boolean) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        onClick = { onClick(task) }
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(
                        0.7f
                    )
                    .fillMaxHeight(),
            ) {
                Text(
                    modifier = Modifier.padding(
                        bottom = 10.dp
                    ),
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Checkbox(
                    checked = task.done,
                    onCheckedChange = onTaskComplete
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskPreviewCardPreview(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = modifier.padding(10.dp)) {
                TaskPreviewCard(
                    task = Task(
                        uid = "",
                        title = "Task Title",
                        description = "Some Shot Description of the task",
                        done = false,
                    )
                )
            }
        }
    }
}