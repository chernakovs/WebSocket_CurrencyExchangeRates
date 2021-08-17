package com.chernakovs.websocket_currencyexchangerates.ui.rates

import androidx.lifecycle.ViewModel
import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import com.chernakovs.websocket_currencyexchangerates.domain.usecases.SubscribeUseCase
import com.chernakovs.websocket_currencyexchangerates.domain.usecases.UnsubscribeUseCase
import kotlinx.coroutines.flow.SharedFlow

class RatesViewModel(
    private val subscribeUseCase: SubscribeUseCase,
    private val unsubscribeUseCase: UnsubscribeUseCase
) : ViewModel() {

    lateinit var channel: SharedFlow<SocketObject>

    fun subscribe() {
        channel = subscribeUseCase.subscribe()
    }

    fun unsubscribe() {
        unsubscribeUseCase.unsubscribe()
    }
}