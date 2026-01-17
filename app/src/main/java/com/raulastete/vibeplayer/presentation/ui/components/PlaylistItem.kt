package com.raulastete.vibeplayer.presentation.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonPrimary
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonPrimary30
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyMediumRegular

data class PlaylistItemUi(
    val id: String,
    val artwork: Uri?,
    val title: String,
    val trackItemQuantity: String
)

@Composable
fun PlaylistItem(
    playlistItemUi: PlaylistItemUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier.clickable { onClick() },
        headlineContent = {
            Text(
                text = playlistItemUi.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                text = playlistItemUi.trackItemQuantity,
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
                    imageVector = ImageVector.vectorResource(R.drawable.playlist_duotone_icon),
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