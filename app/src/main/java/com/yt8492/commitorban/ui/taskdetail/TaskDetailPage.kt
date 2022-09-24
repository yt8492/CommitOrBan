package com.yt8492.commitorban.ui.taskdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.navigation.NavController
import com.yt8492.commitorban.infra.PunishmentImage
import com.yt8492.commitorban.infra.Twitter
import com.yt8492.commitorban.ui.theme.CommitOrBanTheme
import com.yt8492.commitorban.util.TimeTickSecEventReceiver
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit

@Composable
fun TaskDetailPage(
    taskId: String,
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val task = getTask(id = taskId) ?: return
    val (now, setNow) = remember {
        mutableStateOf(Instant.now())
    }
    TimeTickSecEventReceiver {
        setNow(Instant.now())
    }
    val seconds = ChronoUnit.SECONDS.between(now, task.due)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = task.title)
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
                .fillMaxSize(),
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
                Text(text = task.content)
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "期限")
                Spacer(modifier = Modifier.height(16.dp))
                val dueText = when {
                    seconds < 0 -> "残り0秒"
                    seconds > 1 * DAY -> "残り${seconds / DAY}日"
                    seconds > 1 * HOUR -> "残り${seconds / HOUR}時間"
                    seconds > 1 * MINUTE -> "残り${seconds / MINUTE}分"
                    else -> "残り${seconds}秒"
                }
                Text(text = dueText)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        val punishment = task.punishment as PunishmentImage
                        val inputStream = context.contentResolver.openInputStream(punishment.uri) ?: return@launch
                        Twitter.tweet("test", inputStream)
                    }
                },
                enabled = seconds >= 0,
            ) {
                Text(
                    text = "達成",
                    style = CommitOrBanTheme.typography.h5,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

private const val SECOND = 1
private const val MINUTE = 1 * SECOND * 60
private const val HOUR = 1 * MINUTE * 60
private const val DAY = 1 * HOUR * 24

