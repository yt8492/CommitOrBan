package com.yt8492.commitorban.ui.tasklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.domain.model.dummyTask1
import com.yt8492.commitorban.ui.theme.CommitOrBanTheme
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun TaskItem(
    task: Task,
    onClick: (Task) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(task) },
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = task.title)
            Spacer(modifier = Modifier.weight(1f))
            val dueText = formatter.format(task.due)
            Text(text = dueText)
        }
        Divider()
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    CommitOrBanTheme {
        TaskItem(task = dummyTask1) {

        }
    }
}

private val formatter = DateTimeFormatter
    .ofPattern("yyyy/MM/dd hh:mm")
    .withZone(ZoneId.systemDefault())