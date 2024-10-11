package ameri.hossein.cryptoapp.viewmodel

import ameri.hossein.cryptoapp.data.repository.DetailRepository
import ameri.hossein.cryptoapp.ui.detail.DetailIntent
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
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {
    val intentChannel = Channel<DetailIntent>()
    private val _state = MutableStateFlow<BaseState>(BaseState.Idle)
    val state: StateFlow<BaseState> get() = _state

    init {
        handleIntents()
    }

    private fun handleIntents() = viewModelScope.launch {
        intentChannel.consumeAsFlow().collect { intent ->
            when (intent) {
                is DetailIntent.CallCoinDetail -> fetchingCoinDetail(intent.id)
            }
        }
    }

    private fun fetchingCoinDetail(id: String) = viewModelScope.launch {
        _state.emit(BaseState.Loading)
        val response = repository.getCoinDetail(id)
        if (response.isSuccessful) {
            response.body()?.let { BaseState.Detail.LoadCoinDetail(it) }?.let { _state.emit(it) }
        } else {
            val error = ErrorResponse(response).generateResponse()
            _state.emit(error)
        }
    }
}