package com.interview.athletesapplication.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.interview.athletesapplication.model.Athlete
import com.interview.athletesapplication.model.FullName

@Composable
fun AthleteCard(athlete: Athlete) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Name: ${athlete.fullName.firstName} ${athlete.fullName.lastName}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Age: ${athlete.age}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Composable
fun AthleteCardPreview() {
    AthleteCard(
        athlete = Athlete(
            FullName(
                "MS", "Dhoni"
            ), 30
        )
    )
}