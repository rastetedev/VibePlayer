package com.raulastete.vibeplayer.presentation.features.home.views.empty

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
import com.raulastete.vibeplayer.presentation.ui.components.PrimaryButton
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyMediumRegular

@Composable
 fun EmptyView(
    loadTracks: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.home_empty_state_title),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.home_empty_state_description),
            style = MaterialTheme.typography.bodyMediumRegular.copy(TextSecondary)
        )
        Spacer(Modifier.height(20.dp))
        PrimaryButton(
            text = stringResource(R.string.home_empty_state_button),
            onClick = loadTracks
        )
    }
}