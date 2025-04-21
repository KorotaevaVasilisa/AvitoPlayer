package ru.vsls.player.domain

import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.LocalRepository
import javax.inject.Inject

interface UseCaseGetTracks {

    suspend fun getAllTracks(): List<Track>
}

class UseCaseGetTracksImpl @Inject constructor(private val localRepository: LocalRepository) :
    UseCaseGetTracks {
    override suspend fun getAllTracks(): List<Track> {
        return localRepository.getTracksFromDb()
    }

}