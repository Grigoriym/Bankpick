package com.grappim.bankpick.compose.utils

import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow

/**
 * this class handle all the PositionRelated behaviour,
 * calculating the scroll amount and even the new
 * scroll amount if the total keyboard height value changes during the animation
 */
class PositionManager {

    private val positions = HashMap<Int, PositionHandler>()
    private lateinit var parent: LayoutCoordinates

    var isParentSet = false
        private set

    private var currentHeight: Int = 0

    private var scrollToConsumeForKeyboard: Float = 0F

    fun setupParent(layoutCoordinates: LayoutCoordinates) {
        parent = layoutCoordinates
        if (parent.size.height != 0)
            isParentSet = true
    }

    fun registerPosition(index: Int, positionHandler: PositionHandler) {
        positions[index] = positionHandler
    }

    fun getScrollAmount(index: Int): Float {
        return positions[index]?.calculateOffset(
            scrollToConsumeForKeyboard,
            parent,
            scrollToConsumeForKeyboard == currentHeight.toFloat()
        ) ?: 0F
    }

    fun setCurrentHeight(currentHeight: Int) {
        scrollToConsumeForKeyboard =
            if (this.currentHeight != 0 && currentHeight != 0 && this.currentHeight != currentHeight) {
                (currentHeight - this.currentHeight).toFloat()
            } else currentHeight.toFloat()

        this.currentHeight = currentHeight
    }
}

class PositionHandler(
    layoutCoordinates: LayoutCoordinates,
    private val paddingBottomPx: Float = 0F
) {

    private val bottom = layoutCoordinates.boundsInWindow().bottom

    fun calculateOffset(
        keyboardHeight: Float,
        parent: LayoutCoordinates,
        consumePadding: Boolean
    ): Float {

        val parentBottom = parent.boundsInWindow().bottom

        val newBottom = parentBottom - keyboardHeight
        return bottom - newBottom + if (consumePadding) paddingBottomPx else 0F
    }

}