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
package org.auraframework.impl.instance;

import java.util.Map;

import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.def.JavaModelDef;
import org.auraframework.def.ModelDef;
import org.auraframework.ds.servicecomponent.ModelFactory;
import org.auraframework.ds.servicecomponent.ModelInitializationException;
import org.auraframework.impl.java.model.JavaModel;
import org.auraframework.impl.java.model.JavaModelDefImpl;
import org.auraframework.instance.InstanceBuilder;
import org.auraframework.instance.Model;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@ServiceComponent
public class ModelInstanceBuilder implements InstanceBuilder<Model, ModelDef>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Class<?> getDefinitionClass() {
        return JavaModelDefImpl.class;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Model getInstance(ModelDef modelDef, Map<String, Object> attributes) {
        Class<?> clazz = ((JavaModelDef) modelDef).getJavaType();
        String modelFactoryClassName = clazz.getName() + "Factory";
        Object bean = null;
        try {
            bean = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            try {
                @SuppressWarnings("unchecked")
                Class<? extends ModelFactory> modelFactoryClass = (Class<? extends ModelFactory>) Class
                        .forName(modelFactoryClassName);
                Object instance = applicationContext.getBean(modelFactoryClass);
                if (instance instanceof ModelFactory) {
                    bean = ((ModelFactory) instance).modelInstance();
                }
                // Factory class exists but factory bean could not be found
            } catch (ClassNotFoundException e1) {
                // Factory class could not be found
            } catch (ModelInitializationException x) {
                // The factory was apparently found but failed to create model instance
            }
        }

        return new JavaModel((JavaModelDefImpl) modelDef, bean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
