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
package org.auraframework.integration.test.components.aura;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.auraframework.integration.test.logging.LoggingTestAppender;
import org.auraframework.test.util.WebDriverTestCase;
import org.auraframework.util.test.annotation.ThreadHostileTest;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

/**
 * UI Test for LabelValueProvider.js
 */
public class LabelValueProviderUITest extends WebDriverTestCase {

    // URL string to go to
    private final String URL = "/gvpTest/labelProvider.cmp";
    private By label1 = By.xpath("//div[@id='div1']");
    
    private Logger logger;
    private LoggingTestAppender appender;
    private Level originalLevel;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        appender = new LoggingTestAppender();

        logger = Logger.getLogger("LoggingContextImpl");
        // When we run integration tests, the logging level of logger LoggingContextImpl
        // is WARN, setting it into INFO here so that we can get the log as we run the app.
        originalLevel = logger.getLevel();
        logger.setLevel(Level.INFO);
        logger.addAppender(appender);
    }
    
    @Override
    public void tearDown() throws Exception {
        logger.removeAppender(appender);
        logger.setLevel(originalLevel);
        super.tearDown();
    }
    
    /**
     * Test we have one java call for each valid label request.
     * @throws Exception
     */
    @ThreadHostileTest("TestLoggingAdapter not thread-safe")
    @Test
    public void testEfficientActionRequests() throws Exception {
        open(URL);
        auraUITestingUtil.waitForElementText(label1, "simplevalue1: Today", true);
        
        Long callCount = 0L;
        boolean isLabelControllerCalled = false;
        String message;
        List<LoggingEvent> logs = appender.getLog();
        for(LoggingEvent le : logs) {
       	 	message = le.getMessage().toString();
       	 	while (message.contains("aura://LabelController/ACTION$getLabel")) {
       	 		isLabelControllerCalled = true;
       	 		message = message.substring(message.indexOf("getLabel")+8, message.length()-1);
       	 		callCount ++;
       	 	}
       	 	if(callCount > 0) {
       	 		break;
       	 	}
        }
        assertTrue("Fail: LabelController should be called", isLabelControllerCalled);
        assertTrue("Fail: There should be two calls to LabelController "+callCount.toString(), callCount == 2L);
    }
}
