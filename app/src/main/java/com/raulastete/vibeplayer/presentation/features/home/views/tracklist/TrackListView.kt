package com.raulastete.vibeplayer.presentation.features.home.views.tracklist

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.presentation.features.home.components.MiniPlayer
import com.raulastete.vibeplayer.presentation.features.home.views.tracklist.playlists.PlayListTabScreen
import com.raulastete.vibeplayer.presentation.features.home.views.tracklist.songs.SongsTabScreen
import com.raulastete.vibeplayer.presentation.ui.components.MusicTrackItemUi
import com.raulastete.vibeplayer.presentation.ui.components.TrackItem
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonHover
import com.raulastete.vibeplayer.presentation.ui.theme.TextPrimary
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyMediumMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackListView(
    trackItems: List<MusicTrackItemUi>,
    onClickTrackItem: (MusicTrackItemUi) -> Unit,
    onShuffleTrackListActionClick: () -> Unit,
    onPlayTrackListClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedTabIndex by remember { mutableStateOf(0) }
    val titles = listOf("Songs", "Playlist")
    var showMiniPlayer by remember { mutableStateOf(true) }

    Box(modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            PrimaryTabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.Transparent,
                divider = {
                    HorizontalDivider(thickness = 1.dp, color = ButtonHover)
                },
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(
                            selectedTabIndex,
                            matchContentSize = true
                        ),
                        width = Dp.Unspecified,
                        shape = RoundedCornerShape(topEndPercent = 100, topStartPercent = 100),
                        color = TextPrimary
                    )
                },
                tabs = {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selectedContentColor = TextPrimary,
                            unselectedContentColor = TextSecondary,
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    title,
                                    style = MaterialTheme.typography.bodyMediumMedium
                                )
                            })
                    }
                }
            )

            Box(Modifier.weight(1f)) {
                when (selectedTabIndex) {
                    0 -> SongsTabScreen(
                        trackItems = trackItems,
                        onPlayTrackListClick = onPlayTrackListClick,
                        onShuffleTrackListActionClick = onShuffleTrackListActionClick,
                        onClickTrackItem = onClickTrackItem,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                    )

                    1 -> PlayListTabScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = showMiniPlayer,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier
                .align(Alignment.BottomCenter),
        ) {
            MiniPlayer(
                modifier = Modifier
                    .fillMaxWidth(),
                onDismissRequest = {
                    showMiniPlayer = false
                }
            )
        }
    }
}

@Composable
@Preview
private fun TrackItemPreview() {
    TrackItem(
        musicTrackItemUi = MusicTrackItemUi(
            id = "ID",
            artwork = Uri.EMPTY,
            title = "505",
            artistName = "Artic Monkeys",
            musicLengthFormatted = "4:14"
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = {}
    )
}