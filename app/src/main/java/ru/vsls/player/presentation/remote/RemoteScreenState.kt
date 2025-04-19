package ru.vsls.player.presentation.remote

import ru.vsls.player.domain.entities.Track

data class RemoteScreenState(
    val searchTitle:String ="",
    val tracks:List<Track> = emptyList()
)
