package com.markovmozak.app.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateUtils {
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    fun today(): String = LocalDate.now().format(dateFormatter)

    fun formatDate(date: LocalDate): String = date.format(dateFormatter)

    fun parseDate(dateStr: String): LocalDate? = try {
        LocalDate.parse(dateStr, dateFormatter)
    } catch (_: Exception) {
        null
    }

    fun formatTime(time: LocalTime): String = time.format(timeFormatter)

    fun parseTime(timeStr: String): LocalTime? = try {
        LocalTime.parse(timeStr, timeFormatter)
    } catch (_: Exception) {
        null
    }

    fun isToday(dateStr: String?): Boolean {
        if (dateStr == null) return false
        return dateStr == today()
    }

    fun isOverdue(dateStr: String?): Boolean {
        if (dateStr == null) return false
        val date = parseDate(dateStr) ?: return false
        return date.isBefore(LocalDate.now())
    }

    fun formatDateDisplay(dateStr: String?): String {
        if (dateStr == null) return ""
        val date = parseDate(dateStr) ?: return dateStr
        val today = LocalDate.now()
        return when {
            date == today -> "Danas"
            date == today.plusDays(1) -> "Sutra"
            date == today.minusDays(1) -> "Jučer"
            else -> dateStr
        }
    }
}
