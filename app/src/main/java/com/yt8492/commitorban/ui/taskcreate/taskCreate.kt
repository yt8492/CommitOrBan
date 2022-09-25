package com.yt8492.commitorban.ui.taskcreate

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.TaskStackBuilder
import androidx.core.content.FileProvider
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.yt8492.commitorban.MainActivity
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.infra.LocalTaskRepository
import com.yt8492.commitorban.infra.PunishmentImage
import kotlinx.coroutines.launch
import java.io.File
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.util.UUID

@Composable
fun taskCreate(): TaskCreateResult {
    val context = LocalContext.current
    val repository = LocalTaskRepository.current
    val alarmManager = remember {
        context.getSystemService<AlarmManager>()
    }
    val coroutineScope = rememberCoroutineScope()
    val (done, setDone) = remember {
        mutableStateOf(false)
    }
    val (pendingTask, setPendingTask) = remember {
        mutableStateOf<Task?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if  (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if (data != null && pendingTask != null) {
                coroutineScope.launch {
                    repository.save(pendingTask)
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "commitorban://commitorban.yt8492.com/checkDone?id=${pendingTask.id}".toUri(),
                        context,
                        MainActivity::class.java
                    )
                    val pendingIntent = TaskStackBuilder.create(context).run {
                        addNextIntentWithParentStack(intent)
                        getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
                    }
                    alarmManager?.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        pendingTask.due.toEpochMilli(),
                        pendingIntent,
                    )
                    setDone(true)
                }
            }
        }
    }
    return TaskCreateResult(
        done = done,
        exec = { title, content, date, time ->
            val id = UUID.randomUUID().toString()
            launcher.launch(
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                    intent.resolveActivity(context.packageManager)?.also {
                        val file = File(context.getExternalFilesDir("images"), id)
                        val uri = FileProvider.getUriForFile(
                            context,
                            "com.yt8492.commitorban.fileprovider",
                            file
                        )
                        val due = date.atZone(ZoneId.systemDefault())
                            .withHour(time.hour)
                            .withMinute(time.minute)
                            .toInstant()
                        val task = Task(
                            id = id,
                            title = title,
                            content = content,
                            due = due,
                            punishment = PunishmentImage(uri),
                            done = false,
                        )
                        setPendingTask(task)
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    }
                }
            )
        }
    )
}

data class TaskCreateResult(
    val done: Boolean,
    val exec: (title: String, content: String, date: Instant, time: LocalTime) -> Unit,
)
