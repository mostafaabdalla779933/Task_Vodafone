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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ViewModelComponent::class)
@Module
class RepoModule {
        @Provides
        @ViewModelScoped
        fun provideLocalRepo(airLineDao: AirlLineDao, shared : SharedPreferences): ILocalRepo {
            return LocalRepo(airLineDao,shared)
        }

        @Provides
        @ViewModelScoped
        fun provideRemoteRepo(networkManager: INetworkManager): IRemoteRepo {
            return RemoteRepo(networkManager)
        }
}