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
        rules: {},
        zones: {"America/Martinique":[{"name":"America/Martinique","_offset":"-4:04:20","_rule":"-","format":"LMT","_until":"1890"},{"name":"America/Martinique","_offset":"-4:04:20","_rule":"-","format":"FFMT","_until":"1911 May"},{"name":"America/Martinique","_offset":"-4:00","_rule":"-","format":"AST","_until":"1980 Apr 6"},{"name":"America/Martinique","_offset":"-4:00","_rule":"1:00","format":"ADT","_until":"1980 Sep 28"},{"name":"America/Martinique","_offset":"-4:00","_rule":"-","format":"AST","_until":""}]}
    };
    window.WallTime.autoinit = true;
}).call(this);