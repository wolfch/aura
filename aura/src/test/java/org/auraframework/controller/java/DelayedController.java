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
package org.auraframework.controller.java;

import com.google.common.collect.ImmutableMap;
import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.def.ComponentDef;
import org.auraframework.ds.servicecomponent.Controller;
import org.auraframework.instance.Component;
import org.auraframework.service.InstanceService;
import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.system.Annotations.Key;

import javax.inject.Inject;
import java.util.Map;

@ServiceComponent
public class DelayedController implements Controller {

    @Inject
    private InstanceService instanceService;

    @AuraEnabled
    public Object getComponents(@Key("token") String token) throws Exception {
        Component cmp = instanceService.getInstance("auratest:text", ComponentDef.class);
        Object val = token;
        Map<String, Object> atts = ImmutableMap.of("value", val);
        cmp.getAttributes().set(atts);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        return new Component[] { cmp };
    }
}
