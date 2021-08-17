package com.chernakovs.websocket_currencyexchangerates.data

import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import com.chernakovs.websocket_currencyexchangerates.data.websocket.WebSocketService
import com.chernakovs.websocket_currencyexchangerates.domain.Repository
import kotlinx.coroutines.flow.SharedFlow

class RepositoryImpl(
    private val service: WebSocketService
) : Repository {

        override fun subscribe(): SharedFlow<SocketObject> =
            service.subscribe()

        override fun unsubscribe() {
            service.unsubscribe()
        }

}