package com.chernakovs.websocket_currencyexchangerates.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.chernakovs.websocket_currencyexchangerates.data.RepositoryImpl
import com.chernakovs.websocket_currencyexchangerates.data.websocket.WebSocketService
import com.chernakovs.websocket_currencyexchangerates.domain.usecases.SubscribeUseCase
import com.chernakovs.websocket_currencyexchangerates.domain.usecases.UnsubscribeUseCase
import com.chernakovs.websocket_currencyexchangerates.ui.rates.MainScreen
import com.chernakovs.websocket_currencyexchangerates.ui.rates.RatesViewModel
import com.chernakovs.websocket_currencyexchangerates.ui.theme.WebSocket_CurrencyExchangeRatesTheme

class MainActivity : ComponentActivity() {

    private val repository = RepositoryImpl(service = WebSocketService())

    private val viewModel = RatesViewModel(
        SubscribeUseCase(repository),
        UnsubscribeUseCase(repository)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebSocket_CurrencyExchangeRatesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(viewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.subscribe()
    }

    override fun onPause() {
        super.onPause()
        viewModel.unsubscribe()
    }

}

