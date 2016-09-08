package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class DomainContext extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String id;
    private String name;
    private boolean value;
    private ArrayList<String[]> definition = new ArrayList<>();

    public DomainContext(String id) {
        this.id = id;
    }

    public DomainContext() {
    }

    public void setModelJSONObject(JSONObject modelJSONObject) {
        this.modelJSONObject = modelJSONObject;
        this.id  = stringToLowercase((String) jsonObjectRetrieval(modelJSONObject, "id"));

        //Loop the definition json array
        JSONArray defn = (JSONArray) jsonObjectRetrieval(modelJSONObject, "definition");
        for (int d = 0; d < defn.size(); d++) {
            this.name = ((String) jsonObjectRetrieval(defn, d, "name"));
            this.value = ((boolean) jsonObjectRetrieval(defn, d, "value"));

            this.definition.add(
                    new String[]{this.name, String.valueOf(value)});
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public ArrayList<String[]> getDefinition() {
        return definition;
    }

    public String getClassName() {
        return "context";
    }
}
