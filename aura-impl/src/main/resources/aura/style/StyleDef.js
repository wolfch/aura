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
 * @description Creates a new StyleDef instance, including the class name and descriptor.
 * @constructor
 * @param {Object} config
 */
function StyleDef(config) {
    this.code = config["code"];
    this.className = config["className"];
    this.descriptor = new DefDescriptor(config["descriptor"]);
    this.asyncLoaded = !$A.util.isUndefinedOrNull(this.code); // based on logic in StyleDefImpl serialization
}

StyleDef.prototype.auraType = "StyleDef";

/**
 * Applies style to element. If this StyleDef's style has not been added to the DOM, add it to the DOM.
 */
StyleDef.prototype.apply = function() {
    var element = this.element;
    var code = this.code;
    if (!element && code) {
        element = aura.util.style.apply(code);
        this.element = element;
    }
    delete this.code;
};

StyleDef.prototype.remove = function() {
    //TODO
};

/**
 * Gets the descriptor. (e.g. markup://foo:bar)
 * @returns {DefDescriptor}
 */
StyleDef.prototype.getDescriptor = function() {
    return this.descriptor;
};

/**
 * Gets class name from the style definition.
 * @param {Object} className
 *
 */
StyleDef.prototype.getClassName = function() {
    return this.className;
};

/**
 * Gets whether this def was async loaded from the server, as opposed to preloaded in app.css.
 */
StyleDef.prototype.wasAsyncLoaded = function() {
    return this.asyncLoaded;
};
