@file:OptIn(ExperimentalMaterial3Api::class)

package com.raulastete.vibeplayer.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.presentation.ui.theme.Accent
import com.raulastete.vibeplayer.presentation.ui.theme.ButtonHover
import com.raulastete.vibeplayer.presentation.ui.theme.TextSecondary
import com.raulastete.vibeplayer.presentation.ui.theme.bodyLargeMedium

@Composable
fun MainTopBar(
    showSearchButton: Boolean,
    onScanActionClick: () -> Unit,
    onSearchActionClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.topbar_icon),
                    modifier = Modifier.size(24.dp),
                    contentDescription = null,
                    tint = Accent
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyLargeMedium.copy(Accent)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onScanActionClick,
                modifier = Modifier.background(ButtonHover, shape = CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.scan_icon),
                    contentDescription = stringResource(R.string.scan_action)
                )
            }
            AnimatedVisibility(showSearchButton) {
                Row {
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = onSearchActionClick,
                        modifier = Modifier.background(ButtonHover, shape = CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.search_icon),
                            contentDescription = stringResource(R.string.search_action)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun InternalTopBar(
    title: String = "",
    navigationIcon: ImageVector,
    onBackActionClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.bodyLargeMedium)
        },
        navigationIcon = {
            IconButton(
                onClick = onBackActionClick,
                modifier = Modifier.background(ButtonHover, shape = CircleShape)
            ) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = title,
                    tint = TextSecondary
                )
            }
        }
    )
}