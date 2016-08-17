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

package org.auraframework.integration.test.http.resource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.auraframework.adapter.ServletUtilAdapter;
import org.auraframework.def.DefDescriptor;
import org.auraframework.http.resource.AppJs;
import org.auraframework.impl.AuraImplTestCase;
import org.auraframework.service.ServerService;
import org.auraframework.system.AuraContext;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

public class AppJsTest extends AuraImplTestCase {
    
    /**
     * Check for an exception being caught and routed to the exception handler.
     *
     * Most of this is internal, but we want to make sure that we call handleServletException if there
     * is an exception in the writing of the CSS.
     * 
     * This test may need to be in integration-test module, since Resources.toString is used in
     * prependBootstrapJsPayload(). In the latest version, it throws an exception when url is not
     * reachable. Strict unit test may not work.
     */
    @Test
    public void testExceptionInWrite() throws Exception {
        // Arrange
        ServletUtilAdapter servletUtilAdapter = mock(ServletUtilAdapter.class);
        ServerService serverService = mock(ServerService.class);
        Throwable expectedException = new RuntimeException();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HashSet<DefDescriptor<?>> dependencies = new HashSet<>();
        when(servletUtilAdapter.verifyTopLevel(any(HttpServletRequest.class),
                    any(HttpServletResponse.class), any(AuraContext.class)))
            .thenReturn(dependencies);
        doThrow(expectedException).when(serverService).writeDefinitions(eq(dependencies), any(Writer.class));

        AppJs appJs = new AppJs();
        appJs.setServletUtilAdapter(servletUtilAdapter);
        appJs.setServerService(serverService);

        // Act
        appJs.write(null, response, null);

        // Assert
        // Verify the exception first. Because we catch all exceptions and generate gacks,
        // make sure the expected code path is exercised.
        verify(servletUtilAdapter, times(1)).handleServletException(eq(expectedException),
                eq(false), any(AuraContext.class), any(HttpServletRequest.class),
                any(HttpServletResponse.class), anyBoolean());

        // Knock off the known calls. These are mocked above, and are internal implementation dependent.
        verify(servletUtilAdapter, times(1)).verifyTopLevel(any(HttpServletRequest.class),
                any(HttpServletResponse.class), any(AuraContext.class));
        verify(serverService, times(1)).writeDefinitions(same(dependencies), any(PrintWriter.class));

        // Make sure nothing else happens.
        verifyNoMoreInteractions(serverService);
        verifyNoMoreInteractions(servletUtilAdapter);
    }

}
