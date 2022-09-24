package com.yt8492.commitorban.infra.room

import androidx.room.TypeConverter
import java.time.Instant

class InstantConverter {

    @TypeConverter
    fun fromTimestamp(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }

    @TypeConverter
    fun toTimestamp(instant: Instant?): String? {
        return instant?.toString()
    }
}
