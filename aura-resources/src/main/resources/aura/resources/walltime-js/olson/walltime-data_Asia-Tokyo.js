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
        rules: {"Japan":[{"name":"Japan","_from":"1948","_to":"only","type":"-","in":"May","on":"Sun>=1","at":"2:00","_save":"1:00","letter":"D"},{"name":"Japan","_from":"1948","_to":"1951","type":"-","in":"Sep","on":"Sat>=8","at":"2:00","_save":"0","letter":"S"},{"name":"Japan","_from":"1949","_to":"only","type":"-","in":"Apr","on":"Sun>=1","at":"2:00","_save":"1:00","letter":"D"},{"name":"Japan","_from":"1950","_to":"1951","type":"-","in":"May","on":"Sun>=1","at":"2:00","_save":"1:00","letter":"D"}]},
        zones: {"Asia/Tokyo":[{"name":"Asia/Tokyo","_offset":"9:18:59","_rule":"-","format":"LMT","_until":"1887 Dec 31 15:00u"},{"name":"Asia/Tokyo","_offset":"9:00","_rule":"-","format":"JST","_until":"1896"},{"name":"Asia/Tokyo","_offset":"9:00","_rule":"-","format":"CJT","_until":"1938"},{"name":"Asia/Tokyo","_offset":"9:00","_rule":"Japan","format":"J%sT","_until":""}]}
    };
    window.WallTime.autoinit = true;
}).call(this);