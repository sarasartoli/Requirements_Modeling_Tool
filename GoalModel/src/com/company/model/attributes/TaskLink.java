package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class TaskLink extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String sourceGoalID;
    private String targetTaskID;
    private String contextID;
    private ArrayList<String[]> targetList = new ArrayList<>();



    public void setModelJSONObject(JSONObject tlJDONObject) {
        this.modelJSONObject = tlJDONObject;

        this.sourceGoalID = stringToLowercase(
                removeNullValue( getDefaultGoalValue(),
                        (String) jsonObjectRetrieval(tlJDONObject, "source")));

        //JSONObject target = (JSONObject) jsonObjectRetrieval(tlJDONObject, "target");

        JSONArray target = (JSONArray) jsonObjectRetrieval(tlJDONObject, "target");
        for (int t = 0; t < target.size(); t++) {

            this.targetTaskID = stringToLowercase(
                    removeNullValue( getDefaultGoalValue(),
                            (String) jsonObjectRetrieval(target, t, "task")));

            this.contextID = stringToLowercase(
                    removeNullValue( getDefaultContextValue(),
                            (String) jsonObjectRetrieval(target, t, "context")));

            this.targetList.add(
                    new String[]{this.targetTaskID, this.contextID});
        }
    }

    public String getSourceGoalID() {
        return sourceGoalID;
    }

    public ArrayList<String[]> getTargetList() {
        return targetList;
    }

    public String getClassName() {
        return "taskLinks";
    }
}
