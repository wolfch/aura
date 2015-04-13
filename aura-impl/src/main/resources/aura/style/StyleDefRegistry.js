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
/*jslint sub: true */
/**
 * @description A registry for StyleDefs.
 * @constructor
 */
function StyleDefRegistry() {
    this.styleDefs = {};
    this.asyncLoadedStyleDefs = [];
}

StyleDefRegistry.prototype.auraType = "StyleDefRegistry";

/**
 * Returns a StyleDef instance.
 * Throws an error if config is not provided.
 * @param {Object} config The config object for the StyleDef.
 * @returns {StyleDef} A StyleDef instance.
 */
StyleDefRegistry.prototype.getDef = function(config) {
    aura.assert(config, "StyleDefRegistry config required for registration");

    // todo -- make a proper registry and cache?
    var def = new StyleDef(config);
    if (def.wasAsyncLoaded()) {
        this.asyncLoadedStyleDefs.push(def.getDescriptor().toString());
    }
    return def;
};

/**
 * Returns a list of style defs that were loaded async (i.e., not from preloads).
 * @returns {Array} List of def descriptor strings.
 */
StyleDefRegistry.prototype.getAsyncLoadedStyleDefs = function() {
    return this.asyncLoadedStyleDefs;
};
