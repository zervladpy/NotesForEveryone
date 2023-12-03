package com.example.uf1_proyecto_compose.presentation.screens.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.ModeEdit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileState

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    profileState: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
    logout: () -> Unit
) {

    Scaffold(
        topBar = { Appbar(logout = logout) },
        content = {
            Content(
                modifier = modifier
                    .padding(it)
                    .padding(
                        horizontal = 20.dp
                    )
                    .fillMaxWidth()
                    .fillMaxSize()
                    .navigationBarsPadding(),
                profileState = profileState,
                onEvent = onEvent
            )
        },
        floatingActionButton = {
            FabButton(
                profileState = profileState,
                onEvent = onEvent
            )
        }
    )

}

@Composable
fun FabButton(
    profileState: ProfileState,
    onEvent: (ProfileEvent) -> Unit
) {
    FloatingActionButton(
        onClick = {

            if (profileState.isEditing) {
                onEvent(ProfileEvent.Save())
            }

            onEvent(ProfileEvent.ToggleEdit())

        }
    ) {
        if (profileState.isEditing) {
            Icon(
                imageVector = Icons.Rounded.Done,
                contentDescription = "Save Profile",
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.ModeEdit,
                contentDescription = "Edit Profile",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    logout: () -> Unit = {}
) {

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.settings_screen_title))
        },
        actions = {
            IconButton(onClick = logout) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ExitToApp,
                    contentDescription = "Logout"
                )
            }
        }
    )

}

@Composable
private fun Content(
    modifier: Modifier,
    profileState: ProfileState,
    onEvent: (ProfileEvent) -> Unit
) {

    val selectPhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            onEvent(ProfileEvent.UpdateProfilePic(uri ?: Uri.EMPTY))
        }
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(20.dp),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clickable {
                            if (profileState.isEditing) {
                                selectPhotoLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        },
                    model = if (profileState.user.photoUrl.path.isNullOrEmpty()) {
                        Surface(
                            modifier = Modifier
                                .width(150.dp)
                                .height(150.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = "Profile Photo"
                            )
                        }
                    } else {
                        profileState.user.photoUrl
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = "Profile Photo",
                )
            }

        }

        N4ETextField(
            label = stringResource(id = R.string.email_placeholder),
            value = profileState.user.email,
            isEditable = false
        )
        N4ETextField(
            label = stringResource(id = R.string.name_label),
            value = profileState.user.displayName,
            isEditable = profileState.isEditing,
            onEdit = {
                onEvent(ProfileEvent.UpdateDisplayName(it))
            }
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {

    Notes4EveryoneTheme {
        SettingsScreen(
            profileState = ProfileState(
                user = User(
                    email = "email@email.com",
                    displayName = "Andrei Makarov"
                )
            ),
            onEvent = {},
            logout = {}
        )
    }

}