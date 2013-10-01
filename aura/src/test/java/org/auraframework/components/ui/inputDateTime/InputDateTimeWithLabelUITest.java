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
package org.auraframework.components.ui.inputDateTime;

import org.auraframework.test.WebDriverTestCase;
import org.auraframework.test.WebDriverUtil.BrowserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputDateTimeWithLabelUITest extends WebDriverTestCase{
    public String URL = "/uitest/inputDateTime_Test.cmp";;
    //private final String DATE_INPUT_BOX_SEL = "input[class*='date_input_box']";
    private final String CLASSNAME = "return $A.test.getActiveElement().className";


    /**
     * Excluded Browser Reasons: IE9/10: Sending in Shift anything (tab, page up, page down), does not register when
     * sent through WebDriver. Manually works fine Android and IOS devices: this feature will not be used on mobile
     * devices. Instead the their native versions will be used Safari: Sending in Shift tab does not register when sent
     * through WebDriver. Manually works fine
     */

    public InputDateTimeWithLabelUITest(String name) {
        super(name);
    }

    /***********************************************************************************************
     *********************************** HELPER FUNCTIONS********************************************
     ***********************************************************************************************/    
    public void gotToNextElem(WebDriver driver, String shftTab) {
        String classOfActiveElem = "a[class*='" + auraUITestingUtil.getEval(CLASSNAME) + "']";
        findDomElement(By.cssSelector(classOfActiveElem)).sendKeys(shftTab);

    }

    /***********************************************************************************************
     *********************************** Date Picker Tests*******************************************
     ************************************************************************************************/

    // Testing functionallity of tab, starting from the InputBox to the today button
    // Do Not run with Safari. Safari does not handle tabs normally
    @ExcludeBrowsers({ BrowserType.SAFARI, BrowserType.ANDROID_PHONE, BrowserType.ANDROID_TABLET, BrowserType.IPAD,
            BrowserType.IPHONE })
    public void testTab_TODO() throws Exception {}

    // Checking functionality of the shift tab button
    @ExcludeBrowsers({ BrowserType.IE9, BrowserType.IE10, BrowserType.SAFARI, BrowserType.ANDROID_PHONE,
            BrowserType.ANDROID_TABLET, BrowserType.IPAD, BrowserType.IPHONE })
    public void testShiftTab_TODO() throws Exception {}
}