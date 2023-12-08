package com.interview.athletesapplication.usecase

import com.interview.athletesapplication.repository.AthleteRepository
import com.interview.athletesapplication.model.Athlete

class AddAthleteUseCase(private val athleteRepository: AthleteRepository) {
    operator fun invoke(athlete: Athlete) =
        athleteRepository.addAthlete(athlete = athlete)
}