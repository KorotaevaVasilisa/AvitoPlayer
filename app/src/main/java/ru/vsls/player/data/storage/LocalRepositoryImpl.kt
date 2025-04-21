package ru.vsls.player.data.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vsls.player.data.storage.dao.TracksDao
import ru.vsls.player.data.storage.mapper.mapToDomain
import ru.vsls.player.data.storage.mapper.mapToEntity
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val tracksDao: TracksDao) : LocalRepository {
    override suspend fun checkTrackId(id: Long): Track? {
        return tracksDao.getTrackFromDb(id)?.mapToDomain()
    }

    override  fun getTracksFromDb(): Flow<List<Track>> {
        return tracksDao.getAllTracksFromDb().map { list ->
            list.map { track -> track.mapToDomain() }
        }
    }

    override suspend fun insertTrack(track: Track) {
        tracksDao.insert(track.mapToEntity())
    }
}