package com.example.jobnest.Data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class jobViewModel(private val repository: jobRepo) : ViewModel() {
    private val _jobs = MutableStateFlow<List<Job>>(emptyList())
    val jobs = _jobs.asStateFlow()

    fun registerUser(user: User) {
        viewModelScope.launch { repository.registerUser(user) }
    }

    fun loginUser(email: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch { onResult(repository.loginUser(email, password)) }
    }

    fun addJob(job: Job) {
        viewModelScope.launch { repository.addJob(job) }
    }

    fun fetchJobs() {
        viewModelScope.launch { repository.getAllJobs().collect { _jobs.value = it } }
    }

    fun updateJobStatus(jobId: Int, status: String) {
        viewModelScope.launch { repository.updateJobStatus(jobId, status) }
    }

    fun deleteJob(job: Job) {
        viewModelScope.launch { repository.deleteJob(job) }
    }
}
