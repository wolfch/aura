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
(function() {
    window.WallTime || (window.WallTime = {});
    window.WallTime.data = {
        rules: {"Regina":[{"name":"Regina","_from":"1918","_to":"only","type":"-","in":"Apr","on":"14","at":"2:00","_save":"1:00","letter":"D"},{"name":"Regina","_from":"1918","_to":"only","type":"-","in":"Oct","on":"27","at":"2:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1930","_to":"1934","type":"-","in":"May","on":"Sun>=1","at":"0:00","_save":"1:00","letter":"D"},{"name":"Regina","_from":"1930","_to":"1934","type":"-","in":"Oct","on":"Sun>=1","at":"0:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1937","_to":"1941","type":"-","in":"Apr","on":"Sun>=8","at":"0:00","_save":"1:00","letter":"D"},{"name":"Regina","_from":"1937","_to":"only","type":"-","in":"Oct","on":"Sun>=8","at":"0:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1938","_to":"only","type":"-","in":"Oct","on":"Sun>=1","at":"0:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1939","_to":"1941","type":"-","in":"Oct","on":"Sun>=8","at":"0:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1942","_to":"only","type":"-","in":"Feb","on":"9","at":"2:00","_save":"1:00","letter":"W"},{"name":"Regina","_from":"1945","_to":"only","type":"-","in":"Aug","on":"14","at":"23:00u","_save":"1:00","letter":"P"},{"name":"Regina","_from":"1945","_to":"only","type":"-","in":"Sep","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1946","_to":"only","type":"-","in":"Apr","on":"Sun>=8","at":"2:00","_save":"1:00","letter":"D"},{"name":"Regina","_from":"1946","_to":"only","type":"-","in":"Oct","on":"Sun>=8","at":"2:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1947","_to":"1957","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"Regina","_from":"1947","_to":"1957","type":"-","in":"Sep","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"Regina","_from":"1959","_to":"only","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"Regina","_from":"1959","_to":"only","type":"-","in":"Oct","on":"lastSun","at":"2:00","_save":"0","letter":"S"}]},
        zones: {"America/Regina":[{"name":"America/Regina","_offset":"-6:58:36","_rule":"-","format":"LMT","_until":"1905 Sep"},{"name":"America/Regina","_offset":"-7:00","_rule":"Regina","format":"M%sT","_until":"1960 Apr lastSun 2:00"},{"name":"America/Regina","_offset":"-6:00","_rule":"-","format":"CST","_until":""}]}
    };
    window.WallTime.autoinit = true;
}).call(this);