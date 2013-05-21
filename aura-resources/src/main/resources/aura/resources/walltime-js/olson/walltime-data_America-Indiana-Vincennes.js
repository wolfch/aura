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
        rules: {"US":[{"name":"US","_from":"1918","_to":"1919","type":"-","in":"Mar","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"1918","_to":"1919","type":"-","in":"Oct","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"US","_from":"1942","_to":"only","type":"-","in":"Feb","on":"9","at":"2:00","_save":"1:00","letter":"W"},{"name":"US","_from":"1945","_to":"only","type":"-","in":"Aug","on":"14","at":"23:00u","_save":"1:00","letter":"P"},{"name":"US","_from":"1945","_to":"only","type":"-","in":"Sep","on":"30","at":"2:00","_save":"0","letter":"S"},{"name":"US","_from":"1967","_to":"2006","type":"-","in":"Oct","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"US","_from":"1967","_to":"1973","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"1974","_to":"only","type":"-","in":"Jan","on":"6","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"1975","_to":"only","type":"-","in":"Feb","on":"23","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"1976","_to":"1986","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"1987","_to":"2006","type":"-","in":"Apr","on":"Sun>=1","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"2007","_to":"max","type":"-","in":"Mar","on":"Sun>=8","at":"2:00","_save":"1:00","letter":"D"},{"name":"US","_from":"2007","_to":"max","type":"-","in":"Nov","on":"Sun>=1","at":"2:00","_save":"0","letter":"S"}],"Vincennes":[{"name":"Vincennes","_from":"1946","_to":"only","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"Vincennes","_from":"1946","_to":"only","type":"-","in":"Sep","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"Vincennes","_from":"1953","_to":"1954","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"Vincennes","_from":"1953","_to":"1959","type":"-","in":"Sep","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"Vincennes","_from":"1955","_to":"only","type":"-","in":"May","on":"1","at":"0:00","_save":"1:00","letter":"D"},{"name":"Vincennes","_from":"1956","_to":"1963","type":"-","in":"Apr","on":"lastSun","at":"2:00","_save":"1:00","letter":"D"},{"name":"Vincennes","_from":"1960","_to":"only","type":"-","in":"Oct","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"Vincennes","_from":"1961","_to":"only","type":"-","in":"Sep","on":"lastSun","at":"2:00","_save":"0","letter":"S"},{"name":"Vincennes","_from":"1962","_to":"1963","type":"-","in":"Oct","on":"lastSun","at":"2:00","_save":"0","letter":"S"}]},
        zones: {"America/Indiana/Vincennes":[{"name":"America/Indiana/Vincennes","_offset":"-5:50:07","_rule":"-","format":"LMT","_until":"1883 Nov 18 12:09:53"},{"name":"America/Indiana/Vincennes","_offset":"-6:00","_rule":"US","format":"C%sT","_until":"1946"},{"name":"America/Indiana/Vincennes","_offset":"-6:00","_rule":"Vincennes","format":"C%sT","_until":"1964 Apr 26 2:00"},{"name":"America/Indiana/Vincennes","_offset":"-5:00","_rule":"-","format":"EST","_until":"1969"},{"name":"America/Indiana/Vincennes","_offset":"-5:00","_rule":"US","format":"E%sT","_until":"1971"},{"name":"America/Indiana/Vincennes","_offset":"-5:00","_rule":"-","format":"EST","_until":"2006 Apr 2 2:00"},{"name":"America/Indiana/Vincennes","_offset":"-6:00","_rule":"US","format":"C%sT","_until":"2007 Nov 4 2:00"},{"name":"America/Indiana/Vincennes","_offset":"-5:00","_rule":"US","format":"E%sT","_until":""}]}
    };
    window.WallTime.autoinit = true;
}).call(this);