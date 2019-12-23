/**
 * Copyright 2019 ForgeRock AS.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.forgerock.openbanking.analytics.webclient.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Configuration
public class MetricsConfiguration {

    @Value("${metrics.endpoints.root}")
    public String rootEndpoint;

    @Value("${metrics.endpoints.endpoint-usage.add-entries}")
    public String endpointUsageAddEntries;

    @Value("${metrics.endpoints.jwts-generation.add-entries}")
    public String jwtsGenerationAddEntries;

    @Value("${metrics.endpoints.jwts-validation.add-entries}")
    public String jwtsValidationAddEntries;

    @ConditionalOnMissingBean(WebClient.class)
    @Bean
    WebClient defaultWebClient() {
        return WebClient.create();
    }

}
