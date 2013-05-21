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
        rules: {"Russia":[{"name":"Russia","_from":"1917","_to":"only","type":"-","in":"Jul","on":"1","at":"23:00","_save":"1:00","letter":"MST"},{"name":"Russia","_from":"1917","_to":"only","type":"-","in":"Dec","on":"28","at":"0:00","_save":"0","letter":"MMT"},{"name":"Russia","_from":"1918","_to":"only","type":"-","in":"May","on":"31","at":"22:00","_save":"2:00","letter":"MDST"},{"name":"Russia","_from":"1918","_to":"only","type":"-","in":"Sep","on":"16","at":"1:00","_save":"1:00","letter":"MST"},{"name":"Russia","_from":"1919","_to":"only","type":"-","in":"May","on":"31","at":"23:00","_save":"2:00","letter":"MDST"},{"name":"Russia","_from":"1919","_to":"only","type":"-","in":"Jul","on":"1","at":"2:00","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1919","_to":"only","type":"-","in":"Aug","on":"16","at":"0:00","_save":"0","letter":"-"},{"name":"Russia","_from":"1921","_to":"only","type":"-","in":"Feb","on":"14","at":"23:00","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1921","_to":"only","type":"-","in":"Mar","on":"20","at":"23:00","_save":"2:00","letter":"M"},{"name":"Russia","_from":"1921","_to":"only","type":"-","in":"Sep","on":"1","at":"0:00","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1921","_to":"only","type":"-","in":"Oct","on":"1","at":"0:00","_save":"0","letter":"-"},{"name":"Russia","_from":"1981","_to":"1984","type":"-","in":"Apr","on":"1","at":"0:00","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1981","_to":"1983","type":"-","in":"Oct","on":"1","at":"0:00","_save":"0","letter":"-"},{"name":"Russia","_from":"1984","_to":"1991","type":"-","in":"Sep","on":"lastSun","at":"2:00s","_save":"0","letter":"-"},{"name":"Russia","_from":"1985","_to":"1991","type":"-","in":"Mar","on":"lastSun","at":"2:00s","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1992","_to":"only","type":"-","in":"Mar","on":"lastSat","at":"23:00","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1992","_to":"only","type":"-","in":"Sep","on":"lastSat","at":"23:00","_save":"0","letter":"-"},{"name":"Russia","_from":"1993","_to":"2010","type":"-","in":"Mar","on":"lastSun","at":"2:00s","_save":"1:00","letter":"S"},{"name":"Russia","_from":"1993","_to":"1995","type":"-","in":"Sep","on":"lastSun","at":"2:00s","_save":"0","letter":"-"},{"name":"Russia","_from":"1996","_to":"2010","type":"-","in":"Oct","on":"lastSun","at":"2:00s","_save":"0","letter":"-"}]},
        zones: {"Europe/Moscow":[{"name":"Europe/Moscow","_offset":"2:30:20","_rule":"-","format":"LMT","_until":"1880"},{"name":"Europe/Moscow","_offset":"2:30","_rule":"-","format":"MMT","_until":"1916 Jul 3"},{"name":"Europe/Moscow","_offset":"2:30:48","_rule":"Russia","format":"%s","_until":"1919 Jul 1 2:00"},{"name":"Europe/Moscow","_offset":"3:00","_rule":"Russia","format":"MSK/MSD","_until":"1922 Oct"},{"name":"Europe/Moscow","_offset":"2:00","_rule":"-","format":"EET","_until":"1930 Jun 21"},{"name":"Europe/Moscow","_offset":"3:00","_rule":"Russia","format":"MSK/MSD","_until":"1991 Mar 31 2:00s"},{"name":"Europe/Moscow","_offset":"2:00","_rule":"Russia","format":"EE%sT","_until":"1992 Jan 19 2:00s"},{"name":"Europe/Moscow","_offset":"3:00","_rule":"Russia","format":"MSK/MSD","_until":"2011 Mar 27 2:00s"},{"name":"Europe/Moscow","_offset":"4:00","_rule":"-","format":"MSK","_until":""}]}
    };
    window.WallTime.autoinit = true;
}).call(this);