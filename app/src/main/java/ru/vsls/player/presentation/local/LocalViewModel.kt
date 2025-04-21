package ru.vsls.player.presentation.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.vsls.player.domain.UseCaseGetTracks
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(private val useCaseGetTracks: UseCaseGetTracks) :
    ViewModel() {


     val localTracks = useCaseGetTracks.getAllTracks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

}