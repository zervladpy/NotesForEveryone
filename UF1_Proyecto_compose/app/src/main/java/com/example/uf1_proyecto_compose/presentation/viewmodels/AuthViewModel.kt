package com.example.uf1_proyecto_compose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {


    fun loginWithEmailAndPassword(email: String, password: String) {}

    fun registerWithEmailAndPassword(email: String, password: String) {}

}