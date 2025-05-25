/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.shared.dispatcher

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.SocketPolicy.NO_RESPONSE
import java.util.concurrent.TimeUnit

/**
 * A custom [MockWebServer] [Dispatcher] implementation for timeout case. This will delay response
 * remaining faithful to [OkHttpClient] settings for the test environment.
 */
object TimeoutDispatcher : Dispatcher() {

    private const val BYTES_PER_PERIOD = 1024L
    private const val PERIOD = 2L

    /**
     * @return [MockResponse] with throttled body and [NO_RESPONSE] policy.
     */
    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse()
            .setSocketPolicy(NO_RESPONSE)
            .throttleBody(BYTES_PER_PERIOD, PERIOD, TimeUnit.SECONDS)
    }
}
