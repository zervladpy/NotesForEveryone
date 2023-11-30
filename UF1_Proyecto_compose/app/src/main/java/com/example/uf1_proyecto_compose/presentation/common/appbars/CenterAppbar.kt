package com.example.uf1_proyecto_compose.presentation.common.appbars

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            Color.Transparent
        ),
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
        actions = actions,
    )

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CenterAppbarPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            CenteredAppbar(
                title = "Home"
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CenterAppbarPreviewWithActions(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
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
    }
}
