package com.example.uf1_proyecto_compose.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun HomeTaskProgressionIndicator(
    modifier: Modifier = Modifier,
    title: String = "",
    totalTask: Int = 0,
    tasksDone: Int = 0,
) {

    if (tasksDone > totalTask) {
        throw Exception("Done tasks cant be greater than total tasks")
    }

    OutlinedCard(
        modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = title)
            }
            CircularProgressIndicator(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                strokeCap = StrokeCap.Round,
                trackColor = MaterialTheme.colorScheme.primary,
                color = MaterialTheme.colorScheme.tertiary,
                progress = {
                    (totalTask / tasksDone).toFloat()
                }
            )
        }
    }

}