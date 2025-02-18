package com.example.jobnest.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobnest.Data.Job
import com.example.jobnest.Data.jobViewModel

@Composable
fun JobPostScreen(navController: NavController, viewModel: jobViewModel) {
    val context = LocalContext.current
    var jobTitle by remember { mutableStateOf("") }
    var organizationName by remember { mutableStateOf("") }
    var jobCategory by remember { mutableStateOf("") }
    var skillsRequired by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Post a Job", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = jobTitle, onValueChange = { jobTitle = it }, label = { Text("Job Title") })
        OutlinedTextField(value = organizationName, onValueChange = { organizationName = it }, label = { Text("Organization Name") })
        OutlinedTextField(value = jobCategory, onValueChange = { jobCategory = it }, label = { Text("Job Category") })
        OutlinedTextField(value = skillsRequired, onValueChange = { skillsRequired = it }, label = { Text("Skills Required") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (jobTitle.isNotEmpty() && organizationName.isNotEmpty()) {
                val job = Job(
                    jobTitle = jobTitle,
                    organizationName = organizationName,
                    jobCategory = jobCategory,
                    skillsRequired = skillsRequired,
                    status = "Applied"
                )
                viewModel.addJob(job)
                Toast.makeText(context, "Job Posted Successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate("job_list")
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Post Job")
        }
    }
}
