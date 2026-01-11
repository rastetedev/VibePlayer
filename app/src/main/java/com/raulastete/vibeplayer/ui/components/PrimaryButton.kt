package com.raulastete.vibeplayer.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.ui.theme.ButtonHover
import com.raulastete.vibeplayer.ui.theme.ButtonPressed
import com.raulastete.vibeplayer.ui.theme.ButtonPrimary
import com.raulastete.vibeplayer.ui.theme.TextDisabled
import com.raulastete.vibeplayer.ui.theme.TextPrimary
import com.raulastete.vibeplayer.ui.theme.VibePlayerTheme
import com.raulastete.vibeplayer.ui.theme.bodyLargeMedium

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    showLoading: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val colors = ButtonDefaults.buttonColors(
        containerColor = if (isPressed) ButtonPressed else ButtonPrimary,
        contentColor = TextPrimary,
        disabledContainerColor = ButtonHover,
        disabledContentColor = TextDisabled
    )

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        colors = colors,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = TextDisabled
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(text = text, style = MaterialTheme.typography.bodyLargeMedium)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun PrimaryButtonStatesPreview() {
    VibePlayerTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PrimaryButton(text = "Enabled", onClick = { })

            PrimaryButton(text = "Disabled", onClick = { }, enabled = false)
        }
    }
}