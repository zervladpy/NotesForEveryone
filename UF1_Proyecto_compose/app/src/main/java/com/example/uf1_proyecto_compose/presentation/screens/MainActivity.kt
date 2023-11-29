package com.example.uf1_proyecto_compose.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.uf1_proyecto_compose.domain.model.toDomain
import com.example.uf1_proyecto_compose.presentation.navigation.auth.AuthNavigation
import com.example.uf1_proyecto_compose.presentation.navigation.main.MainNavigation
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.utils.AuthState.Authenticated
import com.example.uf1_proyecto_compose.utils.AuthState.UnAuthenticated
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), FirebaseAuth.AuthStateListener {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window, true
        )

        val authState = authViewModel.state

        setContent {


            UF1_Proyecto_composeTheme {
                when (authState.value) {
                    is Authenticated -> {
                        MainNavigation()
                    }

                    is UnAuthenticated -> {
                        AuthNavigation()
                    }
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(this)
    }

    override fun onDestroy() {
        FirebaseAuth.getInstance().removeAuthStateListener(this)
        super.onDestroy()
    }

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        authViewModel.changeAuthState(auth.currentUser?.toDomain())
    }
}