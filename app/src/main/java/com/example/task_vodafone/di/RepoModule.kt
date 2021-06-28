package com.example.task_vodafone.di

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
        fun provideLocalRepo(airlLineDao: AirlLineDao): ILocalRepo {
            return LocalRepo(airlLineDao)
        }

        @Provides
        @Singleton
        fun provideRemoteRepo(networkManager: INetworkManager): IRemoteRepo {
            return RemoteRepo(networkManager)
        }


}