package ru.vsls.player.data.storage.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vsls.player.data.storage.enteties.TrackDb.Companion.DETAILS_TABLE_NAME

@Entity(tableName = DETAILS_TABLE_NAME)
data class TrackDb(
    @PrimaryKey
    val id: Long,
    val title:String,
    val author:String,
    val preview:String,
    val icon:String
) {
    companion object {
        const val DETAILS_TABLE_NAME = "tracks_table"
    }
}
