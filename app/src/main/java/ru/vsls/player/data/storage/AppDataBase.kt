package ru.vsls.player.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vsls.player.data.storage.dao.TracksDao
import ru.vsls.player.data.storage.enteties.TrackDb

@Database(
    entities = [TrackDb::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getTracksDao(): TracksDao
}