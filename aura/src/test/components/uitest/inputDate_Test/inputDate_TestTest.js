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
 ({
 	/**
 	 * Opening date picker with no value set will open datePicker to todays date.
 	 */
 	testDatePickerOpensToToday : {
 		test : function(cmp) {
 			var today = new Date();
 			var expectedDay = today.getDate();
      		var expectedMonthYear = this.convertMonth(today.getMonth()) + " " + today.getFullYear();
      		
      		this.openDatePicker(cmp);
      		var curDate = $A.test.getActiveElement();
      		$A.test.assertEquals(expectedDay.toString(), $A.util.getText(curDate), "Date picker did not open to todays day");
      		
      		var title = cmp.find("datePickerTestCmp").find("datePicker").find("calTitle");
      		var titleText = $A.util.getText(title.getElement());
      		$A.test.assertEquals(expectedMonthYear, titleText, "Date picker did not open to todays month and year");
 		}
 	},
 	
 	/**
 	 * Clicking on a date on the datePicker will select the date and close the calendar.
 	 */
	testClickonDayWorks : { 
		attributes : {value : "2013-09-25"},
  		test : [function(cmp) {
      		this.openDatePicker(cmp);
  		}, function(cmp) {
  			var datePicker = cmp.find("datePickerTestCmp").find("datePicker").getElement();
      		var curDate = $A.test.getActiveElement();
      		var tbody = curDate.parentNode.parentNode.parentNode;
      		var aboveCurrentDate = tbody.children[2].children[3].children[0];
      		aboveCurrentDate.click();
      		$A.test.addWaitFor(false, function(){return $A.util.hasClass(datePicker, "visible")});
  		}, function(cmp) {
  			var dateValue = cmp.find("datePickerTestCmp").find("inputText").getElement().value;
  			$A.test.assertEquals("2013-09-18", dateValue.toString(), "Clicking on one week prior to todays date did not render the correct result.");
		}]
    },
	
	openDatePicker : function(cmp) {
    	var opener = cmp.find("datePickerTestCmp").find("datePickerOpener").getElement();
		var inputBox = cmp.find("datePickerTestCmp").find("inputText").getElement();
	    var datePicker = cmp.find("datePickerTestCmp").find("datePicker").getElement();
	    if($A.util.isUndefinedOrNull(opener)) {
	    	inputBox.click();
		} else {
			opener.click();
		}
		$A.test.addWaitFor(true, function(){return $A.util.hasClass(datePicker, "visible")});
    },
    
    convertMonth : function(intMonth) {
    	if (!intMonth) {
    		return intMonth;
    	}
    	
    	if (intMonth == 0) {
    		return "January";
    	} else if (intMonth == 1) {
    		return "February";
    	} else if (intMonth == 2) {
    		return "March";
    	} else if (intMonth == 3) {
    		return "April";
    	} else if (intMonth == 4) {
    		return "May";
    	} else if (intMonth == 5) {
    		return "June";
    	} else if (intMonth == 6) {
    		return "July";
    	} else if (intMonth == 7) {
    		return "August";
    	} else if (intMonth == 8) {
    		return "September";
    	} else if (intMonth == 9) {
    		return "October";
    	} else if (intMonth == 10) {
    		return "November";
    	} else if (intMonth == 11) {
    		return "December";
    	}
    }
})