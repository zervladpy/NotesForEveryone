package com.example.uf1_proyecto_compose.presentation.common.appbars

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.presentation.common.buttons.FabButton

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

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomTaskAppbarPreview() {
    BottomTaskAppbar(
        fabIcon = Icons.Rounded.Create,
        actions = {
            
        }
    )
}
