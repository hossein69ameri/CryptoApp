package ameri.hossein.cryptoapp.utils.network

import ameri.hossein.cryptoapp.data.model.ResponseError429
import ameri.hossein.cryptoapp.data.model.ResponseErrors
import ameri.hossein.cryptoapp.utils.base.BaseState
import com.google.gson.Gson
import retrofit2.Response

class ErrorResponse<T>(private val response: Response<T>) {

    open fun generateResponse(): BaseState {
        return when {
            response.code() == 404 -> {
                var errorMessage = ""
                if (response.errorBody() != null) {
                    val errorResponse = Gson().fromJson(response.errorBody()?.charStream(), ResponseErrors::class.java)
                    errorMessage = errorResponse.error.toString()
                }
                BaseState.Error(errorMessage)
            }

            response.code() == 429 -> {
                var errorMessage = ""
                if (response.errorBody() != null) {
                    val errorResponse = Gson().fromJson(response.errorBody()?.charStream(), ResponseError429::class.java)
                    errorMessage = errorResponse.status?.errorMessage.toString()
                }
                BaseState.Error(errorMessage)
            }

            response.code() == 500 -> BaseState.Error("Try again!")
            else -> BaseState.Error(response.message())
        }
    }
}