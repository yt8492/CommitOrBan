package com.yt8492.commitorban.util

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalTime

@Composable
fun datePicker(
    initialDate: Instant?
): DatePickerResult {
    val context = LocalContext.current as AppCompatActivity
    val (date, setDate) = remember {
        mutableStateOf(initialDate)
    }
    return DatePickerResult(
        date = date,
        showDatePicker = {
            MaterialDatePicker.Builder.datePicker()
                .setSelection(date?.toEpochMilli() ?: Instant.now().toEpochMilli())
                .build()
                .apply {
                    show(context.supportFragmentManager, "DatePicker")
                    addOnPositiveButtonClickListener {
                        setDate(Instant.ofEpochMilli(it))
                    }
                }
        }
    )
}

data class DatePickerResult(
    val date: Instant?,
    val showDatePicker: () -> Unit
)

@Composable
fun timePicker(
    initialTime: LocalTime?,
): TimePickerResult {
    val context = LocalContext.current as AppCompatActivity
    val (time, setTime) = remember {
        mutableStateOf(initialTime)
    }
    return TimePickerResult(
        time = time,
        shouTimePicker = {
            TimePickerDialog(
                context, { _, h, m -> setTime(LocalTime.of(h, m)) },
                0,
                0,
                true,
            ).show()
        },
    )
}

data class TimePickerResult(
    val time: LocalTime?,
    val shouTimePicker: () -> Unit,
)
