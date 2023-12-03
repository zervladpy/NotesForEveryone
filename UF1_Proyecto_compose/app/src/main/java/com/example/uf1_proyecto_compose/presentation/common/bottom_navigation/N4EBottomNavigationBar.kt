package com.example.uf1_proyecto_compose.presentation.common.bottom_navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.app_navigation.Routes
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme

@Composable
fun N4EBottomNavigationBar(
    modifier: Modifier = Modifier,
    navItems: List<BottomNavItem>,
    navigateTo: (Routes) -> Unit
) {

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {

        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    navigateTo(item.route)
                },
                icon = {
                    Icon(
                        imageVector = if (selectedIndex == index) {
                            item.icon
                        } else item.outlinedIcon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {

    Notes4EveryoneTheme {
        N4EBottomNavigationBar(
            navItems = mainNavItems,
            navigateTo = {}
        )
    }

}