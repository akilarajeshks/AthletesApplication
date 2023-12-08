package com.interview.athletesapplication.ui.screens.atheletelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.interview.athletesapplication.UIState
import com.interview.athletesapplication.repository.InMemoryAthleteRepository
import com.interview.athletesapplication.model.Athlete
import com.interview.athletesapplication.usecase.AddAthleteUseCase
import com.interview.athletesapplication.usecase.GetAllAthletesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AthleteListViewModel(
    private val getAllAthletesUseCase: GetAllAthletesUseCase,
    private val addAthleteUseCase: AddAthleteUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val uiState = MutableStateFlow<UIState<AthleteListUiModel>>(UIState.Loading)

    fun fetchAllAthletes() {
        uiState.value = UIState.Loading
        viewModelScope.launch(dispatcher) {
            val athletesList = getAllAthletesUseCase.invoke()
            uiState.value = UIState.Content(AthleteListUiModel(athletesList))
        }
    }

    fun addAthlete(athlete: Athlete) {
        when (addAthleteUseCase.invoke(athlete)) {
            true -> {
                fetchAllAthletes()
            }

            false -> {
                // Show error for duplicate items
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
            ): T {
                return AthleteListViewModel(
                    GetAllAthletesUseCase(InMemoryAthleteRepository),
                    AddAthleteUseCase(InMemoryAthleteRepository)
                ) as T
            }
        }
    }
}
