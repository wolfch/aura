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
package org.auraframework.integration.test.root.parser.handler;

import org.auraframework.def.ApplicationDef;
import org.auraframework.def.ComponentDef;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.Definition;
import org.auraframework.impl.AuraImplTestCase;
import org.auraframework.test.source.StringSourceLoader;
import org.auraframework.test.source.StringSourceLoader.NamespaceAccess;
import org.auraframework.throwable.quickfix.QuickFixException;
import org.auraframework.util.test.annotation.UnAdaptableTest;
import org.junit.Ignore;
import org.junit.Test;

@UnAdaptableTest("namespace start with c means something special in core")
public class ComponentAccessAttributeEnforcementTest extends AuraImplTestCase {
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithSameSystemNamespaceGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithSameSystemNamespace() throws QuickFixException 
    {
        //create component with system namespace

        String cmpSource = "<aura:component isTemplate='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithSameSystemNamespacePublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithSameSystemNamespaceInternal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='INTERNAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithOtherSystemNamespace() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithOtherSystemNamespaceGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithOtherSystemNamespacePublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithOtherSystemNamespaceInternal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='INTERNAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithCustomNamespace() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component isTemplate='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithCustomNamespaceGlobal() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component isTemplate='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithSystemNamespaceImplementsTemplateWithCustomNamespacePublic() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component isTemplate='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    
    @Test
    public void testApplicationWithCustomNamespaceImplementsTemplateWithSystemNamespace() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component isTemplate='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceImplementsTemplateWithSameCustomNamespace() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component isTemplate='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceImplementsTemplateWithOtherCustomNamespace() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component isTemplate='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application template='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    

    /**
     * Abstract tests start
     */
    @Test
    public void testComponentWithSystemNamespaceHasAbstractComponentWithSameSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component abstract='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component with above component in markup
        String source = "<aura:component> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:component>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent2",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithSystemNamespaceHasAbstractComponentWithOtherSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component abstract='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component with above component in markup
        String source = "<aura:component> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:component>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent2",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceHasAbstractComponentWithSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component abstract='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component with above component in markup
        String source = "<aura:component> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:component>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent2",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceHasAbstractComponentWithSameCustomNamespaceInMarkup() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component abstract='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component with above component in markup
        String source = "<aura:component> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:component>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent2",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceHasAbstractComponentWithAnotherCustomNamespaceInMarkup() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component abstract='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component with above component in markup
        String source = "<aura:component> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:component>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testcomponent2",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithSystemNamespaceHasAbstractComponentWithCustomNamespaceInMarkup() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component abstract='true' access='GLOBAL'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component with above component in markup
        String source = "<aura:component> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:component>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent2",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }


