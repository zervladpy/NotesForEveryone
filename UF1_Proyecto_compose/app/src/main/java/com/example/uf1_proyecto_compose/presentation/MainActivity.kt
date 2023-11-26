package com.example.uf1_proyecto_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uf1_proyecto_compose.presentation.navigation.auth.AuthNavigation
import com.example.uf1_proyecto_compose.presentation.navigation.main.MainNavigation
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.auth.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UF1_Proyecto_composeTheme {
                AuthWrapper()
            }
        }
    }
}

@Composable
fun AuthWrapper(
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val state = authViewModel.state.value

    Surface {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            if (state.isAuthenticated) {
                MainNavigation()
            } else {
                AuthNavigation()
            }
        }
    }

}