/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.verifier

import io.github.nithi.kurrency.BuildConfig.BASE_URL
import io.github.nithi.kurrency.util.ext.d
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * A [HostnameVerifier] implementation for internal network operation. Other network traffics will
 * be ignored.
 */
object RevolutHostnameVerifier : HostnameVerifier {

    private const val HOSTNAME_DELIMITER = "/"

    /**
     * Verifies if network traffic is only over [BASE_URL].
     *
     * @param hostname the host name
     * @param session SSLSession used on the connection to host
     *
     * @return true if the host name is acceptable
     */
    override fun verify(hostname: String?, session: SSLSession?): Boolean {
        val expectedHostname = BASE_URL
            .substringBeforeLast(HOSTNAME_DELIMITER)
            .substringAfterLast(HOSTNAME_DELIMITER)

        d { "Hostname is $expectedHostname}" }

        return hostname.toString().contains(expectedHostname)
    }
}
