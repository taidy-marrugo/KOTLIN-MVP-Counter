package com.globant.counter

import com.globant.counter.mvp.model.CalculatorItem
import com.globant.counter.mvp.model.CalculatorModel
import com.globant.counter.mvp.model.CountModel
import com.globant.counter.mvp.presenter.CountPresenter
import com.globant.counter.mvp.view.CountView
import com.globant.counter.utils.bus.RxBus
import com.globant.counter.utils.bus.observer.OnCalculatorActionItemPressedBusObserver
import com.globant.counter.utils.bus.observer.OnCalculatorEqualButtonPressedBusObserver
import com.globant.counter.utils.bus.observer.OnCalculatorNumberButtonPressedBusObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class PresenterTest {

    private var presenter: CountPresenter? = null
    private lateinit var model: CalculatorModel
    @Mock
    lateinit var view: CountView
    @Mock
    lateinit var activity: MainActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model = CalculatorModel()

        // When
        whenever(view.activity).thenReturn(activity)

        presenter = CountPresenter(model!!, view)
    }

    @Test

    fun `when plus is pressed, sum the inputs and return the right sum`() {
        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(7.0f))
        RxBus.post(OnCalculatorActionItemPressedBusObserver.OnCalculatorActionButtonPressed(CalculatorItem.ADDITION))
        verify(view, times(1)).setExpression("7.0 +")

        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(5.0f))

        assertEquals(model?.action, CalculatorItem.ADDITION)

        RxBus.post(OnCalculatorEqualButtonPressedBusObserver.OnCalculatorEqualButtonPressed())

        assertEquals(model?.value1, 12.0f)
        assertEquals(model?.value2, 0.0f)

    }

    @Test
    fun `when substraction is pressed, substracts the inputs and return the right rest`() {
        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(10.0f))
        RxBus.post(OnCalculatorActionItemPressedBusObserver.OnCalculatorActionButtonPressed(CalculatorItem.SUBSTRACTION))
        verify(view, times(1)).setExpression("10.0 -")

        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(3.0f))

        assertEquals(model?.action,CalculatorItem.SUBSTRACTION)

        RxBus.post(OnCalculatorEqualButtonPressedBusObserver.OnCalculatorEqualButtonPressed())


        assertEquals(model?.value1, 7.0f)
        assertEquals(model?.value2, 0.0f)
    }

    @Test
    fun `when multiplier is pressed, multiply the inputs and return the right multiplication`() {
        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(7.0f))
        RxBus.post(OnCalculatorActionItemPressedBusObserver.OnCalculatorActionButtonPressed(CalculatorItem.MULTIPLICATION))
        verify(view, times(1)).setExpression("7.0 *")

        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(4.0f))

        assertEquals(model?.action, CalculatorItem.MULTIPLICATION)

        RxBus.post(OnCalculatorEqualButtonPressedBusObserver.OnCalculatorEqualButtonPressed())


        assertEquals(model?.value1, 28.0f)
        assertEquals(model?.value2, 0.0f)
    }

    @Test
    fun `when division is pressed, divide the inputs and return the right division`() {
        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(25.0f))
        RxBus.post(OnCalculatorActionItemPressedBusObserver.OnCalculatorActionButtonPressed(CalculatorItem.DIVISION))
        verify(view, times(1)).setExpression("25.0 /")

        RxBus.post(OnCalculatorNumberButtonPressedBusObserver.OnCalculatorNumberButtonPressed(5.0f))

        assertEquals(model?.action, CalculatorItem.DIVISION)

        RxBus.post(OnCalculatorEqualButtonPressedBusObserver.OnCalculatorEqualButtonPressed())


        assertEquals(model?.value1, 5.0f)
        assertEquals(model?.value2, 0.0f)
    }


}