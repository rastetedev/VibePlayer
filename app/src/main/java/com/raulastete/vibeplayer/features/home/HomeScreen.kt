package com.raulastete.vibeplayer.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.vibeplayer.features.home.views.EmptyView
import com.raulastete.vibeplayer.features.home.views.ScanningView
import com.raulastete.vibeplayer.features.home.views.TrackListView
import com.raulastete.vibeplayer.ui.components.MainTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onScanActionClick: () -> Unit,
    onTrackItemClick: (trackItemId: String) -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onScanActionClick = onScanActionClick,
        onLoadTracks = {
            viewModel.getLocalAudioFiles(context)
        },
        onTrackItemClick = onTrackItemClick
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    onScanActionClick: () -> Unit,
    onLoadTracks: () -> Unit,
    onTrackItemClick: (trackItemId: String) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                onScanActionClick = onScanActionClick
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (uiState.state) {
                ContentState.Empty -> EmptyView(
                    modifier = Modifier.fillMaxSize(),
                    loadTracks = onLoadTracks
                )

                ContentState.Scanning -> ScanningView(
                    modifier = Modifier.fillMaxSize(),
                )

                is ContentState.TrackList -> TrackListView(
                    trackItems = uiState.state.trackItems,
                    onClickTrackItem = { trackItem ->
                        onTrackItemClick(trackItem.id)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}