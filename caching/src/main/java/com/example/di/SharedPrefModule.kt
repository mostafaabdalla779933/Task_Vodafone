package com.example.di

import android.content.Context
import android.content.SharedPreferences
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
class SharedPrefModule {


    @Provides
    @Singleton
    fun provideSharedpreferences(@ApplicationContext context: Context): SharedPreferences {
        return   context.getSharedPreferences("weather", Context.MODE_MULTI_PROCESS)

    }
}