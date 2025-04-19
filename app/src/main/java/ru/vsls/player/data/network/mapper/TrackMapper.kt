package ru.vsls.player.data.network.mapper

import ru.vsls.player.data.network.entities.TrackApi
import ru.vsls.player.domain.entities.Track

fun TrackApi.mapToDomain():Track= Track(
    id = id,
    title = title,
    duration = duration,
    preview = preview,
    coverHash = coverHash,
    author = artist.name
)