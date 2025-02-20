package com.example.jobnest

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEmployeeDatabase(@ApplicationContext context: Context): EmployeeDatabase {
        return EmployeeDatabase.getDatabase(context)
    }

    @Provides
    fun provideEmployeeDao(database: EmployeeDatabase): EmployeeDao {
        return database.employeeDao()
    }

    @Provides
    @Singleton
    fun provideCandidateDatabase(@ApplicationContext context: Context): CandidateDatabase {
        return CandidateDatabase.getDatabase(context)
    }

    @Provides
    fun provideCandidateDao(database: CandidateDatabase): CandidateDao {
        return database.candidateDao()
    }

    @Provides
    @Singleton
    fun provideJobDatabase(@ApplicationContext context: Context): JobDatabase {
        return JobDatabase.getDatabase(context)
    }

    @Provides
    fun provideJobDao(database: JobDatabase): JobDao {
        return database.jobDao()
    }
}
