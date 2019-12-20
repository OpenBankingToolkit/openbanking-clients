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

import com.forgerock.openbanking.analytics.model.entries.ConsentStatusEntry;
import com.forgerock.openbanking.analytics.model.entries.DirectoryCounterType;
import com.forgerock.openbanking.analytics.model.entries.EndpointUsageEntry;
import com.forgerock.openbanking.analytics.model.entries.JwtsGenerationEntry;
import com.forgerock.openbanking.analytics.model.entries.JwtsValidationEntry;
import com.forgerock.openbanking.analytics.model.entries.PsuCounterEntry;
import com.forgerock.openbanking.analytics.model.entries.SessionCounterType;
import com.forgerock.openbanking.analytics.model.entries.TokenUsage;
import com.forgerock.openbanking.analytics.model.entries.TppEntry;
import com.forgerock.openbanking.analytics.model.entries.callback.CallBackCounterEntry;
import com.forgerock.openbanking.analytics.services.CallBackCountersKPIService;
import com.forgerock.openbanking.analytics.services.ConsentMetricService;
import com.forgerock.openbanking.analytics.services.DirectoryCountersKPIService;
import com.forgerock.openbanking.analytics.services.MetricService;
import com.forgerock.openbanking.analytics.services.PsuCounterEntryKPIService;
import com.forgerock.openbanking.analytics.services.SessionCountersKPIService;
import com.forgerock.openbanking.analytics.services.TokenUsageService;
import com.forgerock.openbanking.analytics.services.TppEntriesKPIService;
import com.forgerock.openbanking.model.oidc.AccessTokenResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;

@Configuration
public class NoOpConfiguration {

    @ConditionalOnMissingBean(CallBackCountersKPIService.class)
    @Bean
    CallBackCountersKPIService noOpCallBackCountersKPIService() {
        return new NoOpCallBackCountersKPIService();
    }

    @ConditionalOnMissingBean(ConsentMetricService.class)
    @Bean
    ConsentMetricService noOpConsentMetricService() {
        return new NoOpConsentMetricService();
    }

    @ConditionalOnMissingBean(DirectoryCountersKPIService.class)
    @Bean
    DirectoryCountersKPIService noOpDirectoryCountersKPIService() {
        return new NoOpDirectoryCountersKPIService();
    }

    @ConditionalOnMissingBean(MetricService.class)
    @Bean
    MetricService noOpMetricService() {
        return new NoOpMetricService();
    }

    @ConditionalOnMissingBean(PsuCounterEntryKPIService.class)
    @Bean
    PsuCounterEntryKPIService noOpPsuCounterEntryKPIService() {
        return new NoOpPsuCounterEntryKPIService();
    }

    @ConditionalOnMissingBean(SessionCountersKPIService.class)
    @Bean
    SessionCountersKPIService noOpSessionCountersKPIService() {
        return new NoOpSessionCountersKPIService();
    }


    @ConditionalOnMissingBean(TokenUsageService.class)
    @Bean
    TokenUsageService noOpTokenUsageService() {
        return new NoOpTokenUsageService();
    }


    @ConditionalOnMissingBean(TppEntriesKPIService.class)
    @Bean
    TppEntriesKPIService noOpTppEntriesKPIService() {
        return new NoOpTppEntriesKPIService();
    }

    static class NoOpSessionCountersKPIService implements SessionCountersKPIService {

        @Override
        public void incrementSessionCounter(SessionCounterType... sessionCounterType) {

        }
    }

    static class NoOpTokenUsageService implements TokenUsageService {

        @Override
        public void incrementTokenUsage(TokenUsage... tokenUsage) {

        }

        @Override
        public void incrementTokenUsage(AccessTokenResponse accessTokenResponse) {

        }

        @Override
        public void increment(Collection<TokenUsage> tokenUsages) {

        }
    }

    static class NoOpTppEntriesKPIService implements TppEntriesKPIService {

        @Override
        public void pushTppEntry(TppEntry entry) {

        }
    }

    static class NoOpMetricService implements MetricService {

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
    }

    static class NoOpPsuCounterEntryKPIService implements PsuCounterEntryKPIService {
        @Override
        public void pushPsuCounterEntry(PsuCounterEntry entry) {

        }
    }

    static class NoOpDirectoryCountersKPIService implements DirectoryCountersKPIService {
        @Override
        public void incrementTokenUsage(DirectoryCounterType... directoryCounterType) {

        }
    }

    static class NoOpConsentMetricService implements ConsentMetricService {
        @Override
        public void sendConsentActivity(ConsentStatusEntry consentStatusEntry) {

        }
    }

    static class NoOpCallBackCountersKPIService implements CallBackCountersKPIService {
        @Override
        public void sendCallBackEntries(List<CallBackCounterEntry> callBackCounterEntries) {

        }
    }
}
