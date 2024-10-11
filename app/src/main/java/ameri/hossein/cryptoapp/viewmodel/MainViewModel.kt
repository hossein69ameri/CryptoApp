package ameri.hossein.cryptoapp.viewmodel

import ameri.hossein.cryptoapp.data.repository.MainRepository
import ameri.hossein.cryptoapp.ui.main.MainIntent
import ameri.hossein.cryptoapp.utils.base.BaseState
import ameri.hossein.cryptoapp.utils.network.ErrorResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val intentChannel = Channel<MainIntent>()
    private val _state = MutableStateFlow<BaseState>(BaseState.Idle)
    val state: StateFlow<BaseState> get() = _state

    init {
        handleIntents()
    }

    private fun handleIntents() = viewModelScope.launch {
        intentChannel.consumeAsFlow().collect { intent ->
            when (intent) {
                is MainIntent.CallCoinsList -> fetchingCoinsList()
                is MainIntent.CallSupportedCurrencies -> fetchingSupportedCurrencies()
                is MainIntent.CallCoinPrice -> fetchingCoinPrice(intent.id, intent.currency)
                is MainIntent.CallCoinsMarkets -> fetchingCoinsMarkets()
                is MainIntent.NavigateToDetail -> navigateToDetail(intent.id)
            }
        }
    }

    private fun fetchingCoinsList() = viewModelScope.launch {
        val response = repository.getCoinsList()
        if (response.isSuccessful) {
            response.body()?.let { BaseState.Main.LoadCoinsList(it) }?.let { _state.emit(it) }
        } else {
            val error = ErrorResponse(response).generateResponse()
            _state.emit(error)
        }
    }

    private fun fetchingSupportedCurrencies() = viewModelScope.launch {
        val response = repository.getSupportedCurrencies()
        if (response.isSuccessful) {
            response.body()?.let { BaseState.Main.LoadSupportedCurrencies(it) }?.let { _state.emit(it) }
        } else {
            val error = ErrorResponse(response).generateResponse()
            _state.emit(error)
        }
    }

    private fun fetchingCoinPrice(id: String, currency: String) = viewModelScope.launch {
        _state.emit(BaseState.Main.LoadingCoinPrice)
        val response = repository.getCoinPrice(id, currency)
        if (response.isSuccessful) {
            val responseData = response.body()
            responseData?.let {
                val coinInfo = it[id]?.get(currency)
                coinInfo?.let { info -> BaseState.Main.LoadCoinPrice(info) }?.let { price -> _state.emit(price) }
            }
        } else {
            val error = ErrorResponse(response).generateResponse()
            _state.emit(error)
        }
    }

    private fun fetchingCoinsMarkets() = viewModelScope.launch {
        _state.emit(BaseState.Loading)
        val response = repository.getCoinsMarkets()
        if (response.isSuccessful) {
            response.body()?.let { BaseState.Main.LoadCoinsMarkets(it) }?.let { _state.emit(it) }
        } else {
            val error = ErrorResponse(response).generateResponse()
            _state.emit(error)
        }
    }

    private fun navigateToDetail(id: String) = viewModelScope.launch {
        _state.emit(BaseState.Main.NavigateToDetail(id))
    }
}