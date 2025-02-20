package com.example.jobnest.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jobnest.Data.JobPost
import com.example.jobnest.Data.viewmodel.JobViewModel

@Composable
fun JobPostScreen(navController: NavController, viewModel: JobViewModel = hiltViewModel()) {
    var jobTitle by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf("") }
    var jobCategory by remember { mutableStateOf("") }
    var skillsRequired by remember { mutableStateOf("") }
    val categories = listOf("Software", "Marketing", "Finance", "Design")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Post a Job", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        TextField(value = jobTitle, onValueChange = { jobTitle = it }, label = { Text("Job Title") })
        TextField(value = organization, onValueChange = { organization = it }, label = { Text("Organization Name") })

        DropdownMenuBox3(
            selectedOption = jobCategory,
            options = categories,
            onOptionSelected = { jobCategory = it }
        )

        TextField(value = skillsRequired, onValueChange = { skillsRequired = it }, label = { Text("Skills Required") })

        Button(
            onClick = {
                viewModel.addJob(
                    JobPost(jobTitle = jobTitle, organizationName = organization, jobCategory = jobCategory, skillsRequired = skillsRequired)
                )
                navController.navigate("jobs")
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun DropdownMenuBox3(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Button(onClick = { expanded = true }) {
            Text(selectedOption)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}