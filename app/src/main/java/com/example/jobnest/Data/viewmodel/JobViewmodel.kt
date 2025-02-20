package com.example.jobnest.Data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jobnest.Data.JobDao
import com.example.jobnest.Data.JobPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val jobDao: JobDao
) : ViewModel() {

    val jobs: LiveData<List<JobPost>> = jobDao.getAllJobs().asLiveData()

    fun addJob(job: JobPost) {
        viewModelScope.launch {
            jobDao.addJob(job)
        }
    }

    fun getJobById(jobId: Int): JobPost? {
        var jobPost: JobPost? = null
        viewModelScope.launch {
            jobPost = jobDao.getJobById(jobId)
        }
        return jobPost
    }

    fun deleteJob(job: JobPost) {
        viewModelScope.launch {
            jobDao.deleteJob(job)
        }
    }

    fun updateJob(job: JobPost) {
        viewModelScope.launch {
            jobDao.updateJob(job)
        }
    }
}
