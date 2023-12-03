package com.example.uf1_proyecto_compose.app_navigation.auth_router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.app_navigation.router.createViewModel
import com.example.uf1_proyecto_compose.presentation.screens.auth.LandingScreen
import com.example.uf1_proyecto_compose.presentation.screens.auth.LoginScreen
import com.example.uf1_proyecto_compose.presentation.screens.auth.SignUpScreen
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.login.LoginViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup.SignUpViewModel

@Composable
fun AuthRouter(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onAuthEvent: (AuthEvent) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = AuthRoutes.Landing.route
    ) {

        composable(
            route = AuthRoutes.Landing.route
        ) {

            LandingScreen(
                modifier = modifier,
                navigateToLogin = {
                    navController.navigate(AuthRoutes.Login.route)
                },
                navigateToRegister = {
                    navController.navigate(AuthRoutes.SignUp.route)
                },
            )
        }

        composable(
            route = AuthRoutes.Login.route
        ) {

            val loginViewModel: LoginViewModel = createViewModel()

            LoginScreen(
                loginState = loginViewModel.state.value,
                onAuthEvent = onAuthEvent,
                onEvent = loginViewModel::onEvent,
                navigateBack = {
                    navController.popBackStack()
                }
            )

        }

        composable(
            route = AuthRoutes.SignUp.route
        ) {
            val signUpViewModel: SignUpViewModel = createViewModel()

            SignUpScreen(
                modifier = modifier,
                onAuthEvent = onAuthEvent,
                onEvent = signUpViewModel::onEvent,
                signupState = signUpViewModel.state.value,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}