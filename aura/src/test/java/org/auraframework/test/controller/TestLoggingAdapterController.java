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
package org.auraframework.test.controller;

import org.auraframework.annotations.Annotations.ServiceComponent;
import org.auraframework.ds.servicecomponent.Controller;
import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.test.adapter.TestLoggingAdapter;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@ServiceComponent
public class TestLoggingAdapterController implements Controller {

    @Inject
    private TestLoggingAdapter testloggingAdapter;

    @AuraEnabled
    public void beginCapture() {
        if (testloggingAdapter == null) {
            throw new Error("TestLoggingAdapter not configured!");
        }
        testloggingAdapter.clear();
        testloggingAdapter.beginCapture();
    }

    @AuraEnabled
    public List<Map<String, Object>> endCapture() {
        if (testloggingAdapter == null) {
            throw new Error("TestLoggingAdapter not configured!");
        }
        testloggingAdapter.endCapture();
        return testloggingAdapter.getLogs();
    }
}
