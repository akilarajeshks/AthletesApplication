package com.interview.athletesapplication

import androidx.compose.runtime.collectAsState
import com.interview.athletesapplication.model.Athlete
import com.interview.athletesapplication.model.FullName
import com.interview.athletesapplication.repository.AthleteRepository
import com.interview.athletesapplication.ui.screens.atheletelist.AthleteListUiModel
import com.interview.athletesapplication.ui.screens.atheletelist.AthleteListViewModel
import com.interview.athletesapplication.usecase.AddAthleteUseCase
import com.interview.athletesapplication.usecase.GetAllAthletesUseCase
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AthleteViewModelTest {

    private lateinit var athleteListViewModel: AthleteListViewModel
    private val testCoroutineDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testCoroutineDispatcher)
    private val athleteRepository : AthleteRepository = mockk()
    private val addAthleteUseCase: AddAthleteUseCase = AddAthleteUseCase(athleteRepository)
    private val getAthleteUseCase: GetAllAthletesUseCase = GetAllAthletesUseCase(athleteRepository)

    @Test
    fun `Test for adding an athlete`() = testScope.runTest {
        val athlete = Athlete(
            name = FullName("MS", "Dhoni"),
            age = 38
        )
        every { athleteRepository.addAthlete(any()) } returns true
        every { athleteRepository.getAllAthletes() } returns listOf(athlete)

        val uiStateList = mutableListOf<UIState<AthleteListUiModel>>()

        athleteListViewModel = AthleteListViewModel(
            addAthleteUseCase = addAthleteUseCase,
            getAllAthletesUseCase = getAthleteUseCase,
            dispatcher = testCoroutineDispatcher
        )
        val launchJob = launch {
            athleteListViewModel.uiState.toList(uiStateList)
        }

        athleteListViewModel.addAthlete(athlete)
        testCoroutineDispatcher.scheduler.advanceUntilIdle()
        launchJob.cancel()

        uiStateList shouldBe listOf(
            UIState.Loading,
            UIState.Content(AthleteListUiModel(listOf(athlete)))
        )
    }
}