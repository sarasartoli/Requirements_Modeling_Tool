package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONObject;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class ActionTask extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String id;
    private String name;

    public ActionTask(String id) {
        this.id = id;
    }

    public ActionTask(JSONObject taskJSONObject) {
        this.modelJSONObject = taskJSONObject;
    }

    public ActionTask() {

    }

    public void setModelJSONObject(JSONObject modelJSONObject) {
        this.modelJSONObject = modelJSONObject;
        this.id = (String) jsonObjectRetrieval(modelJSONObject, "id");
        this.name = (String) jsonObjectRetrieval(modelJSONObject, "name");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return "task";
    }
}
