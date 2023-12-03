package com.example.uf1_proyecto_compose.presentation.common.inputs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme as appTheme


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
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        if (label.isNotEmpty()) {
            Text(text = label, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(5.dp))
        }
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

                N4ETextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    isError = true,
                    label = "Label",
                    errorMessage = "* Required Field"
                )
            }
        }
    }
}