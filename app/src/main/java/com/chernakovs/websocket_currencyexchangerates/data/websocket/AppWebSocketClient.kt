package com.chernakovs.websocket_currencyexchangerates.data.websocket

import android.util.Log
import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI


class AppWebSocketClient(serverUri: URI?) : WebSocketClient(serverUri) {

    private val socketEventFlow: MutableSharedFlow<SocketObject> = MutableSharedFlow()

    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.d(TAG, "onOpen")
        this.send(
            "{\n" +
                    "    \"type\": \"subscribe\",\n" +
                    "    \"channels\": [{ \"name\": \"ticker\", \"product_ids\": [\"BTC-EUR\"] }]\n" +
                    "}"
        )
    }

    @DelicateCoroutinesApi
    override fun onMessage(message: String?) {
        Log.d(TAG, "onMessage: $message")
        if (message != null) {
            writeToFlow(message)
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.d(TAG, "onClose")
        this.send(
            "{\n" +
                    "    \"type\": \"unsubscribe\",\n" +
                    "    \"channels\": [\"ticker\"]\n" +
                    "}"
        )
    }

    override fun onError(ex: Exception?) {
        Log.e(TAG, "onError: ${ex?.message}")
    }

    fun subscribe(): SharedFlow<SocketObject> {
        return socketEventFlow
    }

    @DelicateCoroutinesApi
    private fun writeToFlow(message: String) {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<SocketObject> = moshi.adapter(SocketObject::class.java)
        val socketObject = adapter.fromJson(message)
        GlobalScope.launch {
            if (socketObject != null) {
                socketEventFlow.emit(socketObject)
            }
        }
    }

    companion object {
        const val TAG = "SocketService"
    }

}