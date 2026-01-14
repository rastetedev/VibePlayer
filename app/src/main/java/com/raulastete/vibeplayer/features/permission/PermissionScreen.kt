package com.raulastete.vibeplayer.features.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.PrimaryButton
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyMediumRegular
import org.koin.androidx.compose.koinViewModel

@Composable
fun PermissionScreen(
    viewmodel: PermissionViewmodel = koinViewModel(),
    onPermissionGranted: () -> Unit
) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (activity?.checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PERMISSION_GRANTED) {
            onPermissionGranted()
        }
    }

    val readMediaPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            when {
                isGranted -> onPermissionGranted()
                else -> if (activity?.shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_AUDIO) == true) {
                    viewmodel.showRationalPermissionDialog()
                } else {
                    viewmodel.dismissRationalPermissionDialog()
                }
            }
        }
    )

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (context.checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PERMISSION_GRANTED) {
                    onPermissionGranted()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    PermissionScreenContent(
        uiState = uiState,
        requestPermission = {
            viewmodel.onPermissionRequested()
            readMediaPermissionResultLauncher.launch(Manifest.permission.READ_MEDIA_AUDIO)
        },
        openSettings = {
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null)
            ).also {
                context.startActivity(it)
            }
        },
        dismissRationalePermissionDialog = viewmodel::dismissRationalPermissionDialog
    )

}

@Composable
private fun PermissionScreenContent(
    uiState: PermissionUiState,
    requestPermission: () -> Unit,
    openSettings: () -> Unit,
    dismissRationalePermissionDialog: () -> Unit
) {
    val activity = LocalActivity.current

    val validatePermission = {
        activity?.checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PERMISSION_DENIED &&
                !activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_AUDIO)
                && uiState.permissionRequestedAtLeastOnce
    }
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
                onClick = {
                    if (validatePermission()) openSettings() else requestPermission()
                },
                text = stringResource(R.string.permission_request_button)
            )
        }

        if (uiState.showRationalPermissionDialog) {
            PermissionDialog(
                onDismiss = dismissRationalePermissionDialog,
                onTryAgain = requestPermission
            )
        }
    }
}

@Composable
fun PermissionDialog(
    onDismiss: () -> Unit,
    onTryAgain: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onTryAgain) {
                Text(stringResource(R.string.try_again_request_permission_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.dismiss_button))
            }
        },
        text = {
            Text(stringResource(R.string.permission_rationale_description_dialog))
        }
    )
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