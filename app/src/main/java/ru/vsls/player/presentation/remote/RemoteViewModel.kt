package ru.vsls.player.presentation.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.player.domain.repositories.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class RemoteViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val _remoteTracksState = MutableStateFlow(RemoteScreenState())
    val remoteTracksState = _remoteTracksState.asStateFlow()
//
//    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
//    val tracks = _tracks.asStateFlow()

    init {
        loadTracks()
    }

    fun loadTracks(searchTitle:String=""){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tracks = if (searchTitle.isBlank()) {
                    remoteRepository.getAllRemoteTracks()
                } else {
                    remoteRepository.findRemoteTracksByTitle(title = searchTitle)
                }

                _remoteTracksState.update { currentState ->
                    currentState.copy(
                        tracks = tracks,
                        searchTitle = searchTitle
                    )
                }
            } catch (ex:Exception){
                _remoteTracksState.update { currentState ->
                    currentState.copy(tracks = emptyList())
                }
            }
        }
    }

    fun searchTracks(title: String ) {
        _remoteTracksState.update { it.copy(searchTitle = title) }
        loadTracks(title)
    }

    fun getCoverUrl(hash: String, size: Int = 200): String {
        return "https://cdn-images.dzcdn.net/images/cover/$hash/${size}x${size}-000000-80-0-0.jpg"
    }
}