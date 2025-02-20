package com.example.jobnest.Data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobnest.Data.Candidate
import com.example.jobnest.Data.Employee
import com.example.jobnest.Data.EmployeeDao
import com.example.jobnest.Data.CandidateDao


@HiltViewModel
class AuthViewmodel @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val candidateDao: CandidateDao
) : ViewModel() {

    private val _loginState = MutableLiveData<String?>()
    val loginState: LiveData<String?> = _loginState

    fun register(firstName: String, lastName: String, email: String, password: String, userType: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (userType == "Employee") {
                val employee = Employee(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    organizationName = "",
                    address = ""
                )
                employeeDao.registerEmployee(employee)
            } else {
                val candidate = Candidate(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
                candidateDao.registerCandidate(candidate)
            }
            onSuccess()
        }
    }

    fun login(email: String, password: String, userType: String) {
        viewModelScope.launch {
            val user = if (userType == "Employee") {
                employeeDao.loginEmployee(email, password)
            } else {
                candidateDao.loginCandidate(email, password)
            }
            _loginState.postValue(user?.let { "Success" } ?: "Invalid Credentials")
        }
    }
}
