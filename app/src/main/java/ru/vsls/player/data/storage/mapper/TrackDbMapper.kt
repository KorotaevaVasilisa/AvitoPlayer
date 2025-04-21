package ru.vsls.player.data.storage.mapper

import ru.vsls.player.data.storage.enteties.TrackDb
import ru.vsls.player.domain.entities.Track

fun TrackDb.mapToDomain(): Track = Track(
    id = id,
    title = title,
    author = author,
    preview = preview,
    coverHash = icon,
)

fun Track.mapToEntity(): TrackDb = TrackDb(
    id = id,
    title = title,
    author = author,
    preview = preview,
    icon = coverHash
)