package com.raulastete.vibeplayer.features.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.SecondaryIconButton
import com.raulastete.vibeplayer.ui.theme.SurfaceBG
import com.raulastete.vibeplayer.ui.theme.TextPrimary

enum class MusicTrackRepeatingMode {
    OFF, REPEAT_ONE, REPEAT_ALL
}

@Composable
fun PlayerControl(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    isInShuffleMode: Boolean,
    repeatingMode: MusicTrackRepeatingMode,
    onShuffleButtonClick: () -> Unit,
    onRepeatButtonClick: () -> Unit,
    onPreviousButtonClick: () -> Unit,
    onPlayButtonClick: () -> Unit,
    onPauseButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SecondaryIconButton(
            modifier = Modifier.size(44.dp),
            icon = ImageVector.vectorResource(R.drawable.shuffle_icon),
            isOn = isInShuffleMode,
            onClick = onShuffleButtonClick
        )
        Spacer(Modifier.weight(1f))
        SecondaryIconButton(
            modifier = Modifier.size(44.dp),
            icon = ImageVector.vectorResource(R.drawable.previous_skip_filled_icon),
            onClick = onPreviousButtonClick
        )
        Spacer(Modifier.width(16.dp))
        MainButton(
            icon = if (isPlaying) ImageVector.vectorResource(R.drawable.pause_filled_icon)
            else ImageVector.vectorResource(R.drawable.play_filled_icon),
            onClick = if (isPlaying) onPauseButtonClick else onPlayButtonClick
        )
        Spacer(Modifier.width(16.dp))
        SecondaryIconButton(
            modifier = Modifier.size(44.dp),
            icon = ImageVector.vectorResource(R.drawable.next_skip_filled_icon),
            onClick = onNextButtonClick
        )
        Spacer(Modifier.weight(1f))
        SecondaryIconButton(
            modifier = Modifier.size(44.dp),
            icon = when (repeatingMode) {
                MusicTrackRepeatingMode.OFF -> ImageVector.vectorResource(R.drawable.repeat_off_icon)
                MusicTrackRepeatingMode.REPEAT_ONE -> ImageVector.vectorResource(R.drawable.repeat_one_icon)
                MusicTrackRepeatingMode.REPEAT_ALL -> ImageVector.vectorResource(R.drawable.repeat_icon)
            },
            isOn = repeatingMode != MusicTrackRepeatingMode.OFF,
            onClick = onRepeatButtonClick
        )
    }
}


@Composable
private fun MainButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(60.dp),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = TextPrimary,
            contentColor = SurfaceBG
        )
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null
        )
    }
}