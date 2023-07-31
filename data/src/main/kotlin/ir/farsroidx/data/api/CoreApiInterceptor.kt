package ir.farsroidx.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

abstract class CoreApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking(Dispatchers.IO) {
            try {
                suspendIntercept(chain)
            } catch (throwable: Throwable) {
                onException(throwable)
            }
        }
    }

    abstract suspend fun suspendIntercept(chain: Interceptor.Chain): Response

    open suspend fun onException(throwable: Throwable): Response {
        throw IOException(throwable)
    }
}