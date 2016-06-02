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
    formatValue: function(component) {
        var value = component.get("v.value"),
            displayValue = value,
            inputElement = component.find("inputDateTimeHtml").getElement();

        if (value && inputElement) {
            var isoDate = $A.localizationService.parseDateTimeISO8601(value);
            var timezone = component.get("v.timezone");

            $A.localizationService.UTCToWallTime(isoDate, timezone, function(walltime) {
                walltime = $A.localizationService.translateToOtherCalendar(walltime);
                var walltimeISO = $A.localizationService.toISOString(walltime);

                // datetime-local input doesn't support any time zone offset information,
                // so we need to remove the 'Z' off of the end.
                displayValue = walltimeISO.split("Z", 1)[0] || walltimeISO;
                inputElement.value = displayValue;
            });
        }
    },

    /**
     * Override
     */
    doUpdate : function (component, value) {
        var timezone = component.get("v.timezone"),
            helper = this,
            isoDate;

        if ($A.util.isEmpty(component.get("v.value"))) {
            component._isInitialValue = true;
            isoDate = $A.localizationService.parseDateTime(value);

            // first convert the date/time to the user's SFDC timezone, because mobile always defaults to the local timezone
            $A.localizationService.UTCToWallTime(isoDate, timezone, function (walltime) {
                helper.setValue(component, walltime, timezone);
            });
        } else {
            isoDate = $A.localizationService.parseDateTimeUTC(value);
            helper.setValue(component, isoDate, timezone);
        }

    },

    setValue : function (component, walltime, timezone) {
        $A.localizationService.WallTimeToUTC(walltime, timezone, function (utcDate) {
            utcDate = $A.localizationService.translateFromOtherCalendar(utcDate);
            component.set("v.value", $A.localizationService.toISOString(utcDate));
        });
    }
});
