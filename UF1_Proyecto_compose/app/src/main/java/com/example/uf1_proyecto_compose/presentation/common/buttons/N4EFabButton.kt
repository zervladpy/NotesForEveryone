package com.example.uf1_proyecto_compose.presentation.common.buttons

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme

@Composable
fun N4EFabButton(
    title: String = "",
    icon: ImageVector,
    description: String = "",
    onClick: () -> Unit = {},
) {

    FloatingActionButton(
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(imageVector = icon, contentDescription = description)
            if (title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = 7.dp),
                    text = title
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun N4EFabButtonPreview(
    modifier: Modifier = Modifier
) {
    Notes4EveryoneTheme {
        Surface(

            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                N4EFabButton(icon = Icons.Rounded.Add)
                Spacer(modifier = Modifier.height(10.dp))
                N4EFabButton(
                    icon = Icons.Rounded.Add,
                    title = "Create Task"
                )
            }
        }
    }
}