/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.auraframework.impl;

import org.auraframework.adapter.ConfigAdapter;
import org.auraframework.adapter.ExceptionAdapter;
import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.impl.integration.IntegrationImpl;
import org.auraframework.integration.Integration;
import org.auraframework.service.ContextService;
import org.auraframework.service.DefinitionService;
import org.auraframework.service.IntegrationService;
import org.auraframework.service.LoggingService;
import org.auraframework.service.SerializationService;
import org.auraframework.system.AuraContext.Mode;
import org.auraframework.throwable.quickfix.QuickFixException;

import javax.inject.Inject;

@ServiceComponent
public class IntegrationServiceImpl implements IntegrationService {
    private static final long serialVersionUID = -2650728458106333787L;

    // Needed to run action
    private LoggingService loggingService;
    private ExceptionAdapter exceptionAdapter;
    private DefinitionService definitionService;
    private SerializationService serializationService;
    private ContextService contextService;
    private ConfigAdapter configAdapter;
 
    @Override
    public Integration createIntegration(String contextPath, Mode mode, boolean initializeAura, String userAgent,
                                         String application, Object dummy) throws QuickFixException {
        return new IntegrationImpl(contextPath, mode, initializeAura, userAgent, application, loggingService, exceptionAdapter, definitionService, serializationService, contextService, configAdapter);
    }

    @Inject
    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Inject
    public void setExceptionAdapter(ExceptionAdapter exceptionAdapter) {
        this.exceptionAdapter = exceptionAdapter;
    }

    @Inject
    public void setDefinitionService(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @Inject
    public void setSerializationService(SerializationService serializationService) {
        this.serializationService = serializationService;
    }

    @Inject
    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }

    @Inject
    public void setConfigAdapter(ConfigAdapter configAdapter) {
        this.configAdapter = configAdapter;
    }
}
