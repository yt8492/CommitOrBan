package com.yt8492.commitorban.ui.tasklist

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.yt8492.commitorban.domain.model.dummyTask1
import com.yt8492.commitorban.domain.model.dummyTask2

@Composable
fun TaskListPage(
    navController: NavController,
) {
    SideEffect {
        Log.d("hogehoge", "taskList")
    }
    val taskList = getAllTask()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "目標一覧")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("taskCreate")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "目標設定")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            items(taskList, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onClick = {
                        navController.navigate("taskDetail?taskId=${it.id}")
                    }
                )
            }
        }
    }
}