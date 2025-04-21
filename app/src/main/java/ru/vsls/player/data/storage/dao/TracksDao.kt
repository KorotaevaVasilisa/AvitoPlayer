package ru.vsls.player.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.vsls.player.data.storage.enteties.TrackDb

@Dao
interface TracksDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun insert(trackDb: TrackDb)

    @Query("SELECT * FROM TRACKS_TABLE WHERE id =:id")
    suspend fun getTrackFromDb(id:Long):TrackDb?

    @Query("SELECT * FROM tracks_table")
    suspend fun getAllTracksFromDb():List<TrackDb>
}