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

import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.def.BaseComponentDef;
import org.auraframework.def.RendererDef;
import org.auraframework.instance.BaseComponent;
import org.auraframework.instance.RendererInstance;
import org.auraframework.service.ContextService;
import org.auraframework.service.InstanceService;
import org.auraframework.service.RenderingService;
import org.auraframework.throwable.AuraRuntimeException;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import java.io.IOException;

/**
 */
@ServiceComponent
@Primary
public class RenderingServiceImpl implements RenderingService {
    @Inject
    ContextService contextService;

    @Inject
    InstanceService instanceService;
    
    /**
     */
    private static final long serialVersionUID = 1663840391180454913L;

    @Override
    public void render(BaseComponent<?, ?> component, Appendable out) throws QuickFixException, IOException {
        contextService.assertEstablished();

        BaseComponent<?, ?> renderable = null;
        BaseComponent<?, ?> tmpRenderable = component;
        BaseComponentDef componentDef = null;
        RendererDef rendererDef = null;

        while (tmpRenderable != null) {
            componentDef = tmpRenderable.getDescriptor().getDef();
            if (rendererDef == null) {
                rendererDef = componentDef.getLocalRendererDef();
                if (rendererDef == null && componentDef.getRendererDescriptor() != null) {
                    break;
                }
                renderable = tmpRenderable;
            }

            tmpRenderable = tmpRenderable.getSuper();
        }

        if (rendererDef == null) {
            throw new AuraRuntimeException(String.format("No local RendererDef found for %s", component));
        }

        RendererInstance renderer = (RendererInstance) instanceService.getInstance(rendererDef);

        renderer.render(renderable, out);
    }

}
