package com.raulastete.vibeplayer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.raulastete.vibeplayer.R

@Composable
 fun ScanningAnimation() {
    Image(
        imageVector = ImageVector.vectorResource(R.drawable.scanning_state_icon),
        contentDescription = null
    )
}