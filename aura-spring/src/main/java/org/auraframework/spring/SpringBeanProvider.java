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
package org.auraframework.spring;

import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.org.auraframework.di.ImplementationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Spring BeanProvider gives access to Spring beans to classes that are not beans (autowired)
 */
@ServiceComponent
public class SpringBeanProvider implements ImplementationProvider {

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public <T> T getImplementation(Class<T> clazz) {
        return this.applicationContext.getBean(clazz);
    }

    @Override
    public <T> Map<String, T> getImplementations(Class<T> clazz) {
        return this.applicationContext.getBeansOfType(clazz);
    }
}
