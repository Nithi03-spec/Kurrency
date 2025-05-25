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
package io.github.nithi.kurrency.data.model.view

import androidx.annotation.DrawableRes

/**
 * A data class that includes each currency for view layer
 *
 * @property id The id for each item
 * @property abbreviation The shortened currency name
 * @property longName The long style of [abbreviation]
 * @property amount The currency amount
 * @property icon The currency icon that represents its country's flag
 */
data class RateViewItem(
    val id: Int,
    val abbreviation: String,
    val longName: String,
    val amount: Float,
    @DrawableRes
    val icon: Int
)
