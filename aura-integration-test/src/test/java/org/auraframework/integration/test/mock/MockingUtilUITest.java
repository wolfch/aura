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
package org.auraframework.integration.test.mock;

import java.util.Map;

import javax.inject.Inject;

import org.auraframework.def.ApplicationDef;
import org.auraframework.def.ComponentDef;
import org.auraframework.def.ControllerDef;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.ModelDef;
import org.auraframework.def.ProviderDef;
import org.auraframework.impl.parser.ParserFactory;
import org.auraframework.impl.test.mock.MockingUtil;
import org.auraframework.instance.ComponentConfig;
import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.system.AuraContext.Authentication;
import org.auraframework.system.AuraContext.Format;
import org.auraframework.system.AuraContext.Mode;
import org.auraframework.test.TestContextAdapter;
import org.auraframework.test.util.WebDriverTestCase;
import org.auraframework.util.FileMonitor;
import org.auraframework.util.test.annotation.UnAdaptableTest;
import org.junit.Test;
import org.openqa.selenium.By;

import com.google.common.collect.ImmutableMap;

@UnAdaptableTest("W-2329849: Failing on SFDC but passing on standalone ios-driver builds. Needs investigation")
public class MockingUtilUITest extends WebDriverTestCase {
    @Inject
    private TestContextAdapter testContextAdapter;

    @Inject
    private FileMonitor fileMonitor;

    @Inject
    private ParserFactory parserFactory;

    private MockingUtil mockingUtil;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockingUtil = new MockingUtil(testContextAdapter, definitionService, fileMonitor, parserFactory);
    }

    @Override
    public void tearDown() throws Exception {
        contextService.endContext();
        super.tearDown();
    }

    @Test
    public void testMockModelSanity() throws Exception {
        if (!contextService.isEstablished()) {
            contextService.startContext(Mode.SELENIUM, Format.HTML, Authentication.AUTHENTICATED);
        }
        DefDescriptor<ModelDef> modelDefDescriptor = definitionService
                .getDefDescriptor("java://org.auraframework.components.test.java.model.TestJavaModel", ModelDef.class);
        DefDescriptor<ApplicationDef> appDescriptor = addSourceAutoCleanup(ApplicationDef.class,
                String.format(baseApplicationTag, String.format("model='%s'", modelDefDescriptor.getQualifiedName()),
                        "{!m.string}<aura:iteration items='{!m.stringList}' var='i'>{!i}</aura:iteration>"));
        // sanity without mocks
        open(appDescriptor);
        assertEquals("Modelonetwothree", getText(By.cssSelector("body")));
    }

    // Start with a broken component and mock to provide a "working" one.
    @Test
    public void testMockServerProvider() throws Exception {
        DefDescriptor<ProviderDef> providerDefDescriptor = definitionService.getDefDescriptor(
                "java://org.auraframework.impl.java.provider.TestComponentConfigProvider", ProviderDef.class);
        DefDescriptor<ComponentDef> cmpDefDescriptor = addSourceAutoCleanup(ComponentDef.class, String
                .format(baseComponentTag, String.format("provider='%s'", providerDefDescriptor.getQualifiedName()),
                        "<aura:attribute name='echo' type='String'/>{!v.echo}"));
        String url = String.format("/%s/%s.cmp", cmpDefDescriptor.getNamespace(), cmpDefDescriptor.getName());//  /string/thing1.cmp

        // no mocking - provider output isn't valid for this component (or any
        // probably), but the def is valid
        openNoAura(url);
        assertTrue(getText(By.cssSelector("body")).contains(String.format("%s did not provide a valid component",
                providerDefDescriptor.getQualifiedName())));

        // mock provided attributes
        ComponentConfig componentConfig = new ComponentConfig();
        Map<String, Object> attribs = ImmutableMap.of("echo", (Object) "goodbye");
        componentConfig.setAttributes(attribs);
        mockingUtil.mockServerProviderDef(providerDefDescriptor, componentConfig);
        openNoAura(url);
        assertEquals("goodbye", getText(By.cssSelector("body")));
    }

    // Dummy controller method for use by testMockServerAction
    @AuraEnabled
    public static Object lookInside() {
        return "not so interesting";
    }

    @Test
    public void testMockServerActionSanity() throws Exception {
        if (!contextService.isEstablished()) {
            contextService.startContext(Mode.SELENIUM, Format.HTML, Authentication.AUTHENTICATED);
        }
        DefDescriptor<ControllerDef> controllerDefDescriptor = definitionService.getDefDescriptor(
                String.format("java://%s", this.getClass().getCanonicalName()), ControllerDef.class);
        DefDescriptor<ComponentDef> cmpDefDescriptor = addSourceAutoCleanup(
                ComponentDef.class,
                String.format(baseComponentTag,
                        String.format("controller='%s'", controllerDefDescriptor.getQualifiedName()),
                        "<ui:button press='{!c.clicked}' label='act'/><div class='result' aura:id='result'></div>"));
        DefDescriptor<ControllerDef> clientControllerDefDescriptor = definitionService.getDefDescriptor(
                String.format("js://%s.%s", cmpDefDescriptor.getNamespace(), cmpDefDescriptor.getName()),
                ControllerDef.class);
        addSourceAutoCleanup(clientControllerDefDescriptor, "{clicked:function(component){"
                + "var a = component.get('c.lookInside');a.setCallback(component,"
                + "function(action){component.find('result').getElement().innerHTML=action.getReturnValue();});"
                + "$A.enqueueAction(a);}}");
        // sanity without mocks
        open(cmpDefDescriptor);
        assertEquals("", getText(By.cssSelector("div.result")));
        findDomElement(By.cssSelector("button")).click();
        waitForElementTextPresent(findDomElement(By.cssSelector("div.result")), "not so interesting");
    }
}
