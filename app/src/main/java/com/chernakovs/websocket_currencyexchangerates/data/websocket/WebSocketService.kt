package com.chernakovs.websocket_currencyexchangerates.data.websocket

import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import kotlinx.coroutines.flow.SharedFlow
import java.net.URI
import javax.net.ssl.SSLSocketFactory

class WebSocketService() {

    val WEB_SOCKET_URL = "wss://ws-feed.pro.coinbase.com"

    val webSocketUri: URI = URI(WEB_SOCKET_URL)

    private val webSocketClient = AppWebSocketClient(webSocketUri)

    val socketFactory: SSLSocketFactory = SSLSocketFactory.getDefault() as SSLSocketFactory

    fun subscribe(): SharedFlow<SocketObject> {
        webSocketClient.setSocketFactory(socketFactory)
        webSocketClient.connect()
        return webSocketClient.subscribe()
    }

    fun unsubscribe() {
        webSocketClient.close()
    }

}

