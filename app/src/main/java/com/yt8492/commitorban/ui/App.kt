package com.yt8492.commitorban.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.yt8492.commitorban.ui.checkdone.CheckDonePage
import com.yt8492.commitorban.ui.login.HandleTwitterLogin
import com.yt8492.commitorban.ui.login.TwitterLoginPage
import com.yt8492.commitorban.ui.taskcreate.TaskCreatePage
import com.yt8492.commitorban.ui.taskdetail.TaskDetailPage
import com.yt8492.commitorban.ui.tasklist.TaskListPage

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "twitterLogin") {
        composable(
            route = "twitterLogin",
        ) {
            TwitterLoginPage()
        }
        composable(
            route = "handleTwitterLogin?oauthVerifier={oauthVerifier}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "commitorban://commitorban.yt8492.com/callback?oauth_verifier={oauthVerifier}"
                },
            ),
            arguments = listOf(
                navArgument(
                    name = "oauthVerifier",
                ) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val oauthVerifier = requireNotNull(backStackEntry.arguments?.getString("oauthVerifier"))
            HandleTwitterLogin(
                oauthVerifier = oauthVerifier,
                navController = navController,
            )
        }
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
        composable(
            route = "checkDone",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "commitorban://commitorban.yt8492.com/checkDone?id={id}"
                },
            ),
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val id = requireNotNull(backStackEntry.arguments?.getString("id"))
            CheckDonePage(
                id = id,
                navController = navController,
            )
        }
    }
}