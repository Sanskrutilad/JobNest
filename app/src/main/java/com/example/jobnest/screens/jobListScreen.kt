package com.example.jobnest.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobnest.Data.Job
import com.example.jobnest.Data.jobViewModel

@Composable
fun JobListScreen(navController: NavController, viewModel: jobViewModel) {
    val jobList by viewModel.jobs.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchJobs()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Job Listings", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        LazyColumn {
            items(jobList) { job ->
                JobItem(job, viewModel)
            }
        }
    }
}

@Composable
fun JobItem(job: Job, viewModel: jobViewModel) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Applied", "Interview", "Offer", "Rejected")
    var selectedStatus by remember { mutableStateOf(job.status) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(job.jobTitle, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Organization: ${job.organizationName}")
            Text("Category: ${job.jobCategory}")
            Text("Skills: ${job.skillsRequired}")
            Text("Status: $selectedStatus", fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Box {
                    Button(onClick = { expanded = true }) {
                        Text("Update Status")
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        statusOptions.forEach { status ->
                            DropdownMenuItem(text = { Text(status) }, onClick = {
                                selectedStatus = status
                                viewModel.updateJobStatus(job.id, status)
                                Toast.makeText(context, "Status Updated!", Toast.LENGTH_SHORT).show()
                                expanded = false
                            })
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    viewModel.deleteJob(job)
                    Toast.makeText(context, "Job Deleted!", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Delete")
                }
            }
        }
    }
}
