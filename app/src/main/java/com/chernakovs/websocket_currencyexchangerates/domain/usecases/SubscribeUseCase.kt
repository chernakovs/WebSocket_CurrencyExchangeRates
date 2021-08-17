package com.chernakovs.websocket_currencyexchangerates.domain.usecases

import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import com.chernakovs.websocket_currencyexchangerates.domain.Repository
import com.chernakovs.websocket_currencyexchangerates.domain.model.CurrencyExchangeRates
import kotlinx.coroutines.flow.SharedFlow

class SubscribeUseCase(private val repository: Repository ) {
    fun subscribe(): SharedFlow<SocketObject> = repository.subscribe()
}