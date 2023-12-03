package com.example.uf1_proyecto_compose.presentation.navigation.router

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.screens.auth.AuthScreen
import com.example.uf1_proyecto_compose.presentation.screens.error.ErrorScreen
import com.example.uf1_proyecto_compose.presentation.screens.main.MainScreen
import com.example.uf1_proyecto_compose.presentation.screens.splash.SplashScreen
import com.example.uf1_proyecto_compose.presentation.screens.tasks.TaskCreateScreen
import com.example.uf1_proyecto_compose.presentation.screens.tasks.TaskDetailScreen
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.create_task.CreateTaskViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.detail_task.DetailTaskViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksViewModel

@Composable
fun Router(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Routes.Root.route,
    ) {

        navigation(
            startDestination = Routes.Splash.route,
            route = Routes.Root.route
        ) {

            composable(
                route = Routes.Splash.route,
            ) {

                val authViewModel: AuthViewModel = it.sharedViewModel(navController)
                val sharedTasksViewModel: SharedTasksViewModel = it.sharedViewModel(navController)

                SplashScreen(
                    authState = authViewModel.state.value,
                    sharedTasksState = sharedTasksViewModel.state.value,
                    navigateHome = {
                        navController.navigate(Routes.Home.route) {
                            popUpTo("/") { inclusive = true }
                        }
                    },
                    navigateToLanding = {
                        navController.navigate(Routes.Auth.route) {
                            popUpTo(Routes.Root.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Routes.Auth.route
            ) {

                val authViewModel: AuthViewModel = it.sharedViewModel(navController)

                AuthScreen(
                    modifier = Modifier,
                    authState = authViewModel.state.value,
                    onAuthEvent = authViewModel::onEvent,
                    navigateToMain = {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Root.route)
                        }
                    }
                )
            }

            // user authenticated
            navigation(
                route = Routes.Home.route,
                startDestination = Routes.TaskList.route,
            ) {

                composable(
                    route = Routes.TaskList.route
                ) {

                    val sharedTasksViewModel: SharedTasksViewModel =
                        it.sharedViewModel(navController)
                    val authViewModel: AuthViewModel = it.sharedViewModel(navController)

                    val profileViewModel: ProfileViewModel = it.sharedViewModel(navController)

                    val sharedState = sharedTasksViewModel.state

                    Log.d("Router", sharedTasksViewModel.state.value.tasks.toString())

                    MainScreen(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .statusBarsPadding(),
                        sharedTasksState = sharedState.value,
                        onSharedTaskEvent = sharedTasksViewModel::onEvent,
                        onLogout = {
                            authViewModel.onEvent(AuthEvent.LogOut())
                            navController.navigate(Routes.Root.route) {
                                popUpTo(Routes.Root.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToTaskDetail = {
                            navController.navigate(it)
                        },
                        navigateToCreateTask = {
                            navController.navigate(Routes.TaskCreate.route)
                        },
                        profileState = profileViewModel.state.value,
                        onProfileEvent = profileViewModel::onEvent
                    )
                }

                composable(
                    route = Routes.TaskDetail.route,
                ) {
                    val args = it.arguments?.getString("uid") ?: ""
                    val sharedTasksViewModel: SharedTasksViewModel =
                        it.sharedViewModel(navController)
                    val detailTaskViewModel: DetailTaskViewModel = createViewModel()
                    if (args.isEmpty()) {
                        ErrorScreen(
                            navigateBack = {
                                navController.popBackStack(Routes.TaskList.route, true)
                            }
                        )
                    } else {

                        val task =
                            sharedTasksViewModel.state.value.tasks.find { task -> task.uid == args }

                        if (task == null) {

                            ErrorScreen(
                                navigateBack = {
                                    navController.popBackStack(Routes.TaskList.route, true)
                                }
                            )

                        } else {
                            if (detailTaskViewModel.state.value.task.uid.isEmpty()) {
                                detailTaskViewModel.setInitialValues(
                                    task = task
                                )
                            }

                            Log.d("TaskDetailScreen", "rebuild")

                            TaskDetailScreen(
                                modifier = Modifier,
                                state = detailTaskViewModel.state.value,
                                onTaskDetailEvent = detailTaskViewModel::onEvent,
                                onSharedTasksEvent = sharedTasksViewModel::onEvent,
                                navigateBack = {
                                    navController.navigate(route = Routes.TaskList.route) {
                                        popUpTo(Routes.Root.route)
                                    }
                                }
                            )
                        }
                    }
                }

                composable(
                    route = Routes.TaskCreate.route,
                ) {

                    val sharedTasksViewModel: SharedTasksViewModel =
                        it.sharedViewModel(navController)
                    Log.d("Router", sharedTasksViewModel.state.value.tasks.toString())
                    val createTaskViewModel: CreateTaskViewModel = createViewModel()

                    TaskCreateScreen(
                        state = createTaskViewModel.state.value,
                        onCreateTaskViewModelEvent = createTaskViewModel::onEvent,
                        onTasksViewModelAddEvent = sharedTasksViewModel::onEvent,
                        navigateBack = { navController.popBackStack() },
                        navigateToNewTask = { uid -> navController.navigate(uid) },
                    )
                }
            }
        }

    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    Log.d(navGraphRoute, "Shared Tasks ViewModel")
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

/**
 * Creates a Hilt View Model
 * */
@Composable
inline fun <reified T : ViewModel> createHiltViewModel(): T {
    return hiltViewModel()
}

/**
 * Create a default View Model
 * */
@Composable
inline fun <reified T : ViewModel> createViewModel(): T {
    return viewModel()
}