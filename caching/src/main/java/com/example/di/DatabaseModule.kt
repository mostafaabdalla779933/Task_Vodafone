package com.evaph.database.di

import android.content.Context
import androidx.room.Room
import com.evaph.database.db.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideConfigurationsDatabase(@ApplicationContext context: Context?): RoomDatabase {
        return Room.databaseBuilder(context!!, RoomDatabase::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideAirLineDao(database: RoomDatabase): AirlLineDao {
        return database.getAirLineDao()
    }
}