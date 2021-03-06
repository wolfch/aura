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
/**
 */
package org.auraframework.docs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.auraframework.annotations.Annotations.ServiceComponentModelInstance;
import org.auraframework.ds.servicecomponent.ModelInstance;
import org.auraframework.system.Annotations.AuraEnabled;

/**
 * for iterationTest components
 *
 * @since
 */
@ServiceComponentModelInstance
public class TableModel implements ModelInstance {

    private final List<Object> data;

    public TableModel() {
        data = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            Map<String, Object> dora = new HashMap<>();
            char c = (char)('a' + i);
            dora.put("stringy", "" + c + c + c);
            dora.put("whatever", "hooray for everybody");
            data.add(dora);
            dora = new HashMap<>();
            c = (char)('A' + i);
            dora.put("stringy", "" + c + c + c + c + c);
            dora.put("whatever", "boo for nobody");
        }
    }

    @AuraEnabled
    public List<Object> getData() {
        return data;
    }

    @AuraEnabled
    public String getDerp() {
        return "DERP";
    }

    @AuraEnabled
    public List<List<String>> getPlayerData() {
        List<List<String>> team = new ArrayList<>();
        String[][] s = { { "Héctor Sánchez", "23", "Catcher", "2012" },
                { "Buster Posey", "25", "Catcher/First Baseman", "2012,2010" },
                { "Brandon Belt", " 24", "First baseman", "2012" },
                { "Aubrey Huff", "35", "First Basemen", "2012,2010" },
                { "Pablo Sandavol", "26", "Third baseman", "2012,2010" },
                { "Marco Scutaro", "37", "Second baseman", "2012" }, { "Ángel Pagán", "31", "Outfielder", "2012" },
                { "Hunter Pence", "29", "Outfielder", "2012" }, { "Barry Zito", "34", "Starting Pitcher", "2012" },
                { "Brian Wilson", "30", "Closing Pitcher", "2010" },
                { "Ryan Vogelsong", "35", "Starting Pitcher", "2012" },
                { "Sergio Romo", "29", "Closing Pitcher", "2012,2010" },
                { "Guillermo Mota", "39", "Relief Pitcher", "2012,2010" },
                { "Tim Lincecum", "28", "Starting Pitcher", "2012,2010" },
                { "Santiago Casilla", "32", "Relief Pitcher", "2012,2010" },
                { "Matt Cain", "28", "Starting Pitcher", "2012,2010" },
                { "Madison Bumgarner", "23", "Starting Pitcher", "2012,2010" },
                { "Brandon Crawford", "25", "Short Stop", "2012" }, { "Melky Cabrera", "28", "OutFielder", "2009" },
                { "Ryan Theriot", "32", "Short Stop", "2011, 2012" }, };
        for (String[] list : s) {
            ArrayList<String> player = new ArrayList<>();
            for (String field : list) {
                player.add(field);
            }
            team.add(player);
        }
        return team;
    }

}
