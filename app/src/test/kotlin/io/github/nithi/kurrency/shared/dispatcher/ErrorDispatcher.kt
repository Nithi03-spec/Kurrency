/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.shared.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import javax.net.ssl.HttpsURLConnection.HTTP_NOT_FOUND

/**
 * A custom [MockWebServer] [Dispatcher] implementation for error case. This will only return
 * [HTTP_NOT_FOUND] and will cause a crash. Therefore calls should be wrapper with
 * try-catch or so on.
 */
object ErrorDispatcher : Dispatcher() {

    /**
     * @return [MockResponse] with [HTTP_NOT_FOUND] code.
     */
    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(HTTP_NOT_FOUND)
    }
}
