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
package com.forgerock.openbanking.analytics.configuration;

import com.forgerock.openbanking.analytics.model.entries.EndpointUsageEntry;
import com.forgerock.openbanking.analytics.model.entries.JwtsGenerationEntry;
import com.forgerock.openbanking.analytics.model.entries.JwtsValidationEntry;
import com.forgerock.openbanking.analytics.model.entries.TokenUsage;
import com.forgerock.openbanking.analytics.services.CallBackCountersKPIService;
import com.forgerock.openbanking.analytics.services.ConsentMetricService;
import com.forgerock.openbanking.analytics.services.DirectoryCountersKPIService;
import com.forgerock.openbanking.analytics.services.MetricService;
import com.forgerock.openbanking.analytics.services.PsuCounterEntryKPIService;
import com.forgerock.openbanking.analytics.services.SessionCountersKPIService;
import com.forgerock.openbanking.analytics.services.TokenUsageService;
import com.forgerock.openbanking.analytics.services.TppEntriesKPIService;
import com.forgerock.openbanking.config.ApplicationConfiguration;
import com.forgerock.openbanking.model.oidc.AccessTokenResponse;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MetricsConfiguration implements ApplicationConfiguration {

    @ConditionalOnMissingBean(CallBackCountersKPIService.class)
    @Bean
    CallBackCountersKPIService callBackCountersKPIService() {
        return callBackCounterEntries -> {};
    }

    @ConditionalOnMissingBean(ConsentMetricService.class)
    @Bean
    ConsentMetricService consentMetricService() {
        return consentMetricService -> {};
    }

    @ConditionalOnMissingBean(DirectoryCountersKPIService.class)
    @Bean
    DirectoryCountersKPIService directoryCountersKPIService() {
        return directoryCountersKPIService -> {};
    }

    @ConditionalOnMissingBean(MetricService.class)
    @Bean
    MetricService metricService() {
        return new MetricService() {
            @Override
            public void addEndpointUsageEntry(EndpointUsageEntry endpointUsageEntry) {

            }

            @Override
            public void addJwtsGenerationEntry(JwtsGenerationEntry jwtsGenerationEntry) {

            }

            @Override
            public void addJwtsValidationEntry(JwtsValidationEntry jwtsValidationEntry) {

            }

            @Override
            public void pushMetrics() {

            }

            @Override
            public List<EndpointUsageEntry> getEndpointUsageMetric() {
                return null;
            }

            @Override
            public List<JwtsGenerationEntry> getJwtsGenerationMetric() {
                return null;
            }

            @Override
            public List<JwtsValidationEntry> getJwtsValidationMetric() {
                return null;
            }
        };
    }

    @ConditionalOnMissingBean(PsuCounterEntryKPIService.class)
    @Bean
    PsuCounterEntryKPIService psuCounterEntryKPIService() {
        return psuCounterEntryKPIService -> {};
    }

    @ConditionalOnMissingBean(SessionCountersKPIService.class)
    @Bean
    SessionCountersKPIService sessionCountersKPIService() {
        return sessionCountersKPIService -> {};
    }


    @ConditionalOnMissingBean(TokenUsageService.class)
    @Bean
    TokenUsageService tokenUsageService() {
        return  new TokenUsageService() {
            @Override
            public void incrementTokenUsage(TokenUsage... tokenUsage) {

            }

            @Override
            public void incrementTokenUsage(AccessTokenResponse accessTokenResponse) {

            }

            @Override
            public void increment(Collection<TokenUsage> tokenUsages) {

            }
        };
    }


    @ConditionalOnMissingBean(TppEntriesKPIService.class)
    @Bean
    TppEntriesKPIService tppEntriesKPIService() {
        return tppEntriesKPIService -> {};
    }

    @Override
    public String getIssuerID() {
        return "metrics";
    }

    @Override
    public JWKSet getJwkSet() {
        return null;
    }
}
