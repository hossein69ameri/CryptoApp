package ameri.hossein.cryptoapp.data.model.main

import com.google.gson.annotations.SerializedName

class ResponseCoinsMarket : ArrayList<ResponseCoinsMarket.ResponseCoinsMarketItem>(){
    data class ResponseCoinsMarketItem(
        @SerializedName("ath")
        val ath: Double?, // 4878.26
        @SerializedName("ath_change_percentage")
        val athChangePercentage: Double?, // -34.76939
        @SerializedName("ath_date")
        val athDate: String?, // 2021-11-10T14:24:11.849Z
        @SerializedName("atl")
        val atl: Double?, // 67.81
        @SerializedName("atl_change_percentage")
        val atlChangePercentage: Double?, // 66319.39414
        @SerializedName("atl_date")
        val atlDate: String?, // 2013-07-06T00:00:00.000Z
        @SerializedName("circulating_supply")
        val circulatingSupply: Double?, // 120184188.645257
        @SerializedName("current_price")
        val currentPrice: Double?, // 2266.96
        @SerializedName("fully_diluted_valuation")
        val fullyDilutedValuation: Long?, // 945008291830
        @SerializedName("high_24h")
        val high24h: Double?, // 2276.76
        @SerializedName("id")
        val id: String?, // bitcoin
        @SerializedName("image")
        val image: String?, // https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400
        @SerializedName("last_updated")
        val lastUpdated: String?, // 2024-01-08T14:28:47.254Z
        @SerializedName("low_24h")
        val low24h: Double?, // 2170.31
        @SerializedName("market_cap")
        val marketCap: Long?, // 881688236238
        @SerializedName("market_cap_change_24h")
        val marketCapChange24h: Double?, // -734590772.1876068
        @SerializedName("market_cap_change_percentage_24h")
        val marketCapChangePercentage24h: Double?, // 1.46536
        @SerializedName("market_cap_rank")
        val marketCapRank: Int?, // 1
        @SerializedName("max_supply")
        val maxSupply: Double?, // 9253636.48469202
        @SerializedName("name")
        val name: String?, // Bitcoin
        @SerializedName("price_change_24h")
        val priceChange24h: Double?, // 635.65
        @SerializedName("price_change_percentage_24h")
        val priceChangePercentage24h: Double?, // 1.43257
        @SerializedName("roi")
        val roi: Roi?,
        @SerializedName("sparkline_in_7d")
        val sparklineIn7d: SparklineIn7d?,
        @SerializedName("symbol")
        val symbol: String?, // btc
        @SerializedName("total_supply")
        val totalSupply: Double?, // 120184188.645257
        @SerializedName("total_volume")
        val totalVolume: Long? // 25491516425
    ) {
        data class Roi(
            @SerializedName("currency")
            val currency: String?, // btc
            @SerializedName("percentage")
            val percentage: Double?, // 6633.587976981056
            @SerializedName("times")
            val times: Double? // 66.33587976981056
        )
    
        data class SparklineIn7d(
            @SerializedName("price")
            val price: List<Double?>?
        )
    }
}