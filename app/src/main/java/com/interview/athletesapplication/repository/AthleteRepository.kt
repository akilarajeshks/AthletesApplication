package com.interview.athletesapplication.repository

import com.interview.athletesapplication.model.Athlete

interface AthleteRepository {
    fun addAthlete(athlete: Athlete): Boolean
    fun getAllAthletes(): List<Athlete>
}