package com.raulastete.vibeplayer.features.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.MusicTrackItemUi
import com.raulastete.vibeplayer.features.player.components.PlayerControl
import com.raulastete.vibeplayer.ui.components.ArtWorkImage
import com.raulastete.vibeplayer.ui.components.InternalTopBar
import com.raulastete.vibeplayer.ui.theme.Outline
import com.raulastete.vibeplayer.ui.theme.TextPrimary
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyMediumRegular

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = viewModel(),
    onBackActionClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PlayerScreenContent(
        uiState,
        onBackActionClick = onBackActionClick
    )
}

@Composable
private fun PlayerScreenContent(
    uiState: PlayerUiState,
    onBackActionClick: () -> Unit
) {
    Scaffold(
        topBar = {
            InternalTopBar(
                navigationIcon = ImageVector.vectorResource(R.drawable.minimize_track_icon),
                onBackActionClick = onBackActionClick
            )
        }
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            uiState.currentTrack?.let { trackItemUi ->
                Spacer(Modifier.weight(1f))
                TrackInfo(
                    musicTrackItemUi = trackItemUi
                )
                Spacer(Modifier.weight(1f))

            }
            CurrentTrackProgress {
                0.5f
            }
            Spacer(Modifier.height(20.dp))
            PlayerControl(
                modifier = Modifier.fillMaxWidth(),
                isPlaying = uiState.isPlaying,
                isInShuffleMode = uiState.isInShuffleMode,
                repeatingMode = uiState.repeatingMode,
                onPlayButtonClick = {},
                onPauseButtonClick = {},
                onPreviousButtonClick = {},
                onNextButtonClick = {},
                onShuffleButtonClick = {},
                onRepeatButtonClick = {}
            )
        }
    }
}

@Composable
private fun TrackInfo(
    musicTrackItemUi: MusicTrackItemUi
) {
    ArtWorkImage(
        artwork = musicTrackItemUi.artwork,
        modifier = Modifier.size(320.dp),
        placeholderSize = 180.dp
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = musicTrackItemUi.title,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(Modifier.height(4.dp))
    Text(
        text = musicTrackItemUi.artistName,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMediumRegular.copy(
            color = TextSecondary
        )
    )
}

@Composable
private fun CurrentTrackProgress(
    progress: () -> Float
) {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp),
        progress = progress,
        trackColor = Outline,
        color = TextPrimary,
        gapSize = 0.dp,
        drawStopIndicator = {}
    )
}