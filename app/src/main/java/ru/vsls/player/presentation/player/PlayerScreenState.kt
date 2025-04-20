package ru.vsls.player.presentation.player

import ru.vsls.player.domain.entities.Track


data class PlayerScreenState(
    val exoPlayerState: Boolean = false,
    val currentTrack: Track = Track(
        id = 0,
        title = "",
        duration = 30,
        preview = "",
        coverHash = "",
        position = 0,
        author = ""
    ),
    val duration: Long = 0L,
    val currentDuration: Long = 0L,
)


