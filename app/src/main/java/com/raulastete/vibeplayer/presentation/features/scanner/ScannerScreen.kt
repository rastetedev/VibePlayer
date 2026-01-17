package com.raulastete.vibeplayer.presentation.features.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.presentation.ui.components.InternalTopBar
import com.raulastete.vibeplayer.presentation.ui.components.PrimaryButton
import com.raulastete.vibeplayer.presentation.ui.components.ScanningAnimation
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonPrimary
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonPrimary30
import com.raulastete.vibeplayer.presentation.ui.theme.TextDisabled
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyLargeMedium

@Composable
fun ScannerScreen(
    viewModel: ScannerViewModel = viewModel(),
    onBackActionClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScannerScreenContent(
        uiState = uiState,
        onBackActionClick = onBackActionClick,
        scan = viewModel::scan
    )
}

@Composable
private fun ScannerScreenContent(
    uiState: ScannerUiState,
    onBackActionClick: () -> Unit,
    scan: () -> Unit
) {
    Scaffold(
        topBar = {
            InternalTopBar(
                navigationIcon = ImageVector.vectorResource(R.drawable.arrow_left_icon),
                title = stringResource(R.string.scanner_screen_title),
                onBackActionClick = onBackActionClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScanningAnimation(Modifier.size(124.dp))
            Spacer(Modifier.height(24.dp))
            OptionSelector(
                title = stringResource(R.string.scanner_track_duration_selector),
                options = TrackDuration.entries,
                optionSelected = uiState.currentDurationFilter
            ) { }
            Spacer(Modifier.height(16.dp))
            OptionSelector(
                title = stringResource(R.string.scanner_track_size_selector),
                options = TrackSize.entries,
                optionSelected = uiState.currentSizeFilter
            ) { }
            Spacer(Modifier.height(24.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = if (uiState.isScanning) stringResource(R.string.scanner_loading_state_button)
                else stringResource(R.string.scanner_default_state_button),
                showLoading = uiState.isScanning,
                enabled = uiState.isScanning.not(),
                onClick = scan,
            )
        }
    }
}

enum class TrackDuration(override val valueToDisplay: String) : Selectable {
    SECONDS_30("30s"), SECONDS_60("60s");
}

enum class TrackSize(override val valueToDisplay: String) : Selectable {
    KB_100("100KB"), KB_500("500KB");
}

@Composable
fun <T : Selectable> OptionSelector(
    title: String,
    options: List<T>,
    optionSelected: T,
    onClick: (T) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLargeMedium.copy(color = TextSecondary)
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                OptionSelectorItem(
                    modifier = Modifier.weight(1f),
                    selected = option == optionSelected,
                    value = option.valueToDisplay,
                    onClick = { onClick(option) }
                )
            }
        }
    }
}

@Composable
fun OptionSelectorItem(
    value: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )
            .border(
                width = 1.dp,
                color = ButtonPrimary30,
                shape = RoundedCornerShape(100.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = ButtonPrimary,
                unselectedColor = TextSecondary,
                disabledUnselectedColor = TextDisabled
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLargeMedium
        )
    }
}

interface Selectable {
    val valueToDisplay: String
}