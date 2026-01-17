package com.raulastete.vibeplayer.presentation.features.home.views.tracklist.playlists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.presentation.ui.components.PlaylistItem
import com.raulastete.vibeplayer.presentation.ui.components.PlaylistItemUi
import com.raulastete.vibeplayer.presentation.ui.components.PrimaryButton
import com.raulastete.vibeplayer.presentation.ui.components.SecondaryButton
import com.raulastete.vibeplayer.presentation.ui.components.SecondaryIconButton
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonHover
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonPrimary
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonPrimary30
import com.raulastete.vibeplayer.presentation.ui.theme.Outline
import com.raulastete.vibeplayer.presentation.ui.theme.SurfaceBG
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyLargeMedium
import com.raulastete.vibeplayer.presentation.ui.theme.bodyLargeRegular
import com.raulastete.vibeplayer.presentation.ui.theme.bodyMediumRegular
import com.raulastete.vibeplayer.presentation.ui.theme.bodySmallRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayListTabScreen(
    modifier: Modifier = Modifier
) {

    var showCreatePlaylistDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Header(
                modifier = Modifier.fillMaxWidth(),
                playListCount = 1,
                onCreatePlayListButton = {
                    showCreatePlaylistDialog = true
                }
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

        AnimatedVisibility(
            visible = showCreatePlaylistDialog,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            ModalBottomSheet(
                dragHandle = null,
                onDismissRequest = {
                    showCreatePlaylistDialog = false
                },
                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
                containerColor = SurfaceBG
            ) {
                CreatePlayListDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun CreatePlayListDialog(
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Text(
            stringResource(R.string.create_playlist_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            trailingIcon = {
                Text(
                    "${text.count()}/40",
                    style = MaterialTheme.typography.bodySmallRegular.copy(color = TextSecondary),
                    modifier = Modifier.padding(end = 16.dp)
                )
            },
            textStyle = MaterialTheme.typography.bodyLargeRegular,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = ButtonHover,
                unfocusedContainerColor = ButtonHover,
                focusedBorderColor = Outline,
                unfocusedBorderColor = Outline,
            ),
            singleLine = true,
            shape = RoundedCornerShape(100)
        )
        Spacer(Modifier.height(20.dp))
        Row() {
            SecondaryButton(
                text = stringResource(R.string.cancel),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(12.dp))
            PrimaryButton(
                text = stringResource(R.string.create_button),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun Header(
    playListCount: Int,
    onCreatePlayListButton: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            onClick = onCreatePlayListButton
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
                    if (index != myPlaylists.lastIndex) {
                        HorizontalDivider(thickness = 2.dp, color = Outline)
                    }
                }
            }
        }
    }
}