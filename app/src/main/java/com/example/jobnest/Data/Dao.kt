package com.example.jobnest.Data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val organizationName: String,
    val address: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
@Entity(tableName = "jobs")
data class Job(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jobTitle: String,
    val organizationName: String,
    val jobCategory: String,
    val skillsRequired: String,
    var status: String
)
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): User?
}
@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJob(job: Job)

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): Flow<List<Job>>

    @Query("UPDATE jobs SET status = :status WHERE id = :jobId")
    suspend fun updateJobStatus(jobId: Int, status: String)

    @Delete
    suspend fun deleteJob(job: Job)
}

@Database(entities = [User::class, Job::class], version = 1, exportSchema = false)
abstract class JobDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun jobDao(): JobDao

    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getDatabase(context: android.content.Context): JobDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobDatabase::class.java,
                    "job_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
