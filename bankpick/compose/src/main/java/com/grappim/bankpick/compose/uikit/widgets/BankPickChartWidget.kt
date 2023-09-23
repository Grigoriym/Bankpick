package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.bankpick.compose.core.chart.ChartDateEntry
import com.grappim.bankpick.compose.core.chart.getMarker
import com.grappim.bankpick.compose.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.patrykandpatryk.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatryk.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatryk.vico.compose.chart.Chart
import com.patrykandpatryk.vico.compose.chart.line.lineChart
import com.patrykandpatryk.vico.compose.chart.line.lineSpec
import com.patrykandpatryk.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatryk.vico.compose.component.shapeComponent
import com.patrykandpatryk.vico.core.axis.horizontal.HorizontalAxis
import com.patrykandpatryk.vico.core.component.shape.Shapes
import com.patrykandpatryk.vico.core.component.text.textComponent
import com.patrykandpatryk.vico.core.dimensions.MutableDimensions
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.marker.Marker
import com.patrykandpatryk.vico.core.marker.MarkerVisibilityChangeListener
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun BankPickChartWidget(
    modifier: Modifier = Modifier
) {
    var markerVisibility by remember { mutableStateOf(false) }
    var markerValue: Marker? by remember {
        mutableStateOf(null)
    }

    val entries = listOf(
        "2022-07-01" to 1.0f,
        "2022-08-01" to 3.0f,
        "2022-09-01" to 2.0f,
        "2022-10-01" to 7.0f,
        "2022-11-01" to 5.0f,
    ).mapIndexed { index, (dateString, y) ->
        ChartDateEntry(
            localDate = LocalDate.parse(dateString),
            x = index.toFloat(),
            y = y
        )
    }.let { entryCollection -> ChartEntryModelProducer(entryCollection) }

    val activeLabel = textComponent {
        textSizeSp = 15f
        color = Color.White.toArgb()
        padding = MutableDimensions(
            horizontalDp = 4f,
            verticalDp = 4f
        )
        background = shapeComponent(
            shape = Shapes.pillShape,
            color = BankPickNavyBlue
        )
    }
    val inactiveLabel = textComponent {
        textSizeSp = 15f
        color = BankPickSpunPearl.toArgb()
    }

    val bottomAxis = rememberBottomAxis(
        valueFormatter = { value, chartValues ->
            (chartValues
                .chartEntryModel
                .entries[0]
                .getOrNull(value.toInt()) as ChartDateEntry?)
                ?.localDate
                ?.run {
                    this.month.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    )
                }
                .orEmpty()
        },
        label = inactiveLabel
    )

    Box(modifier = modifier) {
        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = BankPickNavyBlue,
                        lineBackgroundShader = verticalGradient(
                            colors = arrayOf(
                                BankPickNavyBlue.copy(alpha = 0.3f),
                                BankPickNavyBlue.copy(alpha = 0f)
                            )
                        ),
                        lineThickness = 5.dp,
                        lineCap = StrokeCap.Round
                    )
                )
            ),
            startAxis = null,
            bottomAxis = bottomAxis,
            chartModelProducer = remember { entries },
            marker = getMarker(),
        )
    }
}

@Composable
@Preview
private fun BankPickChartWidget() {
    BankPickTheme {
        BankPickChartWidget()
    }
}