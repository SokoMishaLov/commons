/**
 * Copyright (c) 2019-present Mikhael Sokolov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("unused", "NOTHING_TO_INLINE")

package ru.sokomishalov.commons.core.collections

import kotlin.contracts.contract


/**
 * @author sokomishalov
 */

infix fun <K, V> Map<K, V>.containsEntryFrom(other: Map<K, V>): Boolean = entries.intersect(other.entries).isNullOrEmpty().not()

inline fun <K, V> Map<K, V>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }

    return (this == null || this.isEmpty()).not()
}