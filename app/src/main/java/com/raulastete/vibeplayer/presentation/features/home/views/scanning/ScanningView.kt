package com.raulastete.vibeplayer.presentation.features.home.views.scanning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.presentation.ui.components.ScanningAnimation
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyMediumRegular

@Composable
 fun ScanningView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScanningAnimation(Modifier.size(124.dp))
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.home_scanning_state_description),
            style = MaterialTheme.typography.bodyMediumRegular.copy(
                TextSecondary
            )
        )
    }
}