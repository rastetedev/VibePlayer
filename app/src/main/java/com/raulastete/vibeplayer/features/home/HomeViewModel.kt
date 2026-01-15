package com.raulastete.vibeplayer.features.home

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulastete.vibeplayer.core.AppDispatchers
import com.raulastete.vibeplayer.domain.MusicTrackRepository
import com.raulastete.vibeplayer.features.home.components.MusicTrackItemUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val musicTrackRepository: MusicTrackRepository,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState(ContentState.Scanning))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeMusicTracks()
    }

    fun observeMusicTracks() {
        viewModelScope.launch(appDispatchers.mainDispatcher) {
            musicTrackRepository.observeMusicTracks().map { list ->
                list.map {
                    MusicTrackItemUi(
                        id = it.id,
                        artwork = it.albumArtUri.toUri(),
                        title = it.title,
                        artistName = it.artistName,
                        musicLengthFormatted = formatDuration(it.playTimeMillis)
                    )
                }
            }
                .collectLatest { uiItems ->
                    withContext(appDispatchers.mainDispatcher) {
                        if (uiItems.isNotEmpty()) {
                            delay(1000)
                            //Add delay when implementation is finished to improve UI
                            //delay(3000)
                            _uiState.update {
                                it.copy(
                                    state = ContentState.TrackList(musicTrackItems = uiItems)
                                )
                            }
                        } else {
                            _uiState.update {
                                it.copy(state = ContentState.Empty)
                            }
                        }
                    }
                }
        }
    }

    fun loadMusicTracks() {

        _uiState.update { it.copy(state = ContentState.Scanning) }

        viewModelScope.launch(appDispatchers.mainDispatcher) {
            musicTrackRepository.syncMusicTracks()
        }
    }

    fun shuffleTrackList() {}
    fun playTrackList() {}

    fun formatDuration(duration: Long): String {
        val minutes = (duration / 1000) / 60
        val seconds = (duration / 1000) % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}