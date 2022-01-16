package com.example.di

import android.content.Context
import androidx.room.Room

import com.example.db.AirlLineDao
import com.example.db.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
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