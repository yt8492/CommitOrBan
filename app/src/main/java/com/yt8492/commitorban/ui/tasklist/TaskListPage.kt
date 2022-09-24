package com.yt8492.commitorban.ui.tasklist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.yt8492.commitorban.domain.model.dummyTask1
import com.yt8492.commitorban.domain.model.dummyTask2

@Composable
fun TaskListPage(
    navController: NavController
) {
    val taskList = listOf(dummyTask1, dummyTask2)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "目標一覧")
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            items(taskList, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onClick = {}
                )
            }
        }
    }
}