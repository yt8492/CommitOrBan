package com.yt8492.commitorban.infra

import android.net.Uri
import com.yt8492.commitorban.domain.model.Punishment

data class PunishmentImage(val uri: Uri) : Punishment