    /**
     * Privileged access tests start
     * I won't add more tests to this, because our access='privileged' only works on client side, see W-2996170. 
     */
    @Test
    @Ignore("W-2996170")
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSystemNamespaceInMarkupAccessPrivileged() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component access='Privileged'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        descriptor.getDef();
    }
    /**
     * Default access tests start
     */
    /**
     * tests around including another component in your markup start
     */
    /**
     * tests around privileged namespace starts
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithPrivilegedNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithPrivilegedNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
         	fail("component of custom namespace shouldn't be able to include component of privileged namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'privilegedNS:testcomponent1' from namespace 'cstring' in 'markup://cstring:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSamePrivilegedNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithOtherPrivilegedNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
         	fail("component of privileged namespace shouldn't be able to include component of another privileged namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
         	fail("component of privileged namespace shouldn't be able to include component of system namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'string:testcomponent1' from namespace 'privilegedNS' in 'markup://privilegedNS:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSystemNamespaceInMarkupAccessInternal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Internal'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
        	fail("component of privileged namespace shouldn't be able to include component of system namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'string:testcomponent1' from namespace 'privilegedNS' in 'markup://privilegedNS:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithCustomNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
        	fail("component of privileged namespace shouldn't be able to include component of custom namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'cstring:testcomponent1' from namespace 'privilegedNS' in 'markup://privilegedNS:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithSameSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithOtherSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
         	fail("component of custom namespace shouldn't be able to include component of system namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'string1:testcomponent1' from namespace 'cstring' in 'markup://cstring:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithCustomNamespaceInMarkup() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithSameCustomNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithAnotherCustomNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
        	fail("component of custom namespace shouldn't be able to include componet of another custom namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'cstring:testcomponent1' from namespace 'cstring1' in 'markup://cstring1:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    /**
     * tests around extends start
     */
    @Test
    public void testComponnetWithSystemNamespaceExtendsComponentWithSameSystemNamespace() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithSystemNamespaceExtendsComponentWithOtherSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithSystemNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
         	fail("component of custom namespace shouldn't be able to include component of system namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'string1:testcomponent1' from namespace 'cstring' in 'markup://cstring:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    @Test
    public void testComponentWithSystemNamespaceExtendsComponentWithCustomNamespaceInMarkup() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithSameCustomNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithAnotherCustomNamespaceInMarkup() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
        	fail("component of custom namespace shouldn't be able to include componet of another custom namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'cstring:testcomponent1' from namespace 'cstring1' in 'markup://cstring1:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
        
    
    /**
     * Verify Public access enforcement
     */
    /**
     * tests around privileged namespace starts
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithPrivilegedNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithPrivilegedNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
         	fail("component of custom namespace shouldn't be able to include component of privileged namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'privilegedNS:testcomponent1' from namespace 'cstring' in 'markup://cstring:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSamePrivilegedNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithOtherPrivilegedNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
         	fail("component of privileged namespace shouldn't be able to include component of another privileged namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSystemNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
         	fail("component of privileged namespace shouldn't be able to include component of system namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'string:testcomponent1' from namespace 'privilegedNS' in 'markup://privilegedNS:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        try {
        	descriptor.getDef();
        	fail("component of privileged namespace shouldn't be able to include component of custom namespace");
        } catch(Exception e) {
        	assertTrue("get unexpected message:"+e.getMessage(), e.getMessage().contains("disallowed by MasterDefRegistry.assertAccess()"));
        	//expect 
    		//System.out.println(e.getMessage());
        	//Access to component 'cstring:testcomponent1' from namespace 'privilegedNS' in 'markup://privilegedNS:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    /**
     * Verify Public access enforcement
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithSameSystemNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithOtherSystemNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithSystemNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
         	fail("component of custom namespace shouldn't be able to include component of system namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'string1:testcomponent1' from namespace 'cstring' in 'markup://cstring:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithSameCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithAnotherCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
        	fail("component of custom namespace shouldn't be able to include componet of another custom namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'cstring:testcomponent1' from namespace 'cstring1' in 'markup://cstring1:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    
    /**
     * tests around extends start
     */
    @Test
    public void testComponnetWithSystemNamespaceExtendsComponentWithSameSystemNamespaceAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithSystemNamespaceExtendsComponentWithOtherSystemNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithSystemNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
         	fail("component of custom namespace shouldn't be able to include component of system namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'string1:testcomponent1' from namespace 'cstring' in 'markup://cstring:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    
    @Test
    public void testComponentWithSystemNamespaceExtendsComponentWithCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithSameCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithAnotherCustomNamespaceInMarkupAccessPublic() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='PUBLIC'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        try {
        	descriptor.getDef();
        	fail("component of custom namespace shouldn't be able to include componet of another custom namespace");
        } catch (Exception e) {
        	//expect 
    		//System.out.println(e.getMessage());
    		//Access to component 'cstring:testcomponent1' from namespace 'cstring1' in 'markup://cstring1:testapplication2(APPLICATION)' disallowed by MasterDefRegistry.assertAccess()
        }
    }
    

    /**
     * Global access tests start
     */
    /**
     * tests around privileged namespace starts
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithPrivilegedNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Public'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithPrivilegedNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        	descriptor.getDef();
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSamePrivilegedNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        descriptor.getDef();
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithOtherPrivilegedNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testcomponent",
                        NamespaceAccess.PRIVILEGED);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        	descriptor.getDef();
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithSystemNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        	descriptor.getDef();
    }
    @Test
    public void testApplicationWithPrivilegedNamespaceIncludeComponentWithCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_PRIVILEGED_NAMESPACE + ":testapplication",
                        NamespaceAccess.PRIVILEGED);
        	descriptor.getDef();
    }
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithSameSystemNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithOtherSystemNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithSystemNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testApplicationWithSystemNamespaceIncludeComponentWithCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testapplication",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithSameCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testApplicationWithCustomNamespaceIncludeComponentWithAnotherCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create application with above component in markup
        String source = "<aura:application> <" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "/> </aura:application>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ApplicationDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testapplication",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testComponnetWithSystemNamespaceExtendsComponentWithSameSystemNamespaceAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testComponentWithSystemNamespaceExtendsComponentWithOtherSystemNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithSystemNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.OTHER_NAMESPACE + ":testcomponent",
                        NamespaceAccess.INTERNAL);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testComponentWithSystemNamespaceExtendsComponentWithCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with custom namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.INTERNAL);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithSameCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
    
    /**
     * Verify Global access enforcement
     */
    @Test
    public void testComponentWithCustomNamespaceExtendsComponentWithAnotherCustomNamespaceInMarkupAccessGlobal() throws QuickFixException {
        //create component with system namespace
        String cmpSource = "<aura:component extensible='true' access='Global'/>";
        DefDescriptor<? extends Definition> cmpDescriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, cmpSource,
                StringSourceLoader.DEFAULT_CUSTOM_NAMESPACE + ":testcomponent",
                        NamespaceAccess.CUSTOM);
        //create component extends above component
        String source = "<aura:component extends='" + cmpDescriptor.getNamespace() + ":" + cmpDescriptor.getName() + "'/>";
        DefDescriptor<? extends Definition> descriptor = getAuraTestingUtil().addSourceAutoCleanup(ComponentDef.class, source,
                StringSourceLoader.OTHER_CUSTOM_NAMESPACE + ":testcomponentChild",
                        NamespaceAccess.CUSTOM);
        descriptor.getDef();
    }
}
