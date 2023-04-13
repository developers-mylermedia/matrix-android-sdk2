package org.matrix.android.sdk.internal.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.matrix.android.sdk.api.failure.GlobalError
import org.matrix.android.sdk.internal.auth.SessionParamsStore
import org.matrix.android.sdk.internal.di.SessionId
import org.matrix.android.sdk.internal.session.SessionScope
import org.matrix.android.sdk.internal.task.TaskExecutor
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Tim van Lieshout on 13/04/2023
 */
internal class GlobalErrorHandlerAuth @Inject constructor() : GlobalErrorReceiver {

    var listener: Listener? = null

    override fun handleGlobalError(globalError: GlobalError) {
        Timber.e("Global error received: $globalError")
        listener?.onGlobalError(globalError)
    }

    internal interface Listener {
        fun onGlobalError(globalError: GlobalError)
    }
}