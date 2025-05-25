/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.shared.ext

import io.github.nithi.kurrency.shared.reader.AssetReader
import java.io.IOException

/**
 * Reads given file from resources directory.
 *
 * @param fileName The file name
 *
 * @return content as [String]
 *
 * @throws IOException if file is not found
 */
@Throws(IOException::class)
fun asset(fileName: String) = AssetReader.getJsonDataFromAsset(fileName)
