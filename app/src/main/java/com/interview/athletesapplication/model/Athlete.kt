package com.interview.athletesapplication.model

data class Athlete(
    val fullName: FullName,
    val age: Int
)

data class FullName(
    val firstName: String,
    val lastName: String
)