package ameri.hossein.cryptoapp.data.repository

import ameri.hossein.cryptoapp.data.network.ApiServices
import ameri.hossein.cryptoapp.utils.PER_PAGE
import ameri.hossein.cryptoapp.utils.USD
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getCoinsList() = api.getCoinsList()
    suspend fun getSupportedCurrencies() = api.getSupportedCurrencies()
    suspend fun getCoinPrice(ids: String, currency: String) = api.getCoinPrice(ids, currency)
    suspend fun getCoinsMarkets() = api.getCoinsMarkets(USD, PER_PAGE, true)
}