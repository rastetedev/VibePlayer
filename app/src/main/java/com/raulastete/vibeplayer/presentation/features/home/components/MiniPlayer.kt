package com.raulastete.vibeplayer.presentation.features.home.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.presentation.ui.components.ArtWorkImage
import com.raulastete.vibeplayer.presentation.ui.components.MusicTrackItemUi
import com.raulastete.vibeplayer.presentation.ui.components.PrimaryIconButton
import com.raulastete.vibeplayer.presentation.ui.components.SecondaryIconButton
import com.raulastete.vibeplayer.presentation.ui.theme.Outline
import com.raulastete.vibeplayer.presentation.ui.theme.TextPrimary
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyMediumRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniPlayer(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        dragHandle = {},
        containerColor = Outline,
    ) {
        MiniPlayerContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            musicTrackItemUi = MusicTrackItemUi(
                id = "SADS",
                artwork = Uri.EMPTY,
                title = "Title",
                artistName = "Artist",
                musicLengthFormatted = "4:2"
            )
        )
    }
}

@Composable
private fun MiniPlayerContent(
    musicTrackItemUi: MusicTrackItemUi,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ArtWorkImage(
            artwork = musicTrackItemUi.artwork,
            modifier = Modifier.size(64.dp),
            placeholderSize = 36.dp
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = musicTrackItemUi.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = musicTrackItemUi.artistName,
                        style = MaterialTheme.typography.bodyMediumRegular.copy(color = TextSecondary)
                    )
                }
                Spacer(Modifier.width(12.dp))
                PrimaryIconButton(
                    modifier = Modifier.size(44.dp),
                    iconSize = 16.dp,
                    onClick = {},
                    icon = ImageVector.vectorResource(R.drawable.next_skip_filled_icon)
                )
                Spacer(Modifier.width(8.dp))
                SecondaryIconButton(
                    modifier = Modifier.size(44.dp),
                    onClick = {},
                    icon = ImageVector.vectorResource(R.drawable.next_skip_filled_icon)
                )

            }
            Spacer(Modifier.height(16.dp))
            CurrentTrackProgress { 0.5f }
        }
    }
}

@Composable
private fun CurrentTrackProgress(
    progress: () -> Float
) {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp),
        progress = progress,
        trackColor = White.copy(alpha = 0.15f),
        color = TextPrimary,
        gapSize = 0.dp,
        drawStopIndicator = {}
    )
}