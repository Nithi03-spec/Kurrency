/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.shared.reader

import java.io.IOException

/**
 * A helper class to read specific file from resources directory.
 */
object AssetReader {

    /**
     * Reads and returns content as [String]
     *
     * @param fileName the file name
     *
     * @return [String]
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    @JvmStatic
    fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String?
        try {
            jsonString = javaClass.classLoader?.getResourceAsStream(fileName)
                ?.bufferedReader()
                ?.use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}
