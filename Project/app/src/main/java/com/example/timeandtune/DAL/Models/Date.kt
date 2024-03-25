package com.example.timeandtune.DAL.Models

data class Date(
    val year: Short,
    val month: String,
    val day: Short
){
    override fun toString(): String {
        return "$year/$month/$day"
    }
    companion object {
        fun parseDate(date: String): Date{
            val dateParts = date.split("/")
            return Date(dateParts[0].toShort(), dateParts[1], dateParts[2].toShort())
        }
    }
}
