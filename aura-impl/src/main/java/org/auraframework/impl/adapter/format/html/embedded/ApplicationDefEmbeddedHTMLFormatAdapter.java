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
package org.auraframework.impl.adapter.format.html.embedded;

import com.google.common.collect.Maps;
import org.auraframework.adapter.ConfigAdapter;
import org.auraframework.adapter.ServletUtilAdapter;
import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.def.ApplicationDef;
import org.auraframework.def.ComponentDef;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.StyleDef;
import org.auraframework.instance.Application;
import org.auraframework.instance.Component;
import org.auraframework.service.ContextService;
import org.auraframework.service.InstanceService;
import org.auraframework.service.RenderingService;
import org.auraframework.service.SerializationService;
import org.auraframework.system.AuraContext;
import org.auraframework.throwable.AuraRuntimeException;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.auraframework.util.javascript.Literal;
import org.auraframework.util.json.JsonEncoder;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

@ThreadSafe
@ServiceComponent
public class ApplicationDefEmbeddedHTMLFormatAdapter extends EmbeddedHTMLFormatAdapter<ApplicationDef> {
    @Inject
    private ContextService contextService;

    @Inject
    private InstanceService instanceService;

    @Inject
    private RenderingService renderingService;

    @Inject
    private SerializationService serializationService;

    @Inject
    private ConfigAdapter configAdapter;

    @Inject
    private ServletUtilAdapter servletUtilAdapter;

    @Override
    public Class<ApplicationDef> getType() {
        return ApplicationDef.class;
    }

    @Override
    public void write(ApplicationDef value, Map<String, Object> componentAttributes, Appendable out) throws IOException {
        AuraContext context = contextService.getCurrentContext();

        try {
            ComponentDef templateDef = value.getTemplateDef();
            Map<String, Object> attributes = Maps.newHashMap();

            StringBuilder sb = new StringBuilder();
            writeHtmlStyles(servletUtilAdapter.getStyles(context), sb);
            attributes.put("auraStyleTags", sb.toString());
            sb.setLength(0);
            writeHtmlScripts(servletUtilAdapter.getScripts(context), sb);
            DefDescriptor<StyleDef> styleDefDesc = templateDef.getStyleDescriptor();
            if (styleDefDesc != null) {
                attributes.put("auraInlineStyle", styleDefDesc.getDef().getCode());
            }

            attributes.put("auraScriptTags", sb.toString());
            Map<String, Object> auraInit = Maps.newHashMap();

            Application instance = instanceService.getInstance(value, null);

            auraInit.put("instance", instance);
            auraInit.put("token", configAdapter.getCSRFToken());
            auraInit.put("host", context.getContextPath());
            auraInit.put("safeEvalWorker", configAdapter.getLockerWorkerURL());

            StringBuilder contextWriter = new StringBuilder();

            serializationService.write(context, null, AuraContext.class, contextWriter, "JSON");

            auraInit.put("context", new Literal(contextWriter.toString()));

            attributes.put("auraInit", JsonEncoder.serialize(auraInit, context.getJsonSerializationContext()));
            Component template = instanceService.getInstance(templateDef.getDescriptor(), attributes);
            renderingService.render(template, out);
        } catch (QuickFixException e) {
            throw new AuraRuntimeException(e);
        }
    }
}
