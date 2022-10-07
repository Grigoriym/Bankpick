package com.grappim.bankpick.compose.core.chart

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.grappim.bankpick.compose.ui.theme.BankPickNavyBlue
import com.patrykandpatryk.vico.core.component.OverlayingComponent
import com.patrykandpatryk.vico.core.component.shape.ShapeComponent
import com.patrykandpatryk.vico.core.component.shape.Shapes
import com.patrykandpatryk.vico.core.marker.Marker

@Composable
fun getMarker(): Marker {
    val outerColor = if (isSystemInDarkTheme()) {
        Color.White.toArgb()
    } else {
        BankPickNavyBlue.toArgb()
    }

    val indicatorOuter = ShapeComponent(
        shape = Shapes.pillShape,
        color = outerColor
    )

    val innerColor = if (isSystemInDarkTheme()) {
        BankPickNavyBlue.toArgb()
    } else {
        Color.White.toArgb()
    }

    val indicatorCenter = ShapeComponent(
        shape = Shapes.pillShape,
        color = innerColor
    )
    val indicatorInner = ShapeComponent(
        shape = Shapes.pillShape,
        color = innerColor
    )

    val indicator = OverlayingComponent(
        outer = indicatorOuter,
        innerPaddingAllDp = 5f,
        inner = OverlayingComponent(
            outer = indicatorCenter,
            inner = indicatorInner,
        ),
    )
    return object : BankPickMarkerComponent(
        indicator = indicator
    ) {
        init {
            indicatorSizeDp = 22f
            onApplyEntryColor = { entryColor ->
                with(indicatorOuter) {
                    setShadow(radius = 10f, color = entryColor)
                }
            }
        }
    }
}