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
package com.forgerock.openbanking.analytics.model.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.forgerock.openbanking.model.SoftwareStatementRole;
import com.forgerock.openbanking.serialiser.IsoDateTimeDeserializer;
import com.forgerock.openbanking.serialiser.IsoDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class TppEntry {
    @Id
    @Indexed
    private String oidcClientId;
    @Indexed
    public String name;
    private String logoUri;
    private Set<SoftwareStatementRole> types = new HashSet<>();

    @Indexed
    public String directoryId;
    public String softwareId;
    @Indexed
    public String organisationId;
    public String organisationName;
    @JsonDeserialize(using = IsoDateTimeDeserializer.class)
    @JsonSerialize(using = IsoDateTimeSerializer.class)
    @Indexed
    private DateTime created;
    @JsonDeserialize(using = IsoDateTimeDeserializer.class)
    @JsonSerialize(using = IsoDateTimeSerializer.class)
    @Indexed
    private DateTime deleted;
}
