package ameri.hossein.cryptoapp.data.network

import ameri.hossein.cryptoapp.data.model.detail.ResponseDetail
import ameri.hossein.cryptoapp.data.model.main.ResponseCoinsList
import ameri.hossein.cryptoapp.data.model.main.ResponseCoinsMarket
import ameri.hossein.cryptoapp.data.model.main.ResponseSupportedCurrencies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("coins/list")
    suspend fun getCoinsList(): Response<ResponseCoinsList>

    @GET("simple/supported_vs_currencies")
    suspend fun getSupportedCurrencies(): Response<ResponseSupportedCurrencies>

    @GET("simple/price")
    suspend fun getCoinPrice(
        @Query("ids") ids: String, @Query("vs_currencies") currency: String
    ): Response<Map<String, Map<String, Double>>>

    @GET("coins/markets")
    suspend fun getCoinsMarkets(
        @Query("vs_currency") currency: String, @Query("per_page") perPage: Int, @Query("sparkline") sparkline: Boolean
    ): Response<ResponseCoinsMarket>

    @GET("coins/{id}")
    suspend fun getCoinDetail(@Path("id") id: String, @Query("sparkline") sparkline: Boolean): Response<ResponseDetail>
}