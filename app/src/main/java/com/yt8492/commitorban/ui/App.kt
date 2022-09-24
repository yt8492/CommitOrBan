package com.yt8492.commitorban.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yt8492.commitorban.ui.tasklist.TaskListPage

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/taskList") {
        composable("/taskList") {
            TaskListPage(
                navController = navController,
            )
        }
    }
}