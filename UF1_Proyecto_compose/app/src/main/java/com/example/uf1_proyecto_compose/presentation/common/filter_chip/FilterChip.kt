package com.example.uf1_proyecto_compose.presentation.common.filter_chip

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme

@Composable
fun FilterChip(
    filter: FilterModel,
    isSelected: Boolean,
    onSelect: () -> Unit = {},
) {

    InputChip(
        selected = isSelected,
        onClick = onSelect,
        label = {
            Text(text = filter.label)
        }
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FilterChipPreview(
    modifier: Modifier = Modifier,
) {
    Notes4EveryoneTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                FilterChip(
                    filter = FilterModel(label = "Filter"),
                    onSelect = {},
                    isSelected = false
                )
                FilterChip(
                    filter = FilterModel(label = "Filter"),
                    onSelect = {},
                    isSelected = true
                )
            }
        }
    }
}