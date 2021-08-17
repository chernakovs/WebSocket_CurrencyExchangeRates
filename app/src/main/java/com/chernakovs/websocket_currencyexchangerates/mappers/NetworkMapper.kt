package com.chernakovs.websocket_currencyexchangerates.mappers

import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import com.chernakovs.websocket_currencyexchangerates.domain.model.CurrencyExchangeRates

class NetworkMapper {
    fun mapDtoSocketObjectToDomainCurrencyExchangeRates(socketObject: SocketObject): CurrencyExchangeRates
        = CurrencyExchangeRates(price = socketObject.price)
}