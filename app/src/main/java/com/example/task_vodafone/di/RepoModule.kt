package com.example.task_vodafone.di

import android.content.SharedPreferences
import com.example.db.AirlLineDao
import com.example.network.network.INetworkManager
import com.example.task_vodafone.repo.ILocalRepo
import com.example.task_vodafone.repo.IRemoteRepo
import com.example.task_vodafone.repo.LocalRepo
import com.example.task_vodafone.repo.RemoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepoModule {


        @Provides
        @Singleton
        fun provideLocalRepo(airlLineDao: AirlLineDao,shared : SharedPreferences): ILocalRepo {
            return LocalRepo(airlLineDao,shared)
        }

        @Provides
        @Singleton
        fun provideRemoteRepo(networkManager: INetworkManager): IRemoteRepo {
            return RemoteRepo(networkManager)
        }


}