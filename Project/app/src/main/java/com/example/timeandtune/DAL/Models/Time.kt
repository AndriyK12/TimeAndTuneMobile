package com.example.timeandtune.DAL.Models

data class Time(
    val hours: Int,
    val minutes: Int,
    val seconds: Int
): java.io.Serializable  {
    override fun toString(): String {
        return "$hours:$minutes:$seconds"
    }
    companion object {
        fun parseTime(time: String): Time {
            val timeParts = time.split(":")
            return Time(timeParts[0].toInt(), timeParts[1].toInt(), timeParts[2].toInt())
        }
    }
}
