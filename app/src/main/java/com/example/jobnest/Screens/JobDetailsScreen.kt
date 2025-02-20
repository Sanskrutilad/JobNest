package com.example.jobnest.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jobnest.Data.viewmodel.JobViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun JobDetailsScreen(navController: NavController, jobId: Int, viewModel: JobViewModel = hiltViewModel()) {
    val job by viewModel.getJobById(jobId).observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (job != null) {
            Text(text = job!!.jobTitle, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Organization: ${job!!.organizationName}", fontSize = 18.sp)
            Text(text = "Category: ${job!!.jobCategory}", fontSize = 18.sp)
            Text(text = "Skills Required: ${job!!.skillsRequired}", fontSize = 18.sp)
            Text(text = "Status: ${job!!.status}", fontSize = 18.sp, color = Color.Blue)
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Back to Jobs List")
            }
        } else {
            Text("Loading job details...", fontSize = 18.sp)
        }
    }
}
