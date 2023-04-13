package org.matrix.android.sdk.internal.network

import org.matrix.android.sdk.api.failure.GlobalError
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Tim van Lieshout on 13/04/2023
 */
class GlobalErrorHandlerMatrix @Inject constructor() : GlobalErrorReceiver {

    var listener: Listener? = null

    override fun handleGlobalError(globalError: GlobalError) {
        Timber.e("Global error received: $globalError")
        listener?.onGlobalError(globalError)
    }

    interface Listener {
        fun onGlobalError(globalError: GlobalError)
    }
}