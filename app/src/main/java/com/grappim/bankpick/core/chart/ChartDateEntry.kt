package com.grappim.bankpick.core.chart

import com.patrykandpatryk.vico.core.entry.ChartEntry
import java.time.LocalDate

class ChartDateEntry(
    val localDate: LocalDate,
    override val x: Float,
    override val y: Float
) : ChartEntry {
    override fun withY(y: Float): ChartEntry = ChartDateEntry(
        localDate = this.localDate,
        x = this.x,
        y = y
    )
}