package com.globant.counter.mvp.view


import android.app.Activity
import android.text.TextUtils
import com.globant.counter.mvp.model.CalculatorItem
import com.globant.counter.utils.bus.RxBus
import com.globant.counter.utils.bus.observer.OnCalculatorActionItemPressedBusObserver
import com.globant.counter.utils.bus.observer.OnCalculatorClearButtonPressedBusObserver
import com.globant.counter.utils.bus.observer.OnCalculatorEqualButtonPressedBusObserver
import com.globant.counter.utils.bus.observer.OnCalculatorNumberButtonPressedBusObserver
import kotlinx.android.synthetic.main.activity_main.*


class CountView(activity: Activity) : ActivityView(activity) {
    fun setExpression(expression: String) {
        activity?.textExpression?.text = expression
    }

    init {
        activity.btnSum.setOnClickListener {
            postOperation(CalculatorItem.ADDITION)
        }

        activity.btnDiv.setOnClickListener {
            postOperation(CalculatorItem.DIVISION)
        }

        activity.btnMulti.setOnClickListener {
            postOperation(CalculatorItem.MULTIPLICATION)
        }

        activity.btnRest.setOnClickListener {
            postOperation(CalculatorItem.SUBSTRACTION)
        }

        activity.btnEqual.setOnClickListener {
            getInput()
            RxBus.post(OnCalculatorEqualButtonPressedBusObserver.OnCalculatorEqualButtonPressed())
        }

        activity.btnClear.setOnClickListener {
            RxBus.post(OnCalculatorClearButtonPressedBusObserver.OnCalculatorClearButton())
        }

    }

    private fun getInput() {
        var input = activity?.inputNumber?.text.toString()
        if (!input.isNullOrEmpty()) {
            RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(input.toFloat()))
        }
        activity?.inputNumber?.setText("")
    }

    fun clear() {
        activity?.inputNumber?.setText("")
        activity?.textExpression?.text = ""
    }
    private fun postOperation(operation: CalculatorItem) {
        getInput()
        RxBus.post(OnCalculatorActionItemPressedBusObserver.OnCalculatorActionButtonPressed(operation))
    }
}
