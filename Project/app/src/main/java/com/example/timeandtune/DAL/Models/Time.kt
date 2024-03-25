package com.example.timeandtune.DAL.Models

data class Time(
    val hours: Short,
    val minutes: Short,
    val seconds: Short
) {
    override fun toString(): String {
        return "$hours:$minutes:$seconds"
    }
    companion object {
        fun parseTime(time: String): Time {
            val timeParts = time.split(":")
            return Time(timeParts[0].toShort(), timeParts[1].toShort(), timeParts[2].toShort())
        }
    }
}
