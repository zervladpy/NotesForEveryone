package com.example.uf1_proyecto_compose.componets.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    navigationController: NavController,
) {

    val menuItems: List<NavDrawerItem> = listOf(
        NavDrawerItem.Dashboard,
        NavDrawerItem.Calendar
    )

    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
    ) {
        Column {
            DrawerHeader()
            DrawerBody(
                menuItems = menuItems,
                onClick = {
                    navigationController.navigate(it.route)
                }
            )
        }
    }

}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier.padding(7.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(7.dp)
                    )
            ) {
                /// Profile Image

            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
            ) {
                Text("Vlad", fontSize = 20.sp)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(
    menuItems: List<NavDrawerItem>,
    onClick: (NavDrawerItem) -> Unit,
) {
    LazyColumn {
        items(menuItems) {
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.desc
                    )
                },
                label = {
                    Text(text = it.label)
                },
                selected = false,
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun MainDrawerPreview() {
    MainDrawer(
        navigationController = rememberNavController()
    )
}