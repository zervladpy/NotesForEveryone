package com.example.uf1_proyecto_compose.presentation.viewmodels.profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import com.example.uf1_proyecto_compose.domain.use_case.profile.GetProfilePicture
import com.example.uf1_proyecto_compose.domain.use_case.profile.UpdateProfile
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUser: GetCurrentUserUseCase,
    private val updateProfilePic: UpdateProfile,
    private val getProfilePicture: GetProfilePicture
) : ViewModel() {

    private val _prev = mutableStateOf(ProfileState())
    val prev: State<ProfileState> = _prev

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        onInit()
        Log.d("PROFILE PHOTO", state.value.user.photoUrl.toString())
    }

    private fun onInit() {
        _state.value = state.value.copy(
            user = getUser()
        )

        getProfilePicture(state.value.user.uid).onEach {
            when (it) {
                is Response.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }

                is Response.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Response.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        user = state.value.user.copy(
                            photoUrl = it.data ?: Uri.EMPTY
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateDisplayName -> updateDisplayName(event.displayName!!)
            is ProfileEvent.UpdateProfilePic -> updateProfilePic(event.profileUri)
            is ProfileEvent.Save -> save()
            is ProfileEvent.ToggleEdit -> toggleEditing()
        }
    }

    private fun updateDisplayName(value: String) {
        _prev.value = state.value
        _state.value = state.value.copy(
            user = state.value.user.copy(
                displayName = value
            )
        )

    }

    private fun updateProfilePic(uri: Uri) {
        _prev.value = state.value
        _state.value = state.value.copy(
            user = state.value.user.copy(
                photoUrl = uri
            )
        )
    }

    private fun toggleEditing() {
        _state.value = state.value.copy(
            isEditing = !state.value.isEditing
        )
    }

    private fun save() {
        updateProfilePic(
            state.value.user.displayName,
            state.value.user.photoUrl
        ).launchIn(viewModelScope)
    }

}