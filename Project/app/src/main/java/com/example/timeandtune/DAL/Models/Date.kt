package com.example.timeandtune.DAL.Models

data class Date(
    val year: Int,
    val month: String,
    val day: Int
): java.io.Serializable {
    override fun toString(): String {
        return "$year/$month/$day"
    }
    companion object {
        fun parseDate(date: String): Date{
            val dateParts = date.split("/")
            return Date(dateParts[0].toInt(), dateParts[1], dateParts[2].toInt())
        }
    }
}
