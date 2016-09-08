package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONObject;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class Goal extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String id;
    private String name;
    private boolean rootGoal;

    public Goal(String id) {
        this.id = id;
    }

    public Goal() {

    }

    public boolean isRootGoal() {
        if(this.rootGoal == true)  return true;
        else return false;
    }

    public void setModelJSONObject(JSONObject modelJSONObject) {
        this.modelJSONObject = modelJSONObject;
        this.id = stringToLowercase((String) jsonObjectRetrieval(modelJSONObject,"id"));
        this.name = ((String) jsonObjectRetrieval(modelJSONObject,"name"));
        this.rootGoal = ((boolean) jsonObjectRetrieval(modelJSONObject,"rootGoal"));
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
