package com.raulastete.vibeplayer.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.raulastete.vibeplayer.R

@Composable
fun ArtWorkImage(
    artwork: Uri,
    modifier: Modifier = Modifier,
    placeholderSize: Dp
) {
    var showPlaceholder by remember { mutableStateOf(true) }
    Box(
        modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF1FF95).copy(alpha = 0.4f),
                        Color(0xFFF1FF95).copy(alpha = 0.1f),
                    ),
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artwork)
                .crossfade(true)
                .build(),
            onSuccess = {
                showPlaceholder = false
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        if (showPlaceholder) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.track_image_placeholder_icon),
                modifier = Modifier.size(placeholderSize),
                contentDescription = null
            )
        }
    }
}