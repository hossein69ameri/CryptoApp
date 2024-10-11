package ameri.hossein.cryptoapp.data.repository

import ameri.hossein.cryptoapp.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getCoinDetail(id: String) = api.getCoinDetail(id, true)
}