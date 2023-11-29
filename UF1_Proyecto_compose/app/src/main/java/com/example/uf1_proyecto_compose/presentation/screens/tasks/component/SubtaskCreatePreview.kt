package com.example.uf1_proyecto_compose.presentation.screens.tasks.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField

@Composable
fun SubtaskCreatePreview(
    modifier: Modifier = Modifier,
    subtask: Subtask,
    action: (Subtask) -> Unit,
) {

    N4ETextField(
        modifier = modifier,
        value = subtask.title,
        isEditable = false,
        trailingIcon = Icons.Rounded.Delete,
        trailingAction = { action(subtask) }
    )

}