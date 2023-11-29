package com.example.uf1_proyecto_compose.presentation.common.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun N4EButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(7.dp),
        enabled = enabled
    ) {
        Text(text = text)
    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun N4EButtonPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = modifier.padding(10.dp)) {

                N4EButton(text = "Click Me", onClick = {})
                N4EButton(text = "Click Me", onClick = {}, enabled = false)

            }
        }
    }
}
