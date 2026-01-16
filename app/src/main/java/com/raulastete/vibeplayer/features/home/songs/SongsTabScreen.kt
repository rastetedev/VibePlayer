package com.raulastete.vibeplayer.features.home.songs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.MusicTrackItemUi
import com.raulastete.vibeplayer.ui.components.SecondaryButton
import com.raulastete.vibeplayer.ui.components.TrackItem
import com.raulastete.vibeplayer.ui.theme.Outline
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyLargeMedium
import kotlin.collections.lastIndex

@Composable
fun SongsTabScreen(
    trackItems: List<MusicTrackItemUi>,
    onShuffleTrackListActionClick: () -> Unit,
    onPlayTrackListClick: () -> Unit,
    onClickTrackItem: (MusicTrackItemUi) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            HeaderSection(
                modifier = Modifier.fillMaxWidth(),
                trackCount = trackItems.size,
                onShuffleTrackListActionClick = onShuffleTrackListActionClick,
                onPlayTrackListClick = onPlayTrackListClick
            )
        }
        itemsIndexed(
            trackItems,
            key = { _, trackItem -> trackItem.title }
        ) { index, trackItemUi ->

            TrackItem(
                modifier = Modifier.padding(vertical = 12.dp),
                musicTrackItemUi = trackItemUi,
                onClick = { onClickTrackItem(trackItemUi) }
            )

            if (index != trackItems.lastIndex) {
                HorizontalDivider(thickness = 2.dp, color = Outline)
            }
        }
    }
}

@Composable
private fun HeaderSection(
    trackCount: Int,
    onShuffleTrackListActionClick: () -> Unit,
    onPlayTrackListClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            SecondaryButton(
                modifier = Modifier.weight(1f),
                onClick = onShuffleTrackListActionClick,
                icon = ImageVector.vectorResource(R.drawable.shuffle_icon),
                text = stringResource(R.string.shuffle_track_list_button)
            )
            Spacer(Modifier.width(8.dp))
            SecondaryButton(
                modifier = Modifier.weight(1f),
                onClick = onPlayTrackListClick,
                icon = ImageVector.vectorResource(R.drawable.play_outline_icon),
                text = stringResource(R.string.play_track_list_button)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            pluralStringResource(R.plurals.track_count, trackCount, trackCount),
            style = MaterialTheme.typography.bodyLargeMedium.copy(color = TextSecondary)
        )
    }
}