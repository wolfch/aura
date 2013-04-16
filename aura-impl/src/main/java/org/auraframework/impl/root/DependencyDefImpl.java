/*
 * Copyright (C) 2012 salesforce.com, inc.
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
package org.auraframework.impl.root;

import java.io.IOException;
import java.util.Set;

import org.auraframework.Aura;
import org.auraframework.builder.DependencyDefBuilder;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.DependencyDef;
import org.auraframework.def.DescriptorFilter;
import org.auraframework.def.RootDefinition;
import org.auraframework.impl.system.DefinitionImpl;
import org.auraframework.system.MasterDefRegistry;
import org.auraframework.system.SourceListener;
import org.auraframework.throwable.quickfix.InvalidDefinitionException;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.auraframework.util.json.Json;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * The definition of a declared dependency.
 */
public final class DependencyDefImpl extends DefinitionImpl<DependencyDef> implements DependencyDef {
    private static final long serialVersionUID = -3245215240391599759L;

    private static final int DEPENDENCY_CACHE_SIZE = 512;
    private final static Cache<DescriptorFilter, Set<DefDescriptor<?>>> dependencyCache = CacheBuilder.newBuilder()
            .initialCapacity(DEPENDENCY_CACHE_SIZE).maximumSize(DEPENDENCY_CACHE_SIZE).build();

    private static final class CacheBlaster implements SourceListener {
        public CacheBlaster() {
            Aura.getDefinitionService().subscribeToChangeNotification(this);
        }

        @Override
        public void onSourceChanged(DefDescriptor<?> source, SourceMonitorEvent event) {
            dependencyCache.invalidateAll();
        }
    }
    // This is protected because me must keep a reference, but we don't appear to use this.
    protected static final CacheBlaster cb = new CacheBlaster();

    private final DefDescriptor<? extends RootDefinition> parentDescriptor;
    private final DescriptorFilter dependency;
    private final QuickFixException error;

    protected DependencyDefImpl(Builder builder) {
        super(builder);

        DescriptorFilter tmp = null;
        QuickFixException caught = null;

        this.parentDescriptor = builder.parentDescriptor;
        if (builder.resource != null) {
            try {
                tmp = new DescriptorFilter(builder.resource, builder.type);
            } catch (IllegalArgumentException iae) {
                caught = new InvalidDefinitionException(iae.getMessage(), getLocation());
            }
        } else {
            caught = new InvalidDefinitionException("Missing required resource", getLocation());
        }
        this.dependency = tmp;
        this.error = caught;
    }

    @Override
    public void validateDefinition() throws QuickFixException {
        // super.validateDefinition();
        if (this.error != null) {
            throw this.error;
        }
        if (this.parentDescriptor == null) {
            throw new InvalidDefinitionException("No parent in DependencyDef", getLocation());
        }
    }

    @Override
    public void validateReferences() throws QuickFixException {
        super.validateReferences();
    }

    @Override
    public void appendDependencies(Set<DefDescriptor<?>> dependencies) throws QuickFixException {
        MasterDefRegistry mdr = Aura.getContextService().getCurrentContext().getDefRegistry();
        Set<DefDescriptor<?>> found;

        found = dependencyCache.getIfPresent(dependency);

        if (found == null) {
            found = mdr.find(dependency);
            if (found.size() == 0) {
                // TODO: QuickFix for broken dependency.
                throw new InvalidDefinitionException("Invalid dependency " + this.dependency, getLocation());
            }
            dependencyCache.put(dependency, found);
        }
        dependencies.addAll(found);
    }

    /**
     * @return Returns the parentDescriptor.
     */
    @Override
    public DefDescriptor<? extends RootDefinition> getParentDescriptor() {
        return parentDescriptor;
    }

    /**
     * Gets the dependency for this instance.
     * 
     * @return The dependency.
     */
    @Override
    public DescriptorFilter getDependency() {
        return this.dependency;
    }

    @Override
    public void serialize(Json json) throws IOException {
        // We do not serialize.
    }

    @Override
    public String toString() {
        return String.valueOf(this.dependency);
    }

    public static class Builder extends DefinitionImpl.BuilderImpl<DependencyDef> implements DependencyDefBuilder {

        public Builder() {
            super(DependencyDef.class);
        }

        private DefDescriptor<? extends RootDefinition> parentDescriptor;
        private String resource;
        private String type;

        /**
         * @see org.auraframework.impl.system.DefinitionImpl.BuilderImpl#build()
         */
        @Override
        public DependencyDefImpl build() {
            return new DependencyDefImpl(this);
        }

        /**
         * Sets the parentDescriptor for this instance.
         * 
         * @param parentDescriptor The parentDescriptor.
         */
        @Override
        public Builder setParentDescriptor(DefDescriptor<? extends RootDefinition> parentDescriptor) {
            this.parentDescriptor = parentDescriptor;
            return this;
        }

        /**
         * Sets the resource for this instance.
         * 
         * @param resource The resource.
         */
        @Override
        public Builder setResource(String resource) {
            this.resource = resource;
            return this;
        }

        /**
         * Sets the type for this instance.
         * 
         * @param type The type.
         */
        @Override
        public Builder setType(String type) {
            this.type = type;
            return this;
        }
    }
}
