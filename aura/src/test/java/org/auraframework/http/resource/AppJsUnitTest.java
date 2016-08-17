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

package org.auraframework.http.resource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.auraframework.adapter.ServletUtilAdapter;
import org.auraframework.service.ServerService;
import org.auraframework.system.AuraContext;
import org.auraframework.system.AuraContext.Format;
import org.auraframework.util.test.util.UnitTestCase;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Simple (non-integration) test case for {@link AppJs}, most useful for exercising hard-to-reach error
 * conditions. I would like this test to be in the "aura" module (vice "aura-impl"), but the configuration there isn't
 * friendly to getting a context service, and I think changing that may impact other tests, so I'm leaving it at least
 * for now.
 */
public class AppJsUnitTest extends UnitTestCase {
    /**
     * Name is API!.
     */
    @Test
    public void testName() {
        assertEquals("app.js", new AppJs().getName());
    }

    /**
     * Format is API!.
     */
    @Test
    public void testFormat() {
        assertEquals(Format.JS, new AppJs().getFormat());
    }

    /**
     * Check that null dependencies doesn't call anything.
     *
     * This test will need to change if the internals change, but null should mean that nothing gets called.
     */
    @Test
    public void testNullDependencies() throws Exception {
        ServletUtilAdapter servletUtilAdapter = mock(ServletUtilAdapter.class);
        ServerService serverService = mock(ServerService.class);
        AppJs appJs = new AppJs();
        appJs.setServletUtilAdapter(servletUtilAdapter);
        appJs.setServerService(serverService);
        when(servletUtilAdapter.verifyTopLevel(any(HttpServletRequest.class),
                any(HttpServletResponse.class), any(AuraContext.class)))
            .thenReturn(null);

        appJs.write(null, null, null);

        // Verify the exception first. Because we catch all exceptions and generate gacks,
        // make sure the expected code path is exercised.
        verify(servletUtilAdapter, never()).handleServletException(any(Throwable.class),
                eq(false), any(AuraContext.class), any(HttpServletRequest.class),
                any(HttpServletResponse.class), anyBoolean());

        //
        // This is the known call to get the null.
        //
        verify(servletUtilAdapter, times(1)).verifyTopLevel(any(HttpServletRequest.class),
                any(HttpServletResponse.class), any(AuraContext.class));

        //
        // Nothing else should happen.
        //
        verifyNoMoreInteractions(serverService);
        verifyNoMoreInteractions(servletUtilAdapter);
    }

    /**
     * Verify that we set the correct contentType to response
     */
    @Test
    public void testSetContentType() {
        AppJs appJs = new AppJs();
        ServletUtilAdapter servletUtilAdapter = mock(ServletUtilAdapter.class);
        appJs.setServletUtilAdapter(servletUtilAdapter);
        when(servletUtilAdapter.getContentType(AuraContext.Format.JS)).thenReturn("text/javascript");
        HttpServletResponse response = new MockHttpServletResponse();

        appJs.setContentType(response);

        assertEquals("text/javascript", response.getContentType());
    }
}
