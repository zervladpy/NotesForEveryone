package com.example.uf1_proyecto_compose.presentation.viewmodels.profile

import android.net.Uri

sealed class ProfileEvent(
    val displayName: String? = null,
    val profileUri: Uri = Uri.EMPTY,
) {
    class UpdateDisplayName(displayName: String) : ProfileEvent(displayName = displayName)
    class UpdateProfilePic(profileUri: Uri) : ProfileEvent(profileUri = profileUri)
    class ToggleEdit : ProfileEvent()
    class Save : ProfileEvent()
}
