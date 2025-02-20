package com.example.jobnest

import android.content.Context
import com.example.jobnest.Data.*
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEmployeeDatabase(@ApplicationContext context: Context): EmployeeDatabase =
        EmployeeDatabase.getDatabase(context)

    @Provides
    fun provideEmployeeDao(database: EmployeeDatabase): EmployeeDao = database.employeeDao()

    @Provides
    @Singleton
    fun provideCandidateDatabase(@ApplicationContext context: Context): CandidateDatabase =
        CandidateDatabase.getDatabase(context)

    @Provides
    fun provideCandidateDao(database: CandidateDatabase): CandidateDao = database.candidateDao()

    @Provides
    @Singleton
    fun provideJobDatabase(@ApplicationContext context: Context): JobDatabase =
        JobDatabase.getDatabase(context)

    @Provides
    fun provideJobDao(database: JobDatabase): JobDao = database.jobDao()
}
