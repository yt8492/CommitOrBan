package com.yt8492.commitorban.ui.checkdone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.yt8492.commitorban.infra.LocalTaskRepository
import com.yt8492.commitorban.infra.PunishmentImage
import com.yt8492.commitorban.infra.Twitter

@Composable
fun CheckDonePage(
    id: String,
    navController: NavController,
) {
    val repository = LocalTaskRepository.current
    val context = LocalContext.current
    val task = repository.get(id).collectAsState(initial = null).value ?: return
    LaunchedEffect(task) {
        if (!task.done) {
            val text = """
                私は目標「${task.title}」を期限までに達成できませんでした。
                #ishinomakihack
                #石巻ハッカソン
            """.trimIndent()
            val punishmentImage = task.punishment as PunishmentImage
            val media = context.contentResolver.openInputStream(punishmentImage.uri)
            Twitter.tweet(text, media)
        }
        navController.navigate("taskList") {
            launchSingleTop = true
        }
    }
}
