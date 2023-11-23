package com.example.uf1_proyecto_compose.presentation.common.appbars

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredAppbar(
    title: String = "",
    navIcon: ImageVector = Icons.Rounded.Menu,
    onNavigationIconClick: () -> Unit = {},
    navDescription: String = "open navigation panel",
    actions: @Composable (RowScope.() -> Unit) = {},
) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick,
            ) {
                Icon(
                    imageVector = navIcon,
                    contentDescription = navDescription
                )
            }
        },
        title = {
            if (title.isNotBlank()) {
                Text(text = title)
            }
        },
        actions = actions
    )

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Composable
fun CenterAppbarPreview() {
    CenteredAppbar(
        title = "Home"
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Composable
fun CenterAppbarPreviewWithActions() {
    CenteredAppbar(
        title = "Home",
        actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications"
            )
        }
    )
}
