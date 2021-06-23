package com.example.shiftworkmanagement.model

import kotlinx.serialization.*
import java.time.LocalDate
import java.time.LocalDateTime

data class Request (
        val id: Int,
        val workPlace: String,

        @SerialName("local_date")
        val requestDate: LocalDate,

        @SerialName("start_time")
        val startTime: LocalDateTime,

        @SerialName("end_time")
        val endTime: LocalDateTime,

        val status: Int
        )