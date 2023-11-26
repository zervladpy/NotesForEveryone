package com.example.uf1_proyecto_compose.presentation.common.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun N4EProviderButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    contentDescription: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(7.dp),
        enabled = enabled,

        ) {

        if (icon != null) {
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = icon,
                contentDescription = contentDescription
            )

        }
        Text(text = text)
    }

}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun N4EProviderButtonPreview(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = modifier.padding(10.dp)) {

                N4EProviderButton(text = "Provider Button", onClick = {})
                N4EProviderButton(text = "Provider Button", icon = Icons.Rounded.Info, onClick = {})
                N4EProviderButton(text = "Provider Button", onClick = {}, enabled = false)
                N4EProviderButton(
                    text = "Provider Button",
                    icon = Icons.Rounded.Info,
                    onClick = {},
                    enabled = false
                )
            }
        }
    }
}
