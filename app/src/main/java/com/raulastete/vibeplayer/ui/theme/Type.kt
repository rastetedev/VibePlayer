package com.raulastete.vibeplayer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.raulastete.vibeplayer.R

val HostGroteskFontFamily = FontFamily(
    Font(resId = R.font.hostgrotesk_regular, weight = FontWeight.Normal),
    Font(resId = R.font.hostgrotesk_medium, weight = FontWeight.Medium),
    Font(resId = R.font.hostgrotesk_semibold, weight = FontWeight.SemiBold),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = HostGroteskFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 32.sp
    ),
    titleMedium = TextStyle(
        fontFamily = HostGroteskFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp
    )
)

val Typography.bodyLargeRegular
    get() = TextStyle(
        fontFamily = HostGroteskFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )


val Typography.bodyLargeMedium
    get() = TextStyle(
        fontFamily = HostGroteskFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )

val Typography.bodyMediumRegular
    get() = TextStyle(
        fontFamily = HostGroteskFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )

val Typography.bodyMediumMedium
    get() = TextStyle(
        fontFamily = HostGroteskFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )