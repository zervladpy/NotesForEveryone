package com.example.uf1_proyecto_compose.presentation.common.texts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppTitle(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier
            .padding()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {


        Text(
            text = "Notes4",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
        Text(
            text = "Everyone",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }

}