package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class TaskDecomposition extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String sourceTaskID;
    private String decType;
    private String targetTaskID;
    private String contextID;
    private ArrayList<String[]> targetList = new ArrayList<>();

    public void setModelJSONObject(JSONObject modelJSONObject) {
        this.modelJSONObject = modelJSONObject;
        this.sourceTaskID =((String) jsonObjectRetrieval(modelJSONObject,"source"));
        this.decType =((String) jsonObjectRetrieval(modelJSONObject,"decompositionType"));

        JSONArray target = (JSONArray) jsonObjectRetrieval(modelJSONObject, "target");
        if (!target.isEmpty() && target != null){
            for (int t = 0; t < target.size(); t++) {
                this.targetTaskID = ((String) jsonObjectRetrieval(target, t, "task"));
                this.contextID = ((String) jsonObjectRetrieval(target, t, "context"));

                this.targetList.add(
                        new String[]{this.targetTaskID, this.contextID});
            }
        }
    }

    public String getSourceTaskID() {
        return sourceTaskID;
    }

    public String getDecType() {
        return decType;
    }

    public ArrayList<String[]> getTargetList() {
        return targetList;
    }

    public String getClassName() {
        return "taskDecomposition";
    }
}
