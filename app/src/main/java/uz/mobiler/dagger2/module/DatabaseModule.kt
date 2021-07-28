package uz.mobiler.dagger2.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.mobiler.dagger2.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    private lateinit var appDatabase: AppDatabase

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesUsersDAO(appDatabase: AppDatabase) = appDatabase.userDao()
}