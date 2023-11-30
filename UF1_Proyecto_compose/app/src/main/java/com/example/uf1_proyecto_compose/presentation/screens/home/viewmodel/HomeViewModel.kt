package com.example.uf1_proyecto_compose.presentation.screens.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    getTasks: GetTasks,
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getTasks().onEach {

            when (it) {
                is Response.Loading -> {

                    _state.value = state.value.copy(isLoading = true)
                }

                is Response.Error -> {

                    _state.value = state.value.copy(isLoading = false)
                }


                is Response.Success -> {

                    _state.value = state.value.copy(isLoading = false)
                }
            }

        }.launchIn(viewModelScope)

    }

}