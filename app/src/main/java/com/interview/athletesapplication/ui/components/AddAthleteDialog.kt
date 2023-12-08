@file:OptIn(ExperimentalMaterial3Api::class)

package com.interview.athletesapplication.ui.components

import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.interview.athletesapplication.model.Athlete
import com.interview.athletesapplication.model.FullName

@Composable
internal fun AddAthleteDialog(
    onDismiss: () -> Unit,
    onSubmit: (Athlete) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {

        Card(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Add Athlete",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(8.dp)
            )
            val firstname = remember {
                mutableStateOf("")
            }
            val secondName = remember {
                mutableStateOf("")
            }
            val age = remember {
                mutableStateOf("")
            }
            val validate =
                firstname.value.isNotBlank() && secondName.value.isNotBlank() && (age.value.toIntOrNull() != null && age.value.toInt() > 0)


            OutlinedTextField(
                value = firstname.value,
                onValueChange = { firstname.value = it },
                label = {
                    Text(
                        text = "First name"
                    )
                }, modifier = Modifier.padding(start = 4.dp, end = 4.dp))
            OutlinedTextField(
                value = secondName.value,
                onValueChange = { secondName.value = it },
                label = { Text(text = "Second Name") },modifier = Modifier.padding(start = 4.dp, end = 4.dp))
            OutlinedTextField(
                value = age.value,
                onValueChange = { age.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Age") },
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )
            Button(onClick = {
                val athlete = Athlete(
                    name = FullName(
                        firstName = firstname.value,
                        lastName = secondName.value
                    ),
                    age = age.value.toInt()
                )
                onSubmit(athlete)
                onDismiss()
            }, enabled = validate, modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "Submit")
            }

        }
    }
}