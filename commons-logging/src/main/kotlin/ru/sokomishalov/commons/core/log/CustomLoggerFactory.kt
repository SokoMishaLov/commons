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
@file:Suppress("MemberVisibilityCanBePrivate")

package ru.sokomishalov.commons.core.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.sokomishalov.commons.core.reflection.unwrapCompanionClass
import java.util.concurrent.ConcurrentHashMap

/**
 * @author sokomishalov
 */
object CustomLoggerFactory {

    private val loggersMap: MutableMap<String, Logger> = ConcurrentHashMap()

    fun <T : Any> getLogger(clazz: Class<T>): Logger {
        val nonCompanionClazz = clazz.unwrapCompanionClass()
        return getLogger(nonCompanionClazz.name)
    }

    fun getLogger(className: String): Logger {
        val logger = loggersMap[className]
        return when {
            logger != null -> logger
            else -> LoggerFactory.getLogger(className).also {
                loggersMap[className] = it
            }
        }
    }
}