package com.raulastete.vibeplayer.features.home.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.PlaylistItem
import com.raulastete.vibeplayer.ui.components.PlaylistItemUi
import com.raulastete.vibeplayer.ui.components.SecondaryButton
import com.raulastete.vibeplayer.ui.components.SecondaryIconButton
import com.raulastete.vibeplayer.ui.theme.ButtonPrimary
import com.raulastete.vibeplayer.ui.theme.ButtonPrimary30
import com.raulastete.vibeplayer.ui.theme.Outline
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyLargeMedium
import com.raulastete.vibeplayer.ui.theme.bodyMediumRegular

@Composable
fun PlayListTabScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Header(
            modifier = Modifier.fillMaxWidth(),
            playListCount = 1
        )
        Spacer(Modifier.height(16.dp))
        FavoritePlaylistSection(
            modifier = Modifier.fillMaxWidth(),
            trackCount = 3,
            onClick = {}
        )
        Spacer(Modifier.height(24.dp))
        MyPlaylistsSection(
            myPlaylists = listOf(
                PlaylistItemUi(
                    id = "1",
                    artwork = null,
                    title = "My Playlist 1",
                    trackItemQuantity = "3 songs"
                ),
                PlaylistItemUi(
                    id = "2",
                    artwork = null,
                    title = "My Playlist 2",
                    trackItemQuantity = "3 songs"
                )
            )
        )
    }
}

@Composable
private fun Header(playListCount: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            pluralStringResource(R.plurals.playlist_count, playListCount, playListCount),
            style = MaterialTheme.typography.bodyLargeMedium.copy(color = TextSecondary)
        )
        SecondaryIconButton(
            modifier = Modifier.size(36.dp),
            icon = ImageVector.vectorResource(R.drawable.plus_icon),
            onClick = {}
        )
    }
}

@Composable
private fun FavoritePlaylistSection(
    trackCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier.clickable { onClick() },
        headlineContent = {
            Text(
                text = stringResource(R.string.favourites_playlist_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                text = pluralStringResource(R.plurals.track_count, trackCount, trackCount),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMediumRegular.copy(color = TextSecondary)
            )
        },
        leadingContent = {
            Box(
                Modifier
                    .size(64.dp)
                    .background(
                        color = ButtonPrimary30,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.heart_duotone_icon),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = ButtonPrimary
                )
            }
        },
        trailingContent = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.menu_dots_icon),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun MyPlaylistsSection(
    myPlaylists: List<PlaylistItemUi>
) {
    Column() {
        Text(
            stringResource(
                R.string.my_playlist_count,
                myPlaylists.count()
            ),
            style = MaterialTheme.typography.bodyLargeMedium.copy(color = TextSecondary)
        )
        Spacer(Modifier.height(12.dp))
        if (myPlaylists.count() == 0) {
            SecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_playlist_button),
                icon = ImageVector.vectorResource(R.drawable.plus_icon),
                onClick = {}
            )
        } else {
            LazyColumn() {
                itemsIndexed(myPlaylists, key = { _, item -> item.id }) { index, item ->
                    PlaylistItem(
                        modifier = Modifier.padding(vertical = 12.dp),
                        playlistItemUi = item,
                        onClick = {}
                    )
                    if(index != myPlaylists.lastIndex){
                        HorizontalDivider(thickness = 2.dp, color = Outline)
                    }
                }
            }
        }
    }
}