package ameri.hossein.cryptoapp.ui.main

sealed class MainIntent {
    object CallCoinsList : MainIntent()
    object CallSupportedCurrencies : MainIntent()
    object CallCoinsMarkets : MainIntent()
    data class CallCoinPrice(val id: String, val currency: String) : MainIntent()
    data class NavigateToDetail(val id: String) : MainIntent()
}