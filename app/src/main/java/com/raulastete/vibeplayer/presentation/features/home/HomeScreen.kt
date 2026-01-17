package com.raulastete.vibeplayer.presentation.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.vibeplayer.presentation.features.home.views.empty.EmptyView
import com.raulastete.vibeplayer.presentation.features.home.views.scanning.ScanningView
import com.raulastete.vibeplayer.presentation.features.home.views.tracklist.TrackListView
import com.raulastete.vibeplayer.presentation.ui.components.MainTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onScanActionClick: () -> Unit,
    onSearchActionClick: () -> Unit,
    onTrackItemClick: (trackItemId: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onScanActionClick = onScanActionClick,
        onSearchActionClick = onSearchActionClick,
        onLoadTracks = viewModel::loadMusicTracks,
        onShuffleTrackListActionClick = viewModel::shuffleTrackList,
        onPlayTrackListClick = viewModel::playTrackList,
        onTrackItemClick = onTrackItemClick
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    onScanActionClick: () -> Unit,
    onSearchActionClick: () -> Unit,
    onShuffleTrackListActionClick: () -> Unit,
    onPlayTrackListClick: () -> Unit,
    onLoadTracks: () -> Unit,
    onTrackItemClick: (trackItemId: String) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                showSearchButton = uiState.state is ContentState.TrackList,
                onScanActionClick = onScanActionClick,
                onSearchActionClick = onSearchActionClick
            )
        }
    ) {
        when (uiState.state) {
            ContentState.Empty -> EmptyView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 16.dp),
                loadTracks = onLoadTracks
            )

            ContentState.Scanning -> ScanningView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            )

            is ContentState.TrackList -> TrackListView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                trackItems = uiState.state.musicTrackItems,
                onClickTrackItem = { trackItem ->
                    onTrackItemClick(trackItem.id)
                },
                onShuffleTrackListActionClick = onShuffleTrackListActionClick,
                onPlayTrackListClick = onPlayTrackListClick
            )
        }
    }
}