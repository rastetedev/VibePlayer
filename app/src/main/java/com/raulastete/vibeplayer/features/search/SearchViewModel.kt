package com.raulastete.vibeplayer.features.search

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulastete.vibeplayer.core.AppDispatchers
import com.raulastete.vibeplayer.domain.MusicTrackRepository
import com.raulastete.vibeplayer.features.home.components.MusicTrackItemUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val musicTrackRepository: MusicTrackRepository,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive = _isSearchActive.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    @OptIn(FlowPreview::class)
    val searchResult: StateFlow<List<MusicTrackItemUi>> =
        _searchQuery
            .debounce(300)
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    flowOf(emptyList())
                } else {
                    musicTrackRepository.search(query).map { tracks ->
                        tracks.map {
                            MusicTrackItemUi(
                                id = it.id,
                                artwork = it.albumArtUri.toUri(),
                                title = it.title,
                                artistName = it.artistName,
                                musicLengthFormatted = formatDuration(it.playTimeMillis)
                            )
                        }
                    }
                }
            }
            .onEach {
                _isLoading.value = true
                delay(2_000)
               _isLoading.value = false
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList(),
            )


    fun onSearchQueryChange(query: String) {

        _isSearchActive.value = query.isNotBlank()

        viewModelScope.launch(appDispatchers.mainDispatcher) {
            _searchQuery.value = query
        }
    }

    fun clearQuery() {
        _isSearchActive.value = false
        _searchQuery.value = ""
    }

    private fun formatDuration(duration: Long): String {
        val minutes = (duration / 1000) / 60
        val seconds = (duration / 1000) % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}