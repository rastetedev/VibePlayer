package com.raulastete.vibeplayer.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.raulastete.vibeplayer.ui.theme.Accent
import kotlin.math.cos
import kotlin.math.sin

private const val MAIN_CIRCLE_RADIUS_FACTOR = 8 / 20f
private const val INNER_CIRCLE_1_RADIUS_FACTOR = 6 / 20f
private const val INNER_CIRCLE_2_RADIUS_FACTOR = 3 / 20f
private val MAIN_CIRCLE_STROKE_WIDTH = 4.dp
private val DECORATIVE_STROKE_WIDTH = 2.dp
private val CENTER_DOT_RADIUS = 4.dp
private const val ARC_SWEEP_ANGLE = 90f
private const val ANIMATION_DURATION_MS = 2000
private val LINE_WIDTH = 2.dp

@Composable
fun ScanningAnimation(
    modifier: Modifier = Modifier
) {

    val infiniteTransition = rememberInfiniteTransition(label = "infinite scanning transition")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ANIMATION_DURATION_MS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scanning angle"
    )

    Canvas(
        modifier = modifier
    ) {

        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val mainRadius = size.height * MAIN_CIRCLE_RADIUS_FACTOR

        val mainCircleStrokePx = MAIN_CIRCLE_STROKE_WIDTH.toPx()
        val decorativeStrokePx = DECORATIVE_STROKE_WIDTH.toPx()
        val centerDotRadiusPx = CENTER_DOT_RADIUS.toPx()

        val arcSize = mainRadius * 2
        val arcSizePx = Size(arcSize, arcSize)
        val arcTopLeft = Offset(centerX - mainRadius, centerY - mainRadius)

        val arcBrush = Brush.sweepGradient(
            0.0f to Color.Transparent,
            0.2f to Accent.copy(alpha = 0.5f),
            1.0f to Color.Transparent,
            center = center
        )

        drawBackgroundCircles(
            decorativeStrokePx,
            mainCircleStrokePx,
            centerDotRadiusPx,
            mainRadius
        )

        drawAnimatedElements(angle, mainRadius, arcBrush, arcTopLeft, arcSizePx)
    }
}

private fun DrawScope.drawBackgroundCircles(
    thinStrokePx: Float,
    thickStrokePx: Float,
    centerDotRadiusPx: Float,
    mainRadius: Float
) {

    drawDecorativeCircles(thinStrokePx)
    drawMainCircle(thickStrokePx, mainRadius)
    drawMainCircle(thickStrokePx, mainRadius)
    drawInnestFilledCircle(centerDotRadiusPx)
}

private fun DrawScope.drawDecorativeCircles(
    thinStrokePx: Float
) {
    drawCircle(
        color = Accent.copy(alpha = 0.1f),
        style = Stroke(width = thinStrokePx)
    )

    drawCircle(
        color = Accent.copy(alpha = 0.1f),
        radius = size.height * INNER_CIRCLE_1_RADIUS_FACTOR,
        style = Stroke(width = thinStrokePx)
    )
    drawCircle(
        color = Accent.copy(alpha = 0.1f),
        radius = size.height * INNER_CIRCLE_2_RADIUS_FACTOR,
        style = Stroke(width = thinStrokePx)
    )
}

private fun DrawScope.drawMainCircle(
    thickStrokePx: Float,
    mainRadius: Float
) {
    drawCircle(
        color = Accent,
        radius = mainRadius,
        style = Stroke(width = thickStrokePx)
    )
}

private fun DrawScope.drawInnestFilledCircle(
    centerDotRadiusPx: Float
){
    drawCircle(color = Accent, radius = centerDotRadiusPx)
}

private fun DrawScope.drawAnimatedElements(
    angle: Float,
    mainRadius: Float,
    arcBrush: Brush,
    arcTopLeft: Offset,
    arcSizePx: Size,

    ) {
    val startAngleForArc = angle - 90
    val angleInRad = Math.toRadians(startAngleForArc.toDouble()).toFloat()

    drawArc(
        brush = arcBrush,
        startAngle = startAngleForArc,
        sweepAngle = ARC_SWEEP_ANGLE,
        useCenter = true,
        size = arcSizePx,
        topLeft = arcTopLeft
    )

    val endX = center.x + mainRadius * cos(angleInRad)
    val endY = center.y + mainRadius * sin(angleInRad)
    drawLine(
        color = Accent,
        start = center,
        end = Offset(x = endX, y = endY),
        strokeWidth = LINE_WIDTH.toPx()
    )
}