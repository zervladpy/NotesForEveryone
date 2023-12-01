package com.example.uf1_proyecto_compose.presentation.common.inputs

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    datePickerState: DatePickerState = rememberDatePickerState(),
    label: String = "Select a Date",
) {

    DatePicker(
        state = datePickerState,
        title = {},
        headline = {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = label,
                textAlign = TextAlign.Center
            )
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DateSelectorPreview(
    modifier: Modifier = Modifier,
) {
    Notes4EveryoneTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = modifier.padding(10.dp)) {
                DateSelector()
            }
        }
    }
}