package com.chernakovs.websocket_currencyexchangerates.ui.rates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.chernakovs.websocket_currencyexchangerates.data.dto.SocketObject

@Composable
fun MainScreen(
    viewModel: RatesViewModel
) {
    val value = viewModel.channel.collectAsState(initial = SocketObject("NULL"))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value.value.price)
    }
}