package com.raulastete.vibeplayer.features.home.views

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.features.home.components.TrackItem
import com.raulastete.vibeplayer.features.home.components.TrackItemUi
import com.raulastete.vibeplayer.ui.theme.Outline

@Composable
 fun TrackListView(
    trackItems: List<TrackItemUi>,
    onClickTrackItem: (TrackItemUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(
            trackItems,
            key = { index, trackItem -> trackItem.title }
        ) { index, trackItemUi ->

            TrackItem(
                modifier = Modifier.padding(vertical = 12.dp),
                trackItemUi = trackItemUi,
                onClick = { onClickTrackItem(trackItemUi) }
            )

            if (index != trackItems.lastIndex) {
                HorizontalDivider(thickness = 2.dp, color = Outline)
            }
        }
    }
}

@Composable
@Preview
private fun TrackItemPreview() {
    TrackItem(
        trackItemUi = TrackItemUi(
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