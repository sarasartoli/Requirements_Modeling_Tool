package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class GoalDecomposition extends JSONGoalMapper {
    private JSONObject modelObject;
    private String sourceGoalID;
    private String decType;
    private String targetGoalID;
    private String contextID;
    private ArrayList<String[]> targetList = new ArrayList<>();

    public void setModelObject(JSONObject gdJSONObject) {
        this.modelObject = gdJSONObject;
        this.sourceGoalID = ((String) jsonObjectRetrieval(gdJSONObject, "source"));
        this.decType =((String) jsonObjectRetrieval(gdJSONObject, "decompositionType"));

        //Loop the  target json array
        JSONArray target = (JSONArray) jsonObjectRetrieval(gdJSONObject, "target");
        for (int t = 0; t < target.size(); t++){
            this.targetGoalID = ((String) jsonObjectRetrieval(target,t,"goal"));
            this.contextID = ((String) jsonObjectRetrieval(target,t, "context"));

            targetList.add(
                    new String[]{this.targetGoalID, this.contextID});
            this.contextID = null;
            this.targetGoalID = null;

        }

    }

    public String getSourceGoalID() {
        return sourceGoalID;
    }

    public String getDecType() {
        return decType;
    }

    public ArrayList<String[]> getTargetList() {
        return targetList;
    }

    public String getClassName() {
        return "goalDecomposition";
    }
}
