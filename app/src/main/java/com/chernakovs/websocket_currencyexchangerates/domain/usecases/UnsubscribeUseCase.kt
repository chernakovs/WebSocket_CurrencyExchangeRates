package com.chernakovs.websocket_currencyexchangerates.domain.usecases

import com.chernakovs.websocket_currencyexchangerates.domain.Repository
import com.chernakovs.websocket_currencyexchangerates.domain.model.CurrencyExchangeRates
import kotlinx.coroutines.flow.SharedFlow

class UnsubscribeUseCase(private val repository: Repository) {
    fun unsubscribe() = repository.unsubscribe()
}