package com.example.uf1_proyecto_compose.presentation.common.drawer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    item: MenuItem,
    isSelected: Boolean = false,
    onSelect: () -> Unit = {},
) {
    NavigationDrawerItem(
        modifier = modifier.padding(top = 10.dp),
        icon = { Icon(imageVector = item.icon, contentDescription = item.description) },
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.label,
                textAlign = TextAlign.End
            )
        },
        selected = isSelected,
        onClick = onSelect,
        shape = RoundedCornerShape(7.dp)
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DrawerItemPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = modifier.padding(10.dp)) {
                Column {
                    DrawerItem(
                        item = MenuItem(
                            label = "Home",
                            description = "",
                            icon = Icons.Rounded.Home,
                            route = ""
                        )
                    )
                    DrawerItem(
                        item = MenuItem(
                            label = "Home",
                            description = "",
                            icon = Icons.Rounded.Home,
                            route = ""
                        ),
                        isSelected = true
                    )
                }
            }
        }
    }
}