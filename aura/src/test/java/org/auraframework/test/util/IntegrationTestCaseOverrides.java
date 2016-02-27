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
package org.auraframework.test.util;

import org.auraframework.test.source.StringSourceExternalLoader;
import org.auraframework.test.source.StringSourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Overrides for beans that will not work outside of the servlets' ApplicationContext.
 */
public class IntegrationTestCaseOverrides {
    @Primary
    @Bean
    public StringSourceLoader remoteStringSourceLoader() {
        return new StringSourceExternalLoader();
    }
}
