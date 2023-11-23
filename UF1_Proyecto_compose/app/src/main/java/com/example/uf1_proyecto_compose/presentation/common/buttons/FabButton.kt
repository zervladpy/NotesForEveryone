package com.example.uf1_proyecto_compose.presentation.common.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FabButton(
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
@Composable
fun FabButtonPreview() {
    FabButton(icon = Icons.Rounded.Add)
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "With Text")
@Composable
fun FabButtonWithTextPreview() {
    FabButton(
        icon = Icons.Rounded.Add,
        title = "Create Task"
    )
}