package com.example.jobnest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobnest.Data.jobViewModel
import com.example.jobnest.screens.JobPostScreen
import com.example.jobnest.screens.LoginScreen
import com.example.jobnest.screens.RegisterScreen
import com.example.jobnest.screens.JobListScreen
import com.example.jobnest.ui.theme.JobNestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobNestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: jobViewModel = viewModel()

    NavHost(navController = navController, startDestination = "register") {
        composable("register") { RegisterScreen(navController, viewModel) }
        composable("login") { LoginScreen(navController, viewModel) }
        composable("job_post") { JobPostScreen(navController, viewModel) }
        composable("job_list") { JobListScreen(navController, viewModel) }
    }
}
