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
package com.forgerock.openbanking.analytics.webclient.services;

import brave.Tracer;
import com.forgerock.openbanking.analytics.model.entries.TppEntry;
import com.forgerock.openbanking.analytics.services.TppEntriesKPIService;
import com.forgerock.openbanking.analytics.webclient.configuration.MetricsConfiguration;
import com.forgerock.openbanking.analytics.utils.MetricUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Slf4j
@Service
public class WebClientTppEntriesKPIService implements TppEntriesKPIService {

    private final WebClient webClient;
    private final MetricsConfiguration metricsConfiguration;
    private final Tracer tracer;

    public WebClientTppEntriesKPIService(WebClient webClient, MetricsConfiguration metricsConfiguration, Tracer tracer) {
        this.webClient = webClient;
        this.metricsConfiguration = metricsConfiguration;
        this.tracer = tracer;
    }

    public void pushTppEntry(TppEntry entry) {
        if (!MetricUtils.isRequestEnabledForAnalytics(tracer)) {
            log.debug("Request excluded for analytics");
            return;
        }
        log.debug("Push tpp entry={}", entry);
        try {
            webClient
                    .post()
                    .uri(metricsConfiguration.rootEndpoint + "/api/kpi/tpps/entries")
                    .body(BodyInserters.fromObject(Arrays.asList(entry)))
                    .retrieve().bodyToMono(String.class)
                    .log()
                    .subscribe(response -> log.debug("Response from metrics: {}", response));
        } catch (Exception e) {
            log.warn("Couldn't send Tpp entry", entry, e);
        }
    }
}
