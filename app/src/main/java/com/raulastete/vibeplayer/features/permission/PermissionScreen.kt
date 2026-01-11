package com.raulastete.vibeplayer.features.permission

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.PrimaryButton
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyMediumRegular
import kotlinx.coroutines.delay

@Composable
fun PermissionScreen(
    onPermissionGranted: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(4_000)
        onPermissionGranted()
    }

    PermissionScreenContent()
}

@Composable
private fun PermissionScreenContent() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppBrand()
            Spacer(Modifier.height(4.dp))
            Text(
                stringResource(R.string.permission_description),
                style = MaterialTheme.typography.bodyMediumRegular.copy(color = TextSecondary),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))
            PrimaryButton(
                onClick = { },
                text = stringResource(R.string.permission_request_button)
            )
        }
    }
}

@Composable
private fun AppBrand() {
    Image(
        imageVector = ImageVector.vectorResource(R.drawable.topbar_icon),
        contentDescription = null,
        modifier = Modifier.size(56.dp),
    )
    Spacer(Modifier.height(20.dp))
    Text(
        stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PermissionScreenContentPreview() {
    PermissionScreenContent()
}