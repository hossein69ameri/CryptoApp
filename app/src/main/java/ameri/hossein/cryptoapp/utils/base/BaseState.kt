package ameri.hossein.cryptoapp.utils.base

import ameri.hossein.cryptoapp.data.model.detail.ResponseDetail
import ameri.hossein.cryptoapp.data.model.main.ResponseCoinsList
import ameri.hossein.cryptoapp.data.model.main.ResponseCoinsMarket
import ameri.hossein.cryptoapp.data.model.main.ResponseSupportedCurrencies

sealed class BaseState(val error: String? = null) {
    object Idle : BaseState()
    object Loading : BaseState()
    class Error(error: String?) : BaseState(error)

    sealed class Main : BaseState() {
        data class LoadCoinsList(val coinsList: ResponseCoinsList) : Main()
        data class LoadSupportedCurrencies(val supportedList: ResponseSupportedCurrencies) : Main()
        data class LoadCoinPrice(val price: Double) : Main()
        data class LoadCoinsMarkets(val coinsMarkets: ResponseCoinsMarket) : Main()
        data class NavigateToDetail(val id: String) : Main()
        object LoadingCoinPrice : Main()
    }

    sealed class Detail : BaseState() {
        data class LoadCoinDetail(val detail: ResponseDetail) : Detail()
    }
}