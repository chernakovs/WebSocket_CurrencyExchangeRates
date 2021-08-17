package com.chernakovs.websocket_currencyexchangerates.domain

import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import com.chernakovs.websocket_currencyexchangerates.domain.model.CurrencyExchangeRates
import kotlinx.coroutines.flow.SharedFlow

interface Repository {

    fun subscribe(): SharedFlow<SocketObject>

    fun unsubscribe()

}