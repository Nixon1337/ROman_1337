package com.example.flyingaround.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateData {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-M-dd")
    private val formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private val nextMonth = LocalDateTime.now().plusMonths(1)
    val properDate = LocalDateTime.of(2023, 8, 27, 17, 5, 0)
    val currentDate = LocalDateTime.now().format(formatter)!!
    val currentDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))!!
    val nextMonthDate = nextMonth.format(formatter)!!
    val departureNextMonth = nextMonth.format(formatter2)!!
    val properShortestDate = properDate.format(formatter)!!
    val properShortDate = properDate.format(formatter2)
    val properFullDate = properDate.format(formatter3)!!
}