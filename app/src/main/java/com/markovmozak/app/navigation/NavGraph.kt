package com.markovmozak.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.markovmozak.app.ui.addtask.AddTaskScreen
import com.markovmozak.app.ui.home.HomeScreen
import com.markovmozak.app.ui.shopping.ShoppingScreen
import com.markovmozak.app.ui.tasks.TasksScreen

object Routes {
    const val HOME = "home"
    const val ALL_TASKS = "all_tasks"
    const val ADD_TASK = "add_task"
    const val EDIT_TASK = "edit_task/{taskId}"
    const val SHOPPING = "shopping"

    fun editTask(taskId: Long) = "edit_task/$taskId"
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToAllTasks = { navController.navigate(Routes.ALL_TASKS) },
                onNavigateToAddTask = { navController.navigate(Routes.ADD_TASK) },
                onNavigateToShopping = { navController.navigate(Routes.SHOPPING) },
                onNavigateToEditTask = { taskId ->
                    navController.navigate(Routes.editTask(taskId))
                }
            )
        }

        composable(Routes.ALL_TASKS) {
            TasksScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddTask = { navController.navigate(Routes.ADD_TASK) },
                onNavigateToEditTask = { taskId ->
                    navController.navigate(Routes.editTask(taskId))
                }
            )
        }

        composable(Routes.ADD_TASK) {
            AddTaskScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.EDIT_TASK,
            arguments = listOf(navArgument("taskId") { type = NavType.LongType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getLong("taskId") ?: 0L
            AddTaskScreen(
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.SHOPPING) {
            ShoppingScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
