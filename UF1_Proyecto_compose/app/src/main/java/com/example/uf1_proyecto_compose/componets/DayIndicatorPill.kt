@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.uf1_proyecto_compose.componets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DayIndicatorPill(modifier: Modifier = Modifier, day: Int, month: String) {

    SuggestionChip(
        onClick = { },
        label = {
            Text(
                modifier = modifier.padding(end = 5.dp),
                text = month
            )
            Text(text = "$day")
        },
    )

}

@Preview(showBackground = true)
@Composable
fun DayIndicatorPillPreview() {
    DayIndicatorPill(day = 22, month = "March")
}
