package com.example.uf1_proyecto_compose.presentation.common.appbars

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.presentation.common.buttons.FabButton
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun BottomTaskAppbar(
    fabIcon: ImageVector,
    onFabClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {

    BottomAppBar(
        actions = actions,
        floatingActionButton = {
            FabButton(
                onClick = onFabClick,
                icon = fabIcon
            )
        }

    )

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomTaskAppbarPreview(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomTaskAppbar(
                fabIcon = Icons.Rounded.Create,
                actions = {

                }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomTaskAppbarPreviewWithActions(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomTaskAppbar(
                fabIcon = Icons.Rounded.Create,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "")
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "")
                    }
                }
            )
        }
    }
}