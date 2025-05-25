/*
 * Copyright (C) 2020. Nuh Koca. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.nithi.kurrency.util.formatter

import io.github.nithi.kurrency.binding.di.BindingScope
import java.text.ParseException
import javax.inject.Inject

/**
 * The [Formatter] implementation.
 */
@BindingScope
class CurrencyFormatter @Inject constructor() : Formatter {

    override fun formatText(text: String): String {
        val number = parseText(text)
        return formatNumber(number)
    }

    override fun formatNumber(number: Number?): String {
        return try {
            formatter.format(number)
        } catch (e: IllegalArgumentException) {
            ""
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun parseText(text: String): Number {
        return try {
            formatter.parse(text)
        } catch (e: ParseException) {
            0.0f
        }
    }
}
