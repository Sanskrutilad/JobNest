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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobnest.Data.User
import com.example.jobnest.Data.jobViewModel


@Composable
fun RegisterScreen(navController: NavController, viewModel: jobViewModel) {
    val context = LocalContext.current
    var organizationName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Register", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = organizationName, onValueChange = { organizationName = it }, label = { Text("Organization Name") })
        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
        OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") })
        OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (organizationName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(
                    organizationName = organizationName,
                    address = address,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
                viewModel.registerUser(user)
                Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                navController.navigate("login")
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Register")
        }
    }
}


