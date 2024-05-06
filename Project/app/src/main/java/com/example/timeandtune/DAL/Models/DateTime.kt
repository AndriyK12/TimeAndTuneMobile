package com.example.timeandtune.DAL.Models

data class DateTime(
    val date: Date,
    val time: Time
): java.io.Serializable  {
    override fun toString(): String {
        return "$date $time"
    }
    companion object {
        fun parseDateTime(dateTime: String): DateTime {
            val dataParts = dateTime.split(" ")
            return DateTime(Date.parseDate(dataParts[0]), Time.parseTime(dataParts[1]))
        }
    }
}
