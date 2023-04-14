package org.matrix.android.sdk.internal.network

import okhttp3.Request
import org.matrix.android.sdk.api.failure.GlobalError
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Tim van Lieshout on 13/04/2023
 */
object GlobalErrorHandlerMatrix {

    var listener: Listener? = null

    fun handleGlobalError(throwable: Throwable, request: Request?) {
        val globalError = throwableToGlobalError(throwable)
        Timber.e("Global error received: $globalError")
        listener?.onGlobalError(globalError, request?.url.toString())
    }

    private fun throwableToGlobalError(throwable: Throwable): GlobalError {
        return when (throwable) {
            is HttpException -> throwable.toGlobalError()
            else -> GlobalError.GenericError
        }
    }

    interface Listener {
        fun onGlobalError(globalError: GlobalError, url: String)
    }
}