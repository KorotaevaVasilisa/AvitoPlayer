package ru.vsls.player.presentation.player

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.player.domain.UseCaseCheckTrack
import ru.vsls.player.domain.UseCaseDownload
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.LocalRepository
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val useCaseCheckTrack: UseCaseCheckTrack,
    private val useCaseDownload: UseCaseDownload,
    private val exoPlayer: ExoPlayer,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val startNumber: Int = savedStateHandle["position"] ?: 0

    private var _playerState = MutableStateFlow(PlayerScreenState())
    val playerState = _playerState.asStateFlow()

    private var progressJob: Job? = null

    init {
        // Устанавливаем начальную позицию;
        val clampedIndex = startNumber.coerceIn(0, exoPlayer.mediaItemCount - 1)
        exoPlayer.seekTo(clampedIndex, 0L)



        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlayingNow: Boolean) {
                _playerState.update { it.copy(exoPlayerState = isPlayingNow) }
                if (isPlayingNow)
                    startTrackingProgress()
                else
                    stopTrackingProgress()
            }

            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    updateTrackInfo()
                }
            }
        })

        exoPlayer.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                val track = mediaItem?.localConfiguration?.tag as? Track
                if (track != null) {
                    _playerState.update {
                        it.copy(
                            currentTrack = track,
                        )
                    }
                }
            }
        })
    }

    fun updateTrackInfo() {
        val track = exoPlayer.currentMediaItem?.localConfiguration?.tag as? Track
        if (track != null) {
            _playerState.update {
                it.copy(
                    currentTrack = track,
                    duration = exoPlayer.duration,
                    currentDuration = exoPlayer.currentPosition
                )
            }
        }
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

    private fun startTrackingProgress() {
        progressJob?.cancel()
        progressJob = viewModelScope.launch {
            while (true) {
                updateProgress() // сразу обновляем
                delay(1000L)
            }
        }
    }

    private fun updateProgress() {
        val position = exoPlayer.currentPosition
        val duration = exoPlayer.duration.takeIf { it > 0 }
            ?: 30_000L // если вдруг null или 0, по дефолту 30 сек

        _playerState.update {
            it.copy(
                currentDuration = position,
            )
        }
    }

    private fun stopTrackingProgress() {
        progressJob?.cancel()
        progressJob = null
    }

    fun downloadTrack() {
        viewModelScope.launch(Dispatchers.IO) {
            val current = _playerState.value.currentTrack
            val result = useCaseCheckTrack.checkTrack(current.id)
            if (result == null) {
                try {
                    useCaseDownload.loadTrack(current)
                } catch (e: Exception) {
                    Log.e("download","Error downloading")
                }
            }
        }
    }

    fun getCoverUrl(hash: String, size: Int = 300): String {
        return "https://cdn-images.dzcdn.net/images/cover/$hash/${size}x${size}-000000-80-0-0.jpg"
    }


}