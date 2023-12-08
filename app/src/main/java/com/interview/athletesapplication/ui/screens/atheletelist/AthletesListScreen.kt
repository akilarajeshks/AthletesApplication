@file:OptIn(ExperimentalMaterial3Api::class)

package com.interview.athletesapplication.ui.screens.atheletelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.interview.athletesapplication.UIState
import com.interview.athletesapplication.model.Athlete
import com.interview.athletesapplication.model.FullName
import com.interview.athletesapplication.ui.components.AddAthleteDialog
import com.interview.athletesapplication.ui.components.AthleteCard

@Composable
fun AthletesListScreen(athleteListViewModel: AthleteListViewModel) {
    val uiState = athleteListViewModel.uiState.collectAsState()
    AthleteListScreen(uiState.value, onAddAthlete =
    { athleteListViewModel.addAthlete(it) })
}

@Composable
fun AthleteListScreen(value: UIState<AthleteListUiModel>, onAddAthlete: (Athlete) -> Unit = {}) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        when (value) {
            is UIState.Content -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Text(
                            text = "Athletes List",
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    items(value.viewData.athleteList) {
                        AthleteCard(athlete = it)
                    }
                }
            }

            UIState.Error -> {
                Text(text = "Some Error occurred")
            }

            UIState.Loading -> {
                CircularProgressIndicator()
            }
        }
        FloatingActionButton(
            onClick = {
                showDialog.value = true
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        if (showDialog.value) {
            AddAthleteDialog(
                onDismiss = { showDialog.value = false },
                onSubmit = onAddAthlete
            )
        }
    }
}

@Preview
@Composable
fun AthleteListScreenContent() {
    MaterialTheme {
        val athletes = listOf(
            Athlete(
                FullName(
                    "MS",
                    "Dhoni"
                ), 32
            ),
            Athlete(
                FullName(
                    "Virat",
                    "Kohli"
                ), 32
            ),
            Athlete(
                FullName(
                    "Rohit",
                    "Sharma"
                ), 32
            ),
            Athlete(
                FullName(
                    "Sachin",
                    "Tendulkar"
                ), 32
            ),
            Athlete(
                FullName(
                    "Sourav",
                    "Ganguly"
                ), 32
            ),
        )
        AthleteListScreen(
            value = UIState.Content(
                AthleteListUiModel(
                    athletes
                )
            )
        )
    }
}

@Preview
@Composable
fun AthleteListLoadingStatePreview() {
    MaterialTheme {
        AthleteListScreen(value = UIState.Loading)
    }
}
