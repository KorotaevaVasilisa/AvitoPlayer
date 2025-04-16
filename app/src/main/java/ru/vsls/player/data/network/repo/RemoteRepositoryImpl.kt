package ru.vsls.player.data.network.repo

import ru.vsls.player.data.network.mapper.mapToDomain
import ru.vsls.player.data.network.services.TracksService
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val service: TracksService) : RemoteRepository {

    override suspend fun getAllRemoteTracks(): List<Track> {
        return service.getTracks().tracks.data.map { it.mapToDomain() }
    }

    override suspend fun findRemoteTracksByTitle(title: String): List<Track> {
        return service.getTracksByTitle(title).data.map { it.mapToDomain() }
    }
}