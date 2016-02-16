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
package org.auraframework.impl.root.parser.handler;

import com.google.common.collect.ImmutableSet;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.LibraryDef;
import org.auraframework.def.RootDefinition;
import org.auraframework.impl.DefinitionAccessImpl;
import org.auraframework.impl.root.library.ImportDefImpl;
import org.auraframework.service.DefinitionService;
import org.auraframework.system.AuraContext;
import org.auraframework.system.Source;
import org.auraframework.throwable.quickfix.InvalidDefinitionException;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.auraframework.util.AuraTextUtil;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.Set;

public class ImportDefHandler extends XMLHandler<ImportDefImpl> {

    public static final String TAG = "aura:import";

    private static final String ATTRIBUTE_LIBRARY = "library";
    private static final String ATTRIBUTE_PROPERTY = "property";

    protected final static Set<String> ALLOWED_ATTRIBUTES = ImmutableSet.of(ATTRIBUTE_LIBRARY, ATTRIBUTE_PROPERTY,
            RootTagHandler.ATTRIBUTE_DESCRIPTION);

    private RootTagHandler<? extends RootDefinition> parentHandler;
    private final ImportDefImpl.Builder builder = new ImportDefImpl.Builder();

    public ImportDefHandler() {
        super();
    }

    public ImportDefHandler(RootTagHandler<? extends RootDefinition> parentHandler, XMLStreamReader xmlReader,
                            Source<?> source, DefinitionService definitionService) {
        super(xmlReader, source, definitionService);
        this.parentHandler = parentHandler;
    }

    @Override
    public ImportDefImpl getElement() throws XMLStreamException, QuickFixException {
        validateAttributes();

        DefDescriptor<? extends RootDefinition> defDescriptor = parentHandler.getDefDescriptor();
        builder.setParentDescriptor(defDescriptor);
        builder.setLocation(getLocation());

        String library = getAttributeValue(ATTRIBUTE_LIBRARY);
        if (AuraTextUtil.isNullEmptyOrWhitespace(library)) {
            throw new InvalidDefinitionException(String.format("%s missing library attribute", TAG), getLocation());
        }
        DefDescriptor<LibraryDef> descriptor = definitionService.getDefDescriptor(library.trim(), LibraryDef.class);
        builder.setDescriptor(descriptor);

        String property = getAttributeValue(ATTRIBUTE_PROPERTY);
        if (AuraTextUtil.isNullEmptyOrWhitespace(property)) {
            throw new InvalidDefinitionException(String.format("%s missing property attribute", TAG), getLocation());
        }
        builder.setProperty(property.trim());

        builder.setDescription(getAttributeValue(RootTagHandler.ATTRIBUTE_DESCRIPTION));

        int next = xmlReader.next();
        if (next != XMLStreamConstants.END_ELEMENT || !TAG.equalsIgnoreCase(getTagName())) {
            error("expected end of %s tag", TAG);
        }

        builder.setOwnHash(source.getHash());
        builder.setAccess(new DefinitionAccessImpl(AuraContext.Access.PRIVATE));

        return builder.build();
    }

    @Override
    public Set<String> getAllowedAttributes() {
        return ALLOWED_ATTRIBUTES;
    }

    @Override
    public String getHandledTag() {
        return TAG;
    }
}
