/*
 *  Copyright (c) 2020, 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.spi.types.domain.transfer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.eclipse.dataspaceconnector.spi.types.domain.Polymorphic;

import java.util.Objects;

/**
 * A resource to be provisioned to support a data transfer.
 */
@JsonTypeName("dataspaceconnector:resourcedefinition")
@JsonDeserialize(builder = ResourceDefinition.Builder.class)
public abstract class ResourceDefinition implements Polymorphic {
    protected String id;
    protected String transferProcessId;

    public String getId() {
        return id;
    }

    public String getTransferProcessId() {
        return transferProcessId;
    }

    void setTransferProcessId(String transferProcessId) {
        this.transferProcessId = transferProcessId;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder<RD extends ResourceDefinition, B extends Builder<RD, B>> {
        protected final RD resourceDefinition;

        protected Builder(RD definition) {
            resourceDefinition = definition;
        }

        public B id(String id) {
            resourceDefinition.id = id;
            return (B) this;
        }

        public B transferProcessId(String id) {
            resourceDefinition.transferProcessId = id;
            return (B) this;
        }

        public RD build() {
            verify();
            return resourceDefinition;
        }

        protected void verify() {
            Objects.requireNonNull(resourceDefinition.id, "id");
        }
    }
}
