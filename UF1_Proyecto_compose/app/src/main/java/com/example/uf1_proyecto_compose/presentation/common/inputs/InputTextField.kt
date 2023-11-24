package com.example.uf1_proyecto_compose.presentation.common.inputs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme as appTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextFieldWithLabel(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    onEdit: (String) -> Unit = {},
    isEditable: Boolean = true,
    maxLines: Int = 1,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(text = label) },
        isError = isError,
        onValueChange = onEdit,
        maxLines = maxLines,
        supportingText = {
            if (isError) {
                Text(
                    text = errorText,
                    style = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.error
                    ),
                )
            }
        },
        enabled = isEditable
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextFieldWithPlaceHolder(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String,
    isError: Boolean = false,
    errorText: String = "",
    onEdit: (String) -> Unit = {},
    isEditable: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        placeholder = { Text(text = placeholder) },
        isError = isError,
        onValueChange = onEdit,
        supportingText = {
            if (isError) {
                Text(
                    text = errorText,
                    style = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.error
                    ),
                )
            }
        },
        enabled = isEditable
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun InputTextFieldPreview() {
    appTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                InputTextFieldWithLabel(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    label = "Label",
                    value = "Text Value",
                    onEdit = {}
                )

                InputTextFieldWithLabel(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    label = "Label",
                    value = "Text Value",
                    onEdit = {},
                    isError = true,
                    errorText = "* Required Field"
                )

                InputTextFieldWithPlaceHolder(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    placeholder = "Placeholder",
                    value = "",
                    onEdit = {}
                )

                InputTextFieldWithPlaceHolder(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    placeholder = "Placeholder",
                    value = "",
                    isError = true,
                    errorText = "* Required field",
                    onEdit = {}
                )
            }
        }
    }
}