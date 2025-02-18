package com.example.jobnest.Data

import kotlinx.coroutines.flow.Flow

class jobRepo(private val userDao: UserDao, private val jobDao: JobDao) {
    suspend fun registerUser(user: User) = userDao.registerUser(user)
    suspend fun loginUser(email: String, password: String) = userDao.loginUser(email, password)
    suspend fun addJob(job: Job) = jobDao.addJob(job)
    fun getAllJobs(): Flow<List<Job>> = jobDao.getAllJobs()
    suspend fun updateJobStatus(jobId: Int, status: String) = jobDao.updateJobStatus(jobId, status)
    suspend fun deleteJob(job: Job) = jobDao.deleteJob(job)
}