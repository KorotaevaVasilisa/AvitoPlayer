package ru.vsls.player.domain

import kotlinx.coroutines.flow.Flow
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.LocalRepository
import javax.inject.Inject

interface UseCaseGetTracks {

    fun getAllTracks(): Flow<List<Track>>
}

class UseCaseGetTracksImpl @Inject constructor(private val localRepository: LocalRepository) :
    UseCaseGetTracks {
    override fun getAllTracks(): Flow<List<Track>> {
        return localRepository.getTracksFromDb()
    }

}