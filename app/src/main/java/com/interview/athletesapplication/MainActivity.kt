package com.interview.athletesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.interview.athletesapplication.ui.screens.atheletelist.AthleteListViewModel
import com.interview.athletesapplication.ui.screens.atheletelist.AthletesListScreen
import com.interview.athletesapplication.ui.theme.AthletesApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AthletesApplicationTheme {
                val viewModel: AthleteListViewModel by viewModels { AthleteListViewModel.Factory }
                viewModel.fetchAllAthletes()
                AthletesListScreen(viewModel)
            }
        }
    }
}