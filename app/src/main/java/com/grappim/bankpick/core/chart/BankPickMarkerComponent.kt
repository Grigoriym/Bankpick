package com.grappim.bankpick.core.chart

import android.graphics.RectF
import com.patrykandpatryk.vico.core.chart.insets.Insets
import com.patrykandpatryk.vico.core.chart.segment.SegmentProperties
import com.patrykandpatryk.vico.core.component.Component
import com.patrykandpatryk.vico.core.component.shape.LineComponent
import com.patrykandpatryk.vico.core.context.DrawContext
import com.patrykandpatryk.vico.core.context.MeasureContext
import com.patrykandpatryk.vico.core.extension.half
import com.patrykandpatryk.vico.core.marker.Marker

open class BankPickMarkerComponent(
    private val indicator: Component?
) : Marker {

    var indicatorSizeDp: Float = 0f

    var onApplyEntryColor: ((entryColor: Int) -> Unit)? = null

    override fun draw(
        context: DrawContext,
        bounds: RectF,
        markedEntries: List<Marker.EntryModel>,
    ): Unit = with(context) {
        val halfIndicatorSize = indicatorSizeDp.half.pixels

        markedEntries.forEachIndexed { _, model ->
            onApplyEntryColor?.invoke(model.color)
            indicator?.draw(
                context,
                model.location.x - halfIndicatorSize,
                model.location.y - halfIndicatorSize,
                model.location.x + halfIndicatorSize,
                model.location.y + halfIndicatorSize,
            )
        }
    }
}