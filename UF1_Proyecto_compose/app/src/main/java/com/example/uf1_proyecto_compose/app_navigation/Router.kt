package com.example.uf1_proyecto_compose.app_navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.screens.auth.LoginScreen
import com.example.uf1_proyecto_compose.presentation.screens.auth.SignUpScreen
import com.example.uf1_proyecto_compose.presentation.screens.landing.LandingScreen
import com.example.uf1_proyecto_compose.presentation.screens.splash.SplashScreen
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.AuthViewModel

@Composable
fun Router() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Auth.route,
    ) {

        // user not authenticated
        navigation(
            route = Routes.Auth.route,
            startDestination = Routes.Splash.route,
        ) {

            composable(
                route = Routes.Splash.route
            ) {

                val viewModel: AuthViewModel = it.sharedViewModel(navController)

                SplashScreen(
                    authState = viewModel,
                    navigateHome = {
                        navController.navigate(
                            Routes.Home.route,
                        ) {
                            popUpTo(Routes.Auth.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToLanding = {
                        navController.navigate(Routes.Landing.route)
                    }
                )
            }

            composable(
                route = Routes.Landing.route
            ) {
                val viewModel: AuthViewModel = it.sharedViewModel(navController)

                LandingScreen(
                    viewModel = viewModel,
                    navigateToLogin = {
                        navController.navigate(
                            Routes.Login.route
                        )
                    },
                    navigateToRegister = {
                        navController.navigate(
                            Routes.Signup.route
                        )
                    },
                    navigateToHome = {
                        navController.navigate(
                            Routes.Home.route
                        ) {
                            popUpTo(Routes.Auth.route) {
                                inclusive = true
                            }
                        }
                    },
                )
            }

            composable(
                route = Routes.Login.route
            ) {
                val viewModel: AuthViewModel = it.sharedViewModel(navController)

                LoginScreen(
                    viewModel = viewModel,
                    navigateBack = {
                        navController.navigate(Routes.Landing.route) {
                            popUpTo(Routes.Auth.route)
                        }
                    },
                    navigateToSignup = {
                        navController.navigate(Routes.Signup.route)
                    },
                    navigateToHome = {
                        navController.navigate(
                            Routes.Home.route
                        ) {
                            popUpTo(Routes.Auth.route) {
                                inclusive = true
                            }
                        }
                    }
                )

            }

            composable(
                route = Routes.Signup.route
            ) {
                val viewModel: AuthViewModel = it.sharedViewModel(navController)

                SignUpScreen(
                    viewModel = viewModel,
                    navigateBack = {
                        navController.navigate(Routes.Landing.route) {
                            popUpTo(Routes.Auth.route)
                        }
                    },
                    navigateToLogin = {
                        navController.navigate(Routes.Login.route)
                    },
                    navigateToHome = {
                        navController.navigate(
                            Routes.Home.route
                        ) {
                            popUpTo(Routes.Auth.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        // user authenticated
        navigation(
            route = Routes.Home.route,
            startDestination = Routes.Dashboard.route,
        ) {

            composable(
                route = Routes.Dashboard.route
            ) {

                Text(text = "Authenticated")

            }

            navigation(
                startDestination = Routes.TaskList.route,
                route = Routes.Tasks.route
            ) {

                val tasksViewModel: ViewModel

                composable(
                    route = Routes.TaskList.route
                ) {}

                composable(
                    route = Routes.TaskCreate.route
                ) { }

                composable(
                    route = Routes.TaskDetail.route
                ) {}
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
