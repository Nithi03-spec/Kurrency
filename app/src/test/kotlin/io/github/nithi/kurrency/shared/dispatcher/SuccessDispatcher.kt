/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.shared.dispatcher

import io.github.nithi.kurrency.shared.ENDPOINT_PREFIX
import io.github.nithi.kurrency.shared.RESPONSE_DIR_PREFIX
import io.github.nithi.kurrency.shared.ext.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import javax.net.ssl.HttpsURLConnection.HTTP_NOT_FOUND
import javax.net.ssl.HttpsURLConnection.HTTP_OK

/**
 * A custom [MockWebServer] [Dispatcher] implementation for success case. This will return
 * [HTTP_OK] if path contains the endpoint and file name is valid.
 */
class SuccessDispatcher(private val fileName: String) : Dispatcher() {

    /**
     * @return [MockResponse] with [HTTP_OK] if path contains the endpoint and file name is valid
     * otherwise [HTTP_NOT_FOUND]
     */
    override fun dispatch(request: RecordedRequest): MockResponse {
        val path = request.path

        return if (path.toString().contains(ENDPOINT_PREFIX)) {
            val response = asset("$RESPONSE_DIR_PREFIX/$fileName")

            if (response.isNullOrEmpty()) MockResponse().setResponseCode(HTTP_NOT_FOUND)

            // response is not null in any case
            MockResponse().setResponseCode(HTTP_OK).setBody(response!!)
        } else {
            MockResponse().setResponseCode(HTTP_NOT_FOUND)
        }
    }
}
