package com.example.uf1_proyecto_compose.presentation.common.inputs

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelector(
    state: TimePickerState = rememberTimePickerState(),
) {

    TimePicker(
        state = state.apply { is24hour },
        layoutType = TimePickerLayoutType.Vertical
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TimeSelectorPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = modifier.padding(10.dp)) {
                TimeSelector()
            }
        }
    }
}