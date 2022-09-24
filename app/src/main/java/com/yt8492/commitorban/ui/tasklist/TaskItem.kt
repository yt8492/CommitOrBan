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
import java.time.Instant
import java.time.temporal.ChronoUnit

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
            val minutes = ChronoUnit.MINUTES.between(Instant.now(), task.due)
            val dueText = when {
                minutes > 1 * DAY -> "残り${minutes / DAY}日"
                minutes > 1 * HOUR -> "残り${minutes / HOUR}時間"
                else -> "残り${minutes / MINUTE}分"
            }
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

private const val MINUTE = 1
private const val HOUR = 1 * MINUTE * 60
private const val DAY = 1 * HOUR * 24
