package com.yt8492.commitorban.ui.taskdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yt8492.commitorban.domain.model.Task
import com.yt8492.commitorban.infra.LocalTaskRepository
import com.yt8492.commitorban.infra.Twitter

@Composable
fun doneTask(): DoneTaskResult {
    val repository = LocalTaskRepository.current
    val (finished, setFinished) = remember {
        mutableStateOf(false)
    }
    return DoneTaskResult(
        finished = finished,
        done = {
            repository.save(it.copy(done = true))
            val text = """
                目標「${it.title}」を達成しました！
                #ishinomakihack
                #石巻ハッカソン
            """.trimIndent()
            Twitter.tweet(text)
            setFinished(true)
        }
    )
}

data class DoneTaskResult(
    val finished: Boolean,
    val done: suspend (Task) -> Unit,
)
