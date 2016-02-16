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
package org.auraframework.org.auraframework.di;

import java.util.Map;

/**
 * Provides dependency injection implementations ("beans"( for given interface
 * The provider itself is a bean which can be passed into classes which are not
 * and should not be injected or autowired
 */
public interface ImplementationProvider {

    <T> T getImplementation(Class<T> clazz);

    <T> Map<String, T> getImplementations(Class<T> clazz);
}
