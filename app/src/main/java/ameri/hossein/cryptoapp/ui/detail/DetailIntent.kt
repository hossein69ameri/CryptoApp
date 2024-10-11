package ameri.hossein.cryptoapp.ui.detail

sealed class DetailIntent {
    data class CallCoinDetail(val id: String) : DetailIntent()
}