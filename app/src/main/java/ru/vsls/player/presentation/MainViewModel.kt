package ru.vsls.player.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.player.domain.entities.Track
import ru.vsls.player.domain.repositories.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( private val remoteRepository: RemoteRepository):ViewModel() {
    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
    val tracks = _tracks.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            _tracks.value =  remoteRepository.getAllRemoteTracks()
        }
    }

    public fun getTracksByTitle(title:String="That’s So True"){
        viewModelScope.launch(Dispatchers.IO) {
            _tracks.value =  remoteRepository.findRemoteTracksByTitle(title)
        }
    }
}