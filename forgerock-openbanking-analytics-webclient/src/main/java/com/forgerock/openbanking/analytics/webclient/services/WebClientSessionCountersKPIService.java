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
import com.forgerock.openbanking.analytics.model.entries.SessionCounterType;
import com.forgerock.openbanking.analytics.services.SessionCountersKPIService;
import com.forgerock.openbanking.analytics.webclient.configuration.MetricsConfiguration;
import com.forgerock.openbanking.analytics.utils.MetricUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Slf4j
@Service
public class WebClientSessionCountersKPIService implements SessionCountersKPIService {

    private final WebClient webClient;
    private final MetricsConfiguration metricsConfiguration;
    private final Tracer tracer;

    public WebClientSessionCountersKPIService(WebClient webClient, MetricsConfiguration metricsConfiguration, Tracer tracer) {
        this.webClient = webClient;
        this.metricsConfiguration = metricsConfiguration;
        this.tracer = tracer;
    }

    public void incrementSessionCounter(SessionCounterType... sessionCounterType) {
        if (!MetricUtils.isRequestEnabledForAnalytics(tracer)) {
            log.debug("Request excluded for analytics");
            return;
        }
        log.debug("Incrementing session counter types={}", Arrays.toString(sessionCounterType));
        try {
            webClient
                    .post()
                    .uri(metricsConfiguration.rootEndpoint + "/api/kpi/session/")
                    .body(BodyInserters.fromObject(sessionCounterType))
                    .retrieve().bodyToMono(String.class)
                    .log()
                    .subscribe(response -> log.debug("Response from metrics: {}", response));
        } catch (Exception e) {
            log.warn("Couldn't send session counters {} KPI to metrics", sessionCounterType, e);
        }
    }
}
