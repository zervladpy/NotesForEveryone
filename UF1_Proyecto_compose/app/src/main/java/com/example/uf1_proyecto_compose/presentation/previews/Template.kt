package com.example.uf1_proyecto_compose.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TemplatePreview(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = modifier.padding(10.dp)) {

            }
        }
    }
}