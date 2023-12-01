package com.example.uf1_proyecto_compose.presentation.common.drawer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    navController: NavController,
    categories: List<MenuCategory>,
    closeDrawer: () -> Unit = {},
    actions: @Composable ColumnScope.() -> Unit = {},
) {

    var selected by remember { mutableStateOf(categories.first().items.first()) }

    ModalDrawerSheet(
        Modifier.width(240.dp)
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            this.apply { actions() }
        }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)

        ) {
            items(categories) { category ->
                Column {
                    Text(text = category.label)
                    category.items.forEach {
                        DrawerItem(
                            item = it,
                            isSelected = it == selected,
                            onSelect = {
                                selected = it
                                closeDrawer()
                                navController.navigate(it.route)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TemplatePreview(
    modifier: Modifier = Modifier,
) {
    Notes4EveryoneTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            DrawerContent(
                
            )
        }
    }
}