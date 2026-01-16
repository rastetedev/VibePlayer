package com.raulastete.vibeplayer.ui.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyMediumRegular

data class MusicTrackItemUi(
    val id: String,
    val artwork: Uri,
    val title: String,
    val artistName: String,
    val musicLengthFormatted: String
)

@Composable
fun TrackItem(
    musicTrackItemUi: MusicTrackItemUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier.clickable { onClick() },
        headlineContent = {
            Text(
                text = musicTrackItemUi.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                text = musicTrackItemUi.artistName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMediumRegular.copy(color = TextSecondary)
            )
        },
        leadingContent = {
            ArtWorkImage(
                modifier = Modifier.size(64.dp),
                artwork = musicTrackItemUi.artwork,
                placeholderSize = 36.dp
            )
        },
        trailingContent = {
            Text(
                text = musicTrackItemUi.musicLengthFormatted,
                style = MaterialTheme.typography.bodyMediumRegular.copy(color = TextSecondary)
            )
        }
    )
}