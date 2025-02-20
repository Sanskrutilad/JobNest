package com.example.jobnest.Data

import androidx.room.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val organizationName: String,
    val address: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isVerified: Boolean = false
)
@Entity(tableName = "candidates")
data class Candidate(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isVerified: Boolean = false
)
@Entity(tableName = "jobs")
data class JobPost(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jobTitle: String,
    val organizationName: String,
    val jobCategory: String,
    val skillsRequired: String,
    val status: String = "Open"
)

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE email = :email AND password = :password")
    suspend fun loginEmployee(email: String, password: String): Employee?

    @Query("SELECT * FROM employees WHERE email = :email")
    suspend fun getEmployeeByEmail(email: String): Employee?
}
@Dao
interface CandidateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerCandidate(candidate: Candidate)

    @Query("SELECT * FROM candidates WHERE email = :email AND password = :password")
    suspend fun loginCandidate(email: String, password: String): Candidate?

    @Query("SELECT * FROM candidates WHERE email = :email")
    suspend fun getCandidateByEmail(email: String): Candidate?
}
@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJob(jobPost: JobPost)

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): Flow<List<JobPost>>

    @Query("SELECT * FROM jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: Int): JobPost?

    @Delete
    suspend fun deleteJob(jobPost: JobPost)

    @Update
    suspend fun updateJob(jobPost: JobPost)
}
