package com.example.uf1_proyecto_compose.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TaskProgressionCard(modifier: Modifier = Modifier) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { }
    ) {
        Row(
        ) {
            /// Text of the Card
            Column(modifier = modifier.padding(10.dp)) {
                Text(text = "Task Progression")
                Text(text = "20 / 40 ")
                DayIndicatorPill(day = 22, month = "November" )
            }

            /// Progress indicator

        }
    }

}