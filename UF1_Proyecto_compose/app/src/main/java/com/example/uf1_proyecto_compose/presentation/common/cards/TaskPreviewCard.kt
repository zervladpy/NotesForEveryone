package com.example.uf1_proyecto_compose.presentation.common.cards

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import java.time.LocalDateTime

@Composable
fun TaskPreviewCard(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: (Task) -> Unit = {},
) {

    OutlinedCard(
        modifier = modifier,
        onClick = { onClick(task) }
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                    horizontal = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        end = 15.dp
                    )
                    .weight(6f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.7f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.created_at),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.7f
                            )
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = task.creationDate.toLocalDate().toString(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.7f
                            )
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .heightIn(
                        min = 70.dp,
                        max = 100.dp
                    )
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator(
                    progress = { task.progression },
                    strokeCap = StrokeCap.Round
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
    Notes4EveryoneTheme {
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
                        progression = 0.7f,
                        creationDate = LocalDateTime.now(),
                        synchronized = false
                    )
                )
            }
        }
    }
}