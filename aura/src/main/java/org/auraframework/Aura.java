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
package org.auraframework;

import javax.inject.Inject;

import org.auraframework.adapter.ConfigAdapter;
import org.auraframework.adapter.LocalizationAdapter;
import org.auraframework.adapter.ServletUtilAdapter;
import org.auraframework.adapter.StyleAdapter;
import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.def.Definition;
import org.auraframework.instance.Instance;
import org.auraframework.service.ContextService;
import org.auraframework.service.ConverterService;
import org.auraframework.service.DefinitionService;
import org.auraframework.service.InstanceService;
import org.auraframework.service.IntegrationService;
import org.auraframework.service.LoggingService;
import org.auraframework.system.AuraContext;
import org.auraframework.test.TestContextAdapter;
import org.auraframework.util.adapter.SourceControlAdapter;

/**
 * Entry point for accessing Aura services
 */
@ServiceComponent
public class Aura implements AuraDeprecated {
    private static IntegrationService integrationService;
    private static StyleAdapter styleAdapter;
    private static ConfigAdapter configAdapter;
    private static LocalizationAdapter localizationAdapter;
    private static LoggingService loggingService;
    private static DefinitionService definitionService;
    private static ContextService contextService;
    private static InstanceService instanceService;
    private static ServletUtilAdapter servletUtilAdapter;
    private static SourceControlAdapter sourceControlAdapter;
    private static ConverterService converterService;
    private static TestContextAdapter testContextAdapter;

    @Inject
    public void setContextService(ContextService service) {
        contextService = service;
    }

    @Inject
    public void setConfigAdapter(ConfigAdapter adapter) {
        configAdapter = adapter;
    }

    @Inject
    public void setLocalizationAdapter(LocalizationAdapter adapter) {
        localizationAdapter = adapter;
    }

    @Inject
    public void setLoggingService(LoggingService service) {
        loggingService = service;
    }

    @Inject
    public void setDefinitionService(DefinitionService service) {
        definitionService = service;
    }

    @Inject
    public void setInstanceService(InstanceService service) {
        instanceService = service;
    }

    @Inject
    public void setStyleAdapter(StyleAdapter adapter) {
        styleAdapter = adapter;
    }

    @Inject
    public void setServletUtilAdapter(ServletUtilAdapter adapter) {
        servletUtilAdapter = adapter;
    }

    @Inject
    public void setSourceControlAdapter(SourceControlAdapter adapter) {
        sourceControlAdapter = adapter;
    }

    @Inject
    public void setIntegrationService(IntegrationService service) {
        integrationService = service;
    }

    @Inject
    public void setConverterService(ConverterService service) {
        converterService = service;
    }

    @Inject
    public void setTestContextAdapter(TestContextAdapter adapter) {
        testContextAdapter = adapter;
    }

    /**
     * Get the Context Service: for creating or interacting with a {@link AuraContext} A AuraContext must be started
     * before working using any other service.
     */
    public static ContextService getContextService() {
        return contextService;
    }

    /**
     * Get the Definition Service: for loading, finding or interacting with a {@link Definition}
     */
    public static DefinitionService getDefinitionService() {
        return definitionService;
    }

    /**
     * Get the Logging Service: Provides Aura with a top-level Logging handler from the host environments
     */
    public static LoggingService getLoggingService() {
        return loggingService;
    }

    /**
     * Get the Instance Service: for constructing an {@link Instance} of a {@link Definition}
     */
    public static InstanceService getInstanceService() {
        return instanceService;
    }

    /**
     * Get the Config Adapter: Provides Aura with configuration from the host environment
     */
    public static ConfigAdapter getConfigAdapter() {
        return configAdapter;
    }

    /**
     * Get the Localization Adapter: Provides Aura with Localization configuration from the host environments
     */
    public static LocalizationAdapter getLocalizationAdapter() {
        return localizationAdapter;
    }

    /**
     * Get the Source Control Adapter : Allows interaction with the source control system.
     */
    public static SourceControlAdapter getSourceControlAdapter() {
        return sourceControlAdapter;
    }

    /**
     * Get the Style Adapter: Used to provide CSS/Style specific functionality.
     */
    public static StyleAdapter getStyleAdapter() {
        return styleAdapter;
    }

    /**
     * Get the Servlet Util Adapter: Used to provide overrides for servlet functionality.
     */
    public static ServletUtilAdapter getServletUtilAdapter() {
        return servletUtilAdapter;
    }

    /**
     * Gets the Integration Service: Service that makes integrating into other containers easy.
     */
    public static IntegrationService getIntegrationService() {
        return integrationService;
    }

    public static ConverterService getConverterService() {
        return converterService;
    }

    public static TestContextAdapter getTestContextAdapter() {
        return testContextAdapter;
    }
}
