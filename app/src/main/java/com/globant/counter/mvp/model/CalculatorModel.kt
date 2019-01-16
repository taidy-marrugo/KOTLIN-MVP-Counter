package com.globant.counter.mvp.model

open class CalculatorModel {

    var action: CalculatorItem = CalculatorItem.EMPTY_ITEM
    var value1: Float = 0.0F
    var value2: Float = 0.0F

    fun setOperator(value: Float) {
        if (action == CalculatorItem.EMPTY_ITEM) {
            value1 = value
        } else {
            value2 = value
        }
    }

    fun getExpression(): String {
        return "$value1 ${action.value}"
    }

    fun getResult(): String {
        try {
            return "${operate()}"
        } catch (e: IllegalStateException) {
            return "$value1"
        }
    }

    fun clear() {
        action = CalculatorItem.EMPTY_ITEM
        value1 = 0.0F
        value2 = 0.0F
    }

    @Throws(IllegalStateException::class)
    private fun operate(): Float {
        if (action == CalculatorItem.EMPTY_ITEM) {
            throw IllegalStateException()
        }

        value1 = when (action) {
            CalculatorItem.DIVS -> (if (value2 != 0.0F) value1 / value2 else 0.0F)
            CalculatorItem.MULTIP -> value1 * value2
            CalculatorItem.RESTA -> value1 - value2
            CalculatorItem.SUMA -> value1 + value2
            else -> 0.0F
        }
        value2 = 0.0F

        action = CalculatorItem.EMPTY_ITEM
        return value1

    }

}
