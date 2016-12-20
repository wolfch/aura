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
package org.auraframework.integration.test;

import static org.auraframework.integration.test.http.AuraRequest.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.auraframework.adapter.ServletUtilAdapter;
import org.auraframework.http.AuraBaseServlet;
import org.auraframework.http.AuraTestFilter;
import org.auraframework.http.CapturingResponseWrapper;
import org.auraframework.http.HttpFilter;
import org.auraframework.integration.test.util.WebDriverTestCase;
import org.auraframework.integration.test.util.WebDriverTestCase.ExcludeBrowsers;
import org.auraframework.system.AuraContext.Format;
import org.auraframework.test.util.WebDriverUtil.BrowserType;
import org.auraframework.throwable.InvalidSessionException;
import org.auraframework.util.test.annotation.FreshBrowserInstance;
import org.auraframework.util.test.annotation.ThreadHostileTest;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

// CSRF is only stored in persistent storage. indexedDB is not supported on Safari,
// so persistent storage is not able to be created on Safari.
@ExcludeBrowsers({ BrowserType.SAFARI, BrowserType.IPAD, BrowserType.IPHONE })
public class AuraClientServiceUITest extends WebDriverTestCase {
	@Inject
	private AuraTestFilter auraTestFilter;
	
	@Inject
    private ServletUtilAdapter servletUtilAdapter;

	@FreshBrowserInstance
    @ThreadHostileTest("ConfigAdapter modified, can't tolerate other tests.")
    @Test
    public void testCsrfTokenSavedOnInit() throws Exception {
		String target = "/clientServiceTest/csrfTokenStorage.app";
		
		AtomicLong counter = new AtomicLong();
    	String expectedBase = "expectedTestToken";
		getMockConfigAdapter().setCSRFToken(() -> {
			return expectedBase + counter.incrementAndGet();
		});
		
        open(target);
        
        // .app generates a token internally since the HTML write is part 1, and inline.js is part 2
        String expectedToken = expectedBase + "2";

        waitForText(By.className("output"), expectedToken);
        
        String storedToken = getStoredToken();
		assertEquals(expectedToken, storedToken);
    }

	@FreshBrowserInstance
    @ThreadHostileTest("ConfigAdapter modified, can't tolerate other tests.")
    @Test
    public void testCsrfTokenLoadedFromStorageOnInit() throws Exception {
		// OnePageApp allows caching of inline.js
		String target = "/clientServiceTest/csrfTokenStorageOnePageApp.app";

		String initialToken = "initialToken";
        getMockConfigAdapter().setCSRFToken(initialToken);

		open(target);
		
        waitForText(By.className("output"), initialToken);
        
        // Manually set the token in storage
		getAuraUITestingUtil().getEval("$A.clientService.resetToken('updatedToken')");
		
		// Reload with caching so that the stored token will not get overwritten by newer server token
        getAuraUITestingUtil().getEval("location.href = location.href;");
        waitForAuraFrameworkReady();
        
        waitForText(By.className("output"), "updatedToken");
    }

	@FreshBrowserInstance
    @ThreadHostileTest("ConfigAdapter modified, can't tolerate other tests.")
    @Test
    public void testCsrfTokenReplacedWithUpdatedToken() throws Exception {
		// OnePageApp allows caching of inline.js
		String target = "/clientServiceTest/csrfTokenStorageOnePageApp.app";

		AtomicLong counter = new AtomicLong();
    	String expectedBase = "expectedTestToken";
		getMockConfigAdapter().setCSRFToken(() -> {
			return expectedBase + counter.incrementAndGet();
		});

		open(target);
		
        // .app generates a token internally since the HTML write is part 1, and inline.js is part 2
        String expectedToken = expectedBase + "2";
        waitForText(By.className("output"), expectedToken);
        
        // Manually set the token in storage
		getAuraUITestingUtil().getEval("$A.clientService.resetToken('updatedToken')");
		
		open(target);
        
        expectedToken = expectedBase + "4";
        waitForText(By.className("output"), expectedToken);

        String storedToken = getStoredToken();
		assertEquals(expectedToken, storedToken);
	}

	@FreshBrowserInstance
    @ThreadHostileTest("ConfigAdapter modified, can't tolerate other tests.")
    @Test
    @Ignore("IN_DEV")
    public void testCsrfTokenSavedFromInvalidSessionException() throws Exception {
		String target = "/clientServiceTest/csrfTokenStorage.app";
		
    	String initialToken = "initialTestToken";
        getMockConfigAdapter().setCSRFToken(initialToken);
        
        open(target);

        String expectedToken = "errorToken";
        Throwable cause = new RuntimeException("intentional");
		RuntimeException expectedException = new InvalidSessionException(cause, expectedToken);
		getMockConfigAdapter().setValidateCSRFTokenException(expectedException );		
		
		// When the app reloads, overwrite the server-provided timestamp so that
		// the stored token will not get overwritten
		addFilter(INLINE.andThen((request, response, chain) -> {
	        CapturingResponseWrapper responseWrapper = new CapturingResponseWrapper((HttpServletResponse) response);
			chain.doFilter(request, responseWrapper);
			String responseString = responseWrapper.getCapturedResponseString();
			responseString = responseString.replaceFirst(",\"time\":(\\d+),", ",\"time\":-1,");
            servletUtilAdapter.setNoCache(response);
            response.setContentType(servletUtilAdapter.getContentType(Format.JS));
            response.setCharacterEncoding(AuraBaseServlet.UTF_ENCODING);
            response.getWriter().write(responseString);
		}));
		
        WebElement trigger = getDriver().findElement(By.className("trigger"));
        trigger.click();
		
        WebDriverWait wait = new WebDriverWait(getDriver(), getAuraUITestingUtil().getTimeout());
        wait.withMessage("token wasn't updated").until(
    		new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
					try {
						WebElement e = d.findElement(By.className("output"));
	                    return expectedToken.equals(e.getText());
					} catch (StaleElementReferenceException ignoreWhilePageReloading) {
						return false;
					}
                }
            }
        );

        String storedToken = getStoredToken();
		assertEquals(expectedToken, storedToken);
    }

	private String getStoredToken() {
        String script =
    		"var callback = arguments[arguments.length - 1];" +
    		"var key = '$AuraClientService.token$';" +
    		"$A.storageService.getStorage('actions').adapter.getItems([key]).then(" +
		    "  function(items){ callback(items[key] ? items[key].value.token : null) }," +
		    "  function(){ callback('Error retrieving from storage') }" +
    		")";
        getDriver().manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
        return (String) getAuraUITestingUtil().waitUntil((WebDriver driver) ->  
            ((JavascriptExecutor) driver).executeAsyncScript(script)
        );
	}
	
	private void waitForText(By locator, String expected) {
		WebElement element = getDriver().findElement(locator);
        waitForElementTextPresent(element, expected);
	}
	
	private HttpFilter addFilter(HttpFilter filter) {
		auraTestFilter.addFilter(filter);
		addTearDownStep(() -> auraTestFilter.removeFilter(filter));
		return filter;
	}
}
