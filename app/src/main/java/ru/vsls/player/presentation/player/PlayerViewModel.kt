package ru.vsls.player.presentation.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.vsls.player.domain.entities.Track
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val exoPlayer: ExoPlayer,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val startNumber: Int = savedStateHandle["position"] ?: 0

    val track = Track(
        id = 2,
        title = "Sweet Child O'Mine",
        duration = 356,
        preview = "https://example.com/preview2",
        coverHash = "290abe93bdda84bb8b170f30a4998c4c",
        position = 4,
        author = "Guns N' Roses"
    )
    val isPlaying: StateFlow<Boolean> = MutableStateFlow(false)

    init {
        // Устанавливаем начальную позицию;
        val clampedIndex = startNumber.coerceIn(0, exoPlayer.mediaItemCount - 1)
        exoPlayer.seekTo(clampedIndex, 0L)

        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlayingNow: Boolean) {
                (isPlaying as MutableStateFlow).value = isPlayingNow
            }
        })
    }

    fun playPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun next() {
        exoPlayer.seekToNext()
    }

    fun previous() {
        exoPlayer.seekToPrevious()
    }

    fun getCoverUrl(hash: String, size: Int = 300): String {
        return "https://cdn-images.dzcdn.net/images/cover/$hash/${size}x${size}-000000-80-0-0.jpg"
    }
}