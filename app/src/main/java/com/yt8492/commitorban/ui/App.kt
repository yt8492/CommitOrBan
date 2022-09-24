package com.yt8492.commitorban.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yt8492.commitorban.ui.taskcreate.TaskCreatePage
import com.yt8492.commitorban.ui.taskdetail.TaskDetailPage
import com.yt8492.commitorban.ui.tasklist.TaskListPage

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "taskList") {
        composable(
            route = "taskList",
        ) {
            TaskListPage(
                navController = navController,
            )
        }
        composable(
            route = "taskDetail?taskId={taskId}",
            arguments = listOf(
                navArgument(
                    name = "taskId",
                ) {
                    type = NavType.StringType
                }
            ),
        ) { backStackEntry ->
            val taskId = requireNotNull(backStackEntry.arguments?.getString("taskId"))
            TaskDetailPage(
                taskId = taskId,
                navController = navController,
            )
        }
        composable(
            route = "taskCreate"
        ) {
            TaskCreatePage(
                navController = navController,
            )
        }
    }
}