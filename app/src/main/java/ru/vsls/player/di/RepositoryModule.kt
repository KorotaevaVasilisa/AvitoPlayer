package ru.vsls.player.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vsls.player.data.network.repo.RemoteRepositoryImpl
import ru.vsls.player.data.storage.LocalRepositoryImpl
import ru.vsls.player.domain.repositories.LocalRepository
import ru.vsls.player.domain.repositories.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteRepository(impl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    @Singleton
    abstract fun bindLocalRepository(impl: LocalRepositoryImpl): LocalRepository
}