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
    _assertErrors : function(expected, actual, msgPrefix) {
        $A.test.assertTrue(expected instanceof Array, msgPrefix + ": expected not an array");
        $A.test.assertTrue(actual instanceof Array, msgPrefix + ": actual not an array");
        $A.test.assertEquals(expected.length, actual.length, msgPrefix + ": unexpected number of errors");
        for (var i = 0; i < expected.length; i++) {
            $A.test.assertEquals(expected[i], actual[i], msgPrefix + ": unexpected error at position " + i);
        }
    },

    /**
     * Check getErrors after addErrors and clearErrors. Check against 2 different expressions to ensure no interactions
     * between expressions. Errors are currently intended for strings only, but use an arbitrary object anyways.
     */
    _testAddGetClearErrors : function(cmp, expr1, expr2) {
        var errors;

        // check initial getErrors
        errors = cmp.getErrors(expr1);
        this._assertErrors([], errors, "initial expr1 getErrors");

        errors = cmp.getErrors(expr2);
        this._assertErrors([], errors, "initial expr2 getErrors");

        // addErrors array
        var err10 = {
            "bad" : "move"
        };
        var err11 = {
            "wrong" : "decision"
        };
        cmp.addErrors(expr1, [ err10, err11 ]);

        // addErrors single error
        var err20 = {
            "wrong" : "path"
        };
        cmp.addErrors(expr2, err20);

        errors = cmp.getErrors(expr1);
        this._assertErrors([ err10, err11 ], errors, "expr1 getErrors");

        errors = cmp.getErrors(expr2);
        this._assertErrors([ err20 ], errors, "expr2 getErrors");

        // addErrors another single
        var err12 = {
            "poor" : "judgement"
        };
        cmp.addErrors(expr1, err12);

        // addErrors another array
        var err21 = {
            "missed" : "turn"
        };
        var err22 = {
            "skipped" : "exit"
        };
        cmp.addErrors(expr2, [ err21, err22 ]);

        errors = cmp.getErrors(expr1);
        this._assertErrors([ err10, err11, err12 ], errors, "expr1 getErrors after append");

        errors = cmp.getErrors(expr2);
        this._assertErrors([ err20, err21, err22 ], errors, "expr2 getErrors after append");

        // clearErrors
        cmp.clearErrors(expr1);
        errors = cmp.getErrors(expr1);
        this._assertErrors([], errors, "expr1 clearErrors");

        cmp.clearErrors(expr2);
        errors = cmp.getErrors(expr2);
        this._assertErrors([], errors, "expr2 clearErrors");
    },

    testAttributeNonExistent : {
        test : function(cmp) {
            this._testAddGetClearErrors(cmp, "v.anything", "v.anything.else");
        }
    },

    testAttribute : {
        test : function(cmp) {
            this._testAddGetClearErrors(cmp, "v.map", "v.map.trunk");
        }
    },

    testModelNonExistent : {
        test : [ function(cmp) {
            this._testAddGetClearErrors(cmp, "m.anything.else", "m.anything");
        } ]
    },

    testModel : {
        test : function(cmp) {
            cmp.set("m.map.leaf", "green");
            this._testAddGetClearErrors(cmp, "m.map", "m.map.leaf");
        }
    },

    testPassthrough : {
        test : [ function(cmp) {
            var ptvp = $A.expressionService.createPassthroughValue({
                "branch" : {
                    "leaf" : "green"
                }
            }, cmp);
            var config = {
                componentDef : "markup://aura:text",
                attributes : {
                    values : {
                        value : "{!v.branch}"
                    }
                }
            };
            var thiscmp = cmp;
            $A.componentService.newComponentAsync(this, function(newCmp) {
                thiscmp._createdCmp = newCmp;
            }, config, ptvp);
        }, function(cmp) {
            this._testAddGetClearErrors(cmp._createdCmp, "v.value", "v.value.branch");
        } ]
    },

    testFunction : {
        test : function(cmp) {
            var target = cmp.find("function");
            $A.test.assertEquals(true, target.getErrors("v.obj"));
            target.addErrors("v.obj", "won't get added");
            $A.test.assertEquals(true, target.getErrors("v.obj"));
            target.clearErrors("v.obj");
            $A.test.assertEquals(true, target.getErrors("v.obj"));
        }
    },

    testAction : {
        test : function(cmp) {
            var a = cmp.get("c.dummy");
            var ref = $A.expressionService.create(cmp, a.getDef());
            cmp.set("v.action", ref);
            $A.test.assertEquals(true, cmp.getErrors("v.action"));
            cmp.addErrors("v.action", "won't get added");
            $A.test.assertEquals(true, cmp.getErrors("v.action"));
            cmp.clearErrors("v.action");
            $A.test.assertEquals(true, cmp.getErrors("v.action"));
        }
    },

    testRerenderAfterAddErrors : {
        test : [ function(cmp) {
            cmp.find("simple").addErrors("v.obj", "first");
            $A.test.addWaitFor("simple:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        } ]
    },

    testRerenderAfterClearErrors : {
        test : [ function(cmp) {
            cmp.find("simple").addErrors("v.obj", "first");
            $A.test.addWaitFor("simple:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        }, function(cmp) {
            cmp.find("simple").clearErrors("v.obj");
            $A.test.addWaitFor("simple:2", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        } ]
    },

    testLocalizedRerenderAfterAddErrors : {
        test : [ function(cmp) {
            cmp.addErrors("m.map", "first");
            this._assertErrors([ "first" ], cmp.getErrors("m.map"), "root");
            this._assertErrors([ "first" ], cmp.find("model").getErrors("v.obj"), "child");
            // only root is rerendered, but not child referencing same value
            $A.test.addWaitFor("undefined:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        } ]
    },

    testRerenderAfterChildAddErrors : {
        test : [ function(cmp) {
            cmp.find("model").addErrors("v.obj", "first");
            this._assertErrors([ "first" ], cmp.getErrors("m.map"), "root");
            this._assertErrors([ "first" ], cmp.find("model").getErrors("v.obj"), "child");
            $A.test.addWaitFor("undefined:1model:1", function() {
                return $A.test.getText(cmp.find("children").getElement()).replace(/\s/g, "")
            })
        } ]
    },

    testLocalizedRerenderAfterClearErrors : {
        test : [ function(cmp) {
            cmp.addErrors("m.map", "first");
            this._assertErrors([ "first" ], cmp.getErrors("m.map"), "root after addErrors");
            this._assertErrors([ "first" ], cmp.find("model").getErrors("v.obj"), "child after addErrors");
            // only root is rerendered, but not child referencing same value
            $A.test.addWaitFor("undefined:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        }, function(cmp) {
            cmp.clearErrors("m.map");
            this._assertErrors([], cmp.getErrors("m.map"), "root after clearErrors");
            this._assertErrors([], cmp.find("model").getErrors("v.obj"), "child after clearErrors");
            // only root is rerendered, but not child referencing same value
            $A.test.addWaitFor("undefined:2", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        } ]
    },

    testLocalizedRerenderAfterChildClearErrors : {
        test : [ function(cmp) {
            cmp.addErrors("m.map", "first");
            this._assertErrors([ "first" ], cmp.getErrors("m.map"), "root after addErrors");
            this._assertErrors([ "first" ], cmp.find("model").getErrors("v.obj"), "child after addErrors");
            // only root is rerendered, but not child referencing same value
            $A.test.addWaitFor("undefined:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        }, function(cmp) {
            cmp.find("model").clearErrors("v.obj");
            this._assertErrors([], cmp.getErrors("m.map"), "root after clearErrors");
            this._assertErrors([], cmp.find("model").getErrors("v.obj"), "child after clearErrors");
            $A.test.addWaitFor("undefined:2model:1", function() {
                return $A.test.getText(cmp.find("children").getElement()).replace(/\s/g, "")
            })
        } ]
    },

    testSetValid : {
        test : [ function(cmp) {
            $A.test.assertEquals(true, cmp.isValid("v.obj"));
            this._assertErrors([], cmp.getErrors("v.obj"));

            // validating valid cmp is essentially no-op
            cmp.setValid("v.obj", true);
            $A.test.assertEquals(true, cmp.isValid("v.obj"));
            this._assertErrors([], cmp.getErrors("v.obj"));
        }, function(cmp) {
            $A.test.assertEquals("", $A.test.getText(cmp.find("children").getElement()));
            
            // add an error to invalidate
            cmp.addErrors("v.obj", "some error");
            $A.test.assertEquals(false, cmp.isValid("v.obj"));
            this._assertErrors([ "some error" ], cmp.getErrors("v.obj"));
            $A.test.addWaitFor("undefined:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        }, function(cmp) {
            // validating cmp clears errors
            cmp.setValid("v.obj", true);
            $A.test.assertEquals(true, cmp.isValid("v.obj"));
            this._assertErrors([], cmp.getErrors("v.obj"));
            $A.test.addWaitFor("undefined:2", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        } ]
    },

    testSetInvalid : {
        test : [ function(cmp) {
            $A.test.assertEquals(true, cmp.isValid("v.obj"));
            this._assertErrors([], cmp.getErrors("v.obj"));

            // invalidating cmp doesn't add a material error
            cmp.setValid("v.obj", false);
            $A.test.assertEquals(false, cmp.isValid("v.obj"));
            this._assertErrors([], cmp.getErrors("v.obj"));
            $A.test.addWaitFor("undefined:1", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        }, function(cmp) {
            // adding an actual error
            cmp.addErrors("v.obj", "actual error");
            $A.test.assertEquals(false, cmp.isValid("v.obj"));
            this._assertErrors([ "actual error" ], cmp.getErrors("v.obj"));
            $A.test.addWaitFor("undefined:2", function() {
                return $A.test.getText(cmp.find("children").getElement())
            })
        }, function(cmp) {
            // setting invalid cmp invalid again is essentially no-op
            cmp.setValid("v.obj", false);
            $A.test.assertEquals(false, cmp.isValid("v.obj"));
            this._assertErrors([ "actual error" ], cmp.getErrors("v.obj"));
        }, function(cmp) {
            // to check that last setValid did not trigger rerender, rerender something else
            cmp.find("simple").setValid("v.obj", false);
            $A.test.addWaitFor("undefined:2simple:1", function() {
                return $A.test.getText(cmp.find("children").getElement()).replace(/\s/g, "");
            })
        } ]
    },

    testClearInvalidValueProvider : {
        test : [
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression 'invalid.something'. No value provider was found for 'invalid'. : undefined");
                    cmp.clearErrors("invalid.something");
                },
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression ''. No value provider was found for ''. : undefined");
                    cmp.clearErrors("");
                },
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression '$Browser.isPhone'. No value provider was found for '$Browser'. : undefined");
                    cmp.clearErrors("$Browser.isPhone");
                }, function(cmp) {
                    $A.test.expectAuraError("Value provider 'c' doesn't implement isValid(). : undefined");
                    cmp.clearErrors("c.something");
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression 'undefined'. : false");
                    // cmp.clearErrors();
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression 'null'. : false");
                    // cmp.clearErrors(null);
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression '[object Object]'. : false");
                    // cmp.clearErrors({});
                } ]
    },

    testAddInvalidValueProvider : {
        test : [
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression 'invalid.something'. No value provider was found for 'invalid'. : undefined");
                    cmp.addErrors("invalid.something", "irrelevant");
                },
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression ''. No value provider was found for ''. : undefined");
                    cmp.addErrors("", "irrelevant");
                },
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression '$Browser.isPhone'. No value provider was found for '$Browser'. : undefined");
                    cmp.addErrors("$Browser.isPhone", "irrelevant");
                }, function(cmp) {
                    $A.test.expectAuraError("Value provider 'c' doesn't implement addErrors(). : undefined");
                    cmp.addErrors("c.something", "irrelevant");
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression 'undefined'. : false");
                    // cmp.addErrors(undefined, "irrelevant");
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression 'null'. : false");
                    // cmp.addErrors(null, "irrelevant");
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression '[object Object]'. : false");
                    // cmp.addErrors({}, "irrelevant");
                } ]
    },

    testGetInvalidValueProvider : {
        test : [
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression 'invalid.something'. No value provider was found for 'invalid'. : undefined");
                    cmp.getErrors("invalid.something");
                },
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression ''. No value provider was found for ''. : undefined");
                    cmp.getErrors("");
                },
                function(cmp) {
                    $A.test
                            .expectAuraError("Unable to get value for expression '$Browser.isPhone'. No value provider was found for '$Browser'. : undefined");
                    cmp.getErrors("$Browser.isPhone");
                }, function(cmp) {
                    $A.test.expectAuraError("Value provider 'c' doesn't implement getErrors(). : undefined");
                    cmp.getErrors("c.something");
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression 'undefined'. : false");
                    // cmp.getErrors();
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression 'null'. : false");
                    // cmp.getErrors(null);
                    // },function(cmp){
                    // $A.test.expectAuraError("Invalid expression '[object Object]'. : false");
                    // cmp.getErrors({});
                } ]
    }
})
