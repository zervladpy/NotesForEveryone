package com.example.uf1_proyecto_compose.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onChange: (String) -> Unit,
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = { onChange(it) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
            )
        },
        label = { Text(text = "Search") }
    )

}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    var searchText: String by remember { mutableStateOf("") }

    SearchBar(
        searchText = searchText,
        onChange = { searchText = it }
    )
}