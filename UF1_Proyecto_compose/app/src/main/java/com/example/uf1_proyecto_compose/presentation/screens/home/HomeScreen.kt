package com.example.uf1_proyecto_compose.presentation.screens.home

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.screens.home.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    openDrawer: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
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