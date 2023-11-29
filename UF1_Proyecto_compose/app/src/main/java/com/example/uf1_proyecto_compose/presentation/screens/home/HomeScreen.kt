package com.example.uf1_proyecto_compose.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    openDrawer: () -> Unit = {},
) {
    Scaffold(
        topBar = { HomeAppbar(openDrawer = openDrawer) },
        content = { HomeContent(modifier.padding(it)) }
    )
}

@Composable
private fun HomeAppbar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(

        ) {
            CenteredAppbar(
                onNavigationIconClick = openDrawer,

                )

            Column(
                modifier = Modifier.padding(
                    horizontal = 15.dp,
                    vertical = 10.dp
                )
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "May 23, 2023", // TODO(Set true date)
                    style = MaterialTheme.typography.titleSmall
                )

                N4ETextField(
                    placeholder = "Search",
                    trailingIcon = Icons.Rounded.Search,
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        /// TODO(Total Task Progression indicator)

        /// TODO(Show )

    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                navController = rememberNavController()
            )
        }
    }
}