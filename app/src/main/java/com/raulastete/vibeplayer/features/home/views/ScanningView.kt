package com.raulastete.vibeplayer.features.home.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.ScanningAnimation
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyMediumRegular

@Composable
 fun ScanningView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScanningAnimation()
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.home_scanning_state_description),
            style = MaterialTheme.typography.bodyMediumRegular.copy(
                TextSecondary
            )
        )
    }
}