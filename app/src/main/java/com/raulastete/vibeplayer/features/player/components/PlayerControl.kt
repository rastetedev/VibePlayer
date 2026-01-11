package com.raulastete.vibeplayer.features.player.components

import android.util.Log.i
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.theme.ButtonHover
import com.raulastete.vibeplayer.ui.theme.SurfaceBG
import com.raulastete.vibeplayer.ui.theme.TextPrimary
import com.raulastete.vibeplayer.ui.theme.TextSecondary

@Composable
fun PlayerControl(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
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
        SecondaryButton(
            icon = ImageVector.vectorResource(R.drawable.previous_track_icon),
            onClick = onPreviousButtonClick
        )
        Spacer(Modifier.width(16.dp))
        MainButton(
            icon = if (isPlaying) ImageVector.vectorResource(R.drawable.pause_track_icon)
            else ImageVector.vectorResource(R.drawable.play_track_icon),
            onClick = if (isPlaying) onPauseButtonClick else onPlayButtonClick
        )
        Spacer(Modifier.width(16.dp))
        SecondaryButton(
            icon = ImageVector.vectorResource(R.drawable.next_track_icon),
            onClick = onNextButtonClick
        )
    }
}

@Composable
private fun SecondaryButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(24.dp)
            .background(color = ButtonHover, shape = CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(16.dp)
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
        modifier = Modifier
            .size(60.dp)
            .background(color = TextPrimary, shape = CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = SurfaceBG,
            modifier = Modifier.size(24.dp)
        )
    }
}