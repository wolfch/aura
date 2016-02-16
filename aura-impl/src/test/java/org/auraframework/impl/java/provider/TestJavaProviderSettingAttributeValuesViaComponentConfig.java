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
package org.auraframework.impl.java.provider;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.auraframework.Aura;
import org.auraframework.annotations.Annotations.ServiceComponentProvider;
import org.auraframework.def.ComponentConfigProvider;
import org.auraframework.def.ComponentDef;
import org.auraframework.def.DefDescriptor.DefType;
import org.auraframework.instance.BaseComponent;
import org.auraframework.instance.Component;
import org.auraframework.instance.ComponentConfig;
import org.auraframework.service.InstanceService;
import org.auraframework.system.Annotations.Provider;
import org.auraframework.throwable.quickfix.QuickFixException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ServiceComponentProvider
@Provider
public class TestJavaProviderSettingAttributeValuesViaComponentConfig implements ComponentConfigProvider {
    @Inject
    private InstanceService instanceService;

    @Override
    public ComponentConfig provide() throws QuickFixException {
        ComponentConfig c = new ComponentConfig();

        c.setDescriptor(Aura.getDefinitionService().getDefDescriptor(
                "test:testJavaProviderSettingAttributeValuesViaComponentConfigHelper", ComponentDef.class));
        c.setAttributes(provideAttributes());

        return c;
    }

    private Map<String, Object> provideAttributes() throws QuickFixException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("a1", "a1Provider");
        attributes.put("a2", null);
        attributes.put("b1", "b1Provider");
        attributes.put("ar1", new String[] { "ar1Provider0", "ar1Provider1" });

        BaseComponent<?, ?> component = Aura.getContextService().getCurrentContext().getCurrentComponent();

        if (component.getAttributes().getValue("a3") != null) {
            attributes.put("b2", "b2Provider");
        }

        Map<String, Object> facetAttributes = Maps.newHashMap();
        facetAttributes.put("value", "aura" /*
                                             * new
                                             * PropertyReferenceImpl("v.value",
                                             * null)
                                             */);
        Component facet = (Component) instanceService.getInstance("ui:outputText", facetAttributes,
                DefType.COMPONENT);
        attributes.put("facet", Lists.newArrayList(facet));

        return attributes;
    }
}
