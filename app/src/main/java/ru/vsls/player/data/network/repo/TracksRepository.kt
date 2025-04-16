package ru.vsls.player.data.network.repo

import ru.vsls.player.data.network.mapper.mapToDomain
import ru.vsls.player.data.network.services.TracksService
import ru.vsls.player.domain.entities.Track

interface TracksRepository{
    suspend fun getAllTracks():List<Track>
}

class TracksRepositoryImpl(private val service: TracksService):TracksRepository{
    override suspend fun getAllTracks(): List<Track> {
        return service.getTracks().map { it.mapToDomain() }
    }
}