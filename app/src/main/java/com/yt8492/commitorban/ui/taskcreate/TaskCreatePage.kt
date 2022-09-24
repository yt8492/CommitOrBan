package com.yt8492.commitorban.ui.taskcreate

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yt8492.commitorban.ui.theme.CommitOrBanTheme
import com.yt8492.commitorban.util.datePicker
import com.yt8492.commitorban.util.timePicker
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun TaskCreatePage(
    navController: NavController,
) {
    val context = LocalContext.current
    val (title, setTitle) = remember {
        mutableStateOf("")
    }
    val (content, setContent) = remember {
        mutableStateOf("")
    }
    val (dueDate, showDueDatePicker) = datePicker(initialDate = Instant.now())
    val (dueTime, showDueTimePicker) = timePicker(initialTime = LocalTime.of(0, 0))
    val (done, taskCreate) = taskCreate()
    LaunchedEffect(done) {
        if (done) {
            Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "目標設定")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "戻る",
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "目標",
                    style = CommitOrBanTheme.typography.subtitle1,
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = title,
                    onValueChange = setTitle,
                    placeholder = {
                        Text(text = "タイトル")
                    },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = content,
                    onValueChange = setContent,
                    placeholder = {
                        Text(text = "詳細")
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "期限")
                Spacer(modifier = Modifier.height(16.dp))
                if (dueDate != null) {
                    val dateText = formatter.format(dueDate)
                    val timeText = "%02d:%02d".format(dueTime?.hour ?: 0, dueTime?.minute ?: 0)
                    Row {
                        TextButton(onClick = { showDueDatePicker() }) {
                            Text(
                                text = dateText
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(onClick = { showDueTimePicker() }) {
                            Text(text = timeText)
                        }
                    }
                } else {
                    Row {
                        TextButton(onClick = { showDueDatePicker() }) {
                            Text(text = "日付を選択")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(onClick = { showDueTimePicker() }) {
                            Text(text = "時間を選択")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    if (title.isNotBlank() && dueDate != null && dueTime != null) {
                        taskCreate(title, content, dueDate, dueTime)
                    }
                },
                enabled = title.isNotBlank() && dueDate != null && dueTime != null,
            ) {
                Text(
                    text = "目標を設定する",
                    style = CommitOrBanTheme.typography.h5,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

private val formatter = DateTimeFormatter
    .ofPattern("yyyy/MM/dd")
    .withZone(ZoneId.systemDefault())