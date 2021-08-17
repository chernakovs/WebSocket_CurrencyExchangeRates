package com.chernakovs.websocket_currencyexchangerates.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SocketObject(
    val price: String
)
