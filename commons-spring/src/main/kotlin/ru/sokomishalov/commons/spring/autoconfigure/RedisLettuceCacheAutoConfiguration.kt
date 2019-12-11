/**
 * Copyright 2019-2019 the original author or authors.
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
@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package ru.sokomishalov.commons.spring.autoconfigure

import com.fasterxml.jackson.databind.ObjectMapper
import io.lettuce.core.RedisClient
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered.HIGHEST_PRECEDENCE
import ru.sokomishalov.commons.cache.CacheService
import ru.sokomishalov.commons.cache.redis.RedisCacheService
import ru.sokomishalov.commons.core.serialization.OBJECT_MAPPER

/**
 * @author sokomishalov
 */
@Configuration
@ConditionalOnClass(CacheService::class, RedisClient::class)
@AutoConfigureOrder(HIGHEST_PRECEDENCE)
class RedisLettuceCacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CacheService::class)
    @ConditionalOnBean(RedisClient::class)
    fun redisCacheService(
            client: RedisClient,
            mapper: ObjectProvider<ObjectMapper>
    ): CacheService {
        return RedisCacheService(client = client, mapper = mapper.getIfAvailable { OBJECT_MAPPER })
    }
}