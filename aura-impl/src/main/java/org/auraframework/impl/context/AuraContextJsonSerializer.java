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
package org.auraframework.impl.context;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.auraframework.adapter.ConfigAdapter;
import org.auraframework.def.BaseComponentDef;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.Definition;
import org.auraframework.instance.GlobalValueProvider;
import org.auraframework.system.AuraContext;
import org.auraframework.test.TestContext;
import org.auraframework.test.TestContextAdapter;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.auraframework.util.json.Json;
import org.auraframework.util.json.JsonSerializers.NoneSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * AuraContext JSON Serializer
 */
public class AuraContextJsonSerializer extends NoneSerializer<AuraContext> {
    public static final String DELETED = "deleted";

    private final TestContextAdapter testContextAdapter;
    private final ConfigAdapter configAdapter;

    public AuraContextJsonSerializer(ConfigAdapter configAdapter, TestContextAdapter testContextAdapter) {
        this.configAdapter = configAdapter;
        this.testContextAdapter = testContextAdapter;
    }

    private void writeDefs(Json json, String name, List<Definition> writable) throws IOException {
        if (writable.size() > 0) {
            Collections.sort(writable, (d1, d2) -> d1.getDescriptor().compareTo(d2.getDescriptor()));
            json.writeMapEntry(name, writable);
        }
    }

    @Override
    public void serialize(Json json, AuraContext ctx) throws IOException {
        json.writeMapBegin();
        json.writeMapEntry("mode", ctx.getMode());

        DefDescriptor<? extends BaseComponentDef> appDesc = ctx.getApplicationDescriptor();
        if (appDesc != null) {
            if (appDesc.getDefType().equals(DefDescriptor.DefType.APPLICATION)) {
                json.writeMapEntry("app", String.format("%s:%s", appDesc.getNamespace(), appDesc.getName()));
            } else {
                json.writeMapEntry("cmp", String.format("%s:%s", appDesc.getNamespace(), appDesc.getName()));
            }
        }

        String contextPath = ctx.getContextPath();
        if (!contextPath.isEmpty()) {
            // serialize servlet context path for html component to prepend for client created components
            json.writeMapEntry("contextPath", contextPath);
        }

        if (ctx.getRequestedLocales() != null) {
            List<String> locales = new ArrayList<>();
            for (Locale locale : ctx.getRequestedLocales()) {
                locales.add(locale.toString());
            }
            json.writeMapEntry("requestedLocales", locales);
        }
        Map<String, String> loadedStrings = Maps.newHashMap();
        Map<DefDescriptor<?>, String> clientLoaded = Maps.newHashMap();
        clientLoaded.putAll(ctx.getClientLoaded());
        for (Map.Entry<DefDescriptor<?>, String> entry : ctx.getLoaded().entrySet()) {
            loadedStrings.put(String.format("%s@%s", entry.getKey().getDefType().toString(),
                    entry.getKey().getQualifiedName()), entry.getValue());
            clientLoaded.remove(entry.getKey());
        }
        for (DefDescriptor<?> deleted : clientLoaded.keySet()) {
            loadedStrings.put(String.format("%s@%s", deleted.getDefType().toString(),
                    deleted.getQualifiedName()), DELETED);
        }
        if (loadedStrings.size() > 0) {
            json.writeMapKey("loaded");
            json.writeMap(loadedStrings);
        }

        if (testContextAdapter != null) {
            TestContext testContext = testContextAdapter.getTestContext();
            if (testContext != null) {
                json.writeMapEntry("test", testContext.getName());
            }
        }

        if (ctx.getFrameworkUID() != null) {
            json.writeMapEntry("fwuid", ctx.getFrameworkUID());
        } else {
            json.writeMapEntry("fwuid", configAdapter.getAuraFrameworkNonce());
        }

        //
        // Now comes the tricky part, we have to serialize all of the definitions that are
        // required on the client side, and, of all types. This way, we won't have to handle
        // ugly cases of actual definitions nested inside our configs, and, we ensure that
        // all dependencies actually get sent to the client. Note that the 'loaded' set needs
        // to be updated as well, but that needs to happen prior to this.
        //
        Map<DefDescriptor<? extends Definition>, Definition> defMap;

        defMap = ctx.getDefRegistry().filterRegistry(ctx.getPreloadedDefinitions());

        if (defMap.size() > 0) {
            List<Definition> componentDefs = Lists.newArrayList();
            List<Definition> eventDefs = Lists.newArrayList();
            List<Definition> libraryDefs = Lists.newArrayList();

            for (Map.Entry<DefDescriptor<? extends Definition>, Definition> entry : defMap.entrySet()) {
                DefDescriptor<? extends Definition> desc = entry.getKey();
                DefDescriptor.DefType dt = desc.getDefType();
                Definition d = entry.getValue();
                //
                // Ignore defs that ended up not being valid. This is arguably something
                // that the MDR should have done when filtering.
                //
                if (d != null) {
                    try {
                        d.retrieveLabels();
                    } catch (QuickFixException qfe) {
                        // this should not throw a QFE
                    }
                    if (DefDescriptor.DefType.COMPONENT.equals(dt) || DefDescriptor.DefType.APPLICATION.equals(dt)) {
                        componentDefs.add(d);
                    } else if (DefDescriptor.DefType.EVENT.equals(dt)) {
                        eventDefs.add(d);
                    } else if (DefDescriptor.DefType.LIBRARY.equals(dt)) {
                        libraryDefs.add(d);
                    }
                }
            }
            writeDefs(json, "componentDefs", componentDefs);
            writeDefs(json, "eventDefs", eventDefs);
            writeDefs(json, "libraryDefs", libraryDefs);
        }

        ctx.serializeAsPart(json);

        //
        // client needs value providers, urls don't
        // Note that we do this _post_ components, because they load labels.
        //
        boolean started = false;

        for (GlobalValueProvider valueProvider : ctx.getGlobalProviders().values()) {
            if (!valueProvider.isEmpty()) {
                if (!started) {
                    json.writeMapKey("globalValueProviders");
                    json.writeArrayBegin();
                    started = true;
                }
                try {
                    // Conditionally disable refSupport for specific value providers.
                    json.getSerializationContext().pushRefSupport(valueProvider.refSupport());
                    json.writeComma();
                    json.writeIndent();
                    json.writeMapBegin();
                    json.writeMapEntry("type", valueProvider.getValueProviderKey().getPrefix());
                    json.writeMapEntry("hasRefs", valueProvider.refSupport());
                    json.writeMapEntry("values", valueProvider.getData());
                    json.writeMapEnd();
                } finally {
                    json.getSerializationContext().popRefSupport();
                }
            }
        }

        if (started) {
            json.writeArrayEnd();
        }
        json.writeMapEnd();
    }
}
