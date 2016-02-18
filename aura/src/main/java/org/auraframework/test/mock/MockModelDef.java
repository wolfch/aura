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
package org.auraframework.test.mock;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.auraframework.def.DefDescriptor;
import org.auraframework.def.ModelDef;
import org.auraframework.def.TypeDef;
import org.auraframework.def.ValueDef;
import org.auraframework.instance.Model;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.auraframework.util.json.Json;

import com.google.common.collect.Maps;

/**
 * A simple ModelDef that provides a MockModel instance.
 */
public class MockModelDef extends MockDefinition<ModelDef> implements ModelDef {
	private static final long serialVersionUID = 8237818157530284425L;
	private final Map<String, ValueDef> members;

    public MockModelDef(DefDescriptor<ModelDef> descriptor, Set<ValueDef> members, List<Answer<Model>> instances) {
        super(descriptor);
        this.members = Maps.newLinkedHashMap();
        if (members != null) {
            for (ValueDef val : members) {
                this.members.put(val.getName(), val);
            }
        }
    }

    @Override
    public void serialize(Json json) throws IOException {
        json.writeMapBegin();
        json.writeMapEntry("descriptor", getDescriptor());
        json.writeMapEntry("members", members.values());
        json.writeMapEnd();
    }

    @Override
    public ValueDef getMemberByName(String name) {
        return members.get(name);
    }

    @Override
    public boolean hasMembers() {
        return !members.isEmpty();
    }

    @Override
    public TypeDef getType(String s) throws QuickFixException {
        return getMemberByName(s).getType();
    }
}
