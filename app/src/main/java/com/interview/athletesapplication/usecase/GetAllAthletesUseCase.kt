package com.interview.athletesapplication.usecase

import com.interview.athletesapplication.repository.AthleteRepository

class GetAllAthletesUseCase(private val athleteRepository: AthleteRepository) {
    operator fun invoke() = athleteRepository.getAllAthletes()
}