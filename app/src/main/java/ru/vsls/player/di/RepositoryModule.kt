package ru.vsls.player.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vsls.player.data.network.repo.RemoteRepositoryImpl
import ru.vsls.player.data.storage.LocalRepositoryImpl
import ru.vsls.player.domain.UseCaseCheckTrack
import ru.vsls.player.domain.UseCaseCheckTrackImpl
import ru.vsls.player.domain.UseCaseDownload
import ru.vsls.player.domain.UseCaseDownloadImpl
import ru.vsls.player.domain.UseCaseGetTracks
import ru.vsls.player.domain.UseCaseGetTracksImpl
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

    @Binds
    @Singleton
    abstract fun bindUseCaseDownload(useCaseDownloadImpl: UseCaseDownloadImpl): UseCaseDownload

    @Binds
    @Singleton
    abstract fun bindUseCaseCheckTrack(useCaseCheckTrackImpl: UseCaseCheckTrackImpl): UseCaseCheckTrack

    @Binds
    @Singleton
    abstract fun bindUseCaseGetTracks(useCaseGetTracksImpl: UseCaseGetTracksImpl): UseCaseGetTracks
}