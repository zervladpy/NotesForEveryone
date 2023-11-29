package com.example.uf1_proyecto_compose.presentation.common.inputs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme as appTheme

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun N4ETextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    label: String = "",
    onEdit: (String) -> Unit = {},
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingAction: () -> Unit = {},
    isError: Boolean = false,
    isPassword: Boolean = false,
    errorMessage: String = "",
    isEditable: Boolean = true,
    maxLines: Int = 1,
) {

    val errorColor = MaterialTheme.colorScheme.error
    val defaultColor = MaterialTheme.colorScheme.onBackground
    val transparent = Color.Transparent

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    color = if (isError) {
                        errorColor
                    } else {
                        transparent
                    },
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp),
                ),
            value = value,
            textStyle = LocalTextStyle.current.copy(
                color = if (isError) {
                    errorColor
                } else {
                    defaultColor
                },
            ),
            onValueChange = onEdit,
            shape = RoundedCornerShape(10.dp),
            colors = textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedContainerColor = if (!isEditable) {
                    Color.Transparent
                } else textFieldColors().unfocusedContainerColor
            ),
            readOnly = !isEditable,
            maxLines = maxLines,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = ""
                    )
                }
            } else null,
            trailingIcon = if (trailingIcon != null) {
                {
                    IconButton(
                        onClick = trailingAction
                    ) {
                        Icon(
                            imageVector = trailingIcon,
                            tint = if (isError) {
                                errorColor
                            } else {
                                defaultColor
                            },
                            contentDescription = "",
                        )
                    }
                }
            } else null,
            placeholder = if (value.isEmpty()) {
                {
                    Text(
                        text = placeholder,
                        color = if (isError) {
                            errorColor
                        } else {
                            defaultColor
                        },
                    )
                }
            } else null,
            label = if (value.isNotEmpty() && label.isNotEmpty()) {
                {
                    Text(
                        text = label,
                        color = if (isError) {
                            errorColor
                        } else {
                            defaultColor
                        },
                    )
                }
            } else null,
            isError = isError,
            visualTransformation = if (isPassword) {
                PasswordVisualTransformation()
            } else VisualTransformation.None,
        )

        if (isError) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                text = errorMessage,
                style = LocalTextStyle.current.copy(
                    color = errorColor,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            )
        }


    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun InputTextFieldPreview() {
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

                N4ETextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                )

                N4ETextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    isError = true,
                    errorMessage = "* Required Field"
                )
            }
        }
    }
}