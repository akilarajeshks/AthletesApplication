package com.interview.athletesapplication.repository

import com.interview.athletesapplication.model.Athlete

object InMemoryAthleteRepository : AthleteRepository {
    private val athletes = mutableSetOf<Athlete>()

    override fun addAthlete(athlete: Athlete): Boolean {
        return athletes.add(athlete)
    }

    override fun getAllAthletes(): List<Athlete> {
        return athletes.toList()
    }
}

