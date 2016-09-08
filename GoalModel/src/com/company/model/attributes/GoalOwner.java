package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONObject;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class GoalOwner extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String goal;
    private String agent;


    public GoalOwner(String goal, String agent) {
        this.goal = goal;
        this.agent = agent;
    }

    public GoalOwner() {
    }

    public void setModelJSONObject(JSONObject goJSONObject) {
        this.modelJSONObject = goJSONObject;
        this.goal = stringToLowercase(
                removeNullValue( getDefaultGoalValue(),
                        (String) jsonObjectRetrieval(goJSONObject, "goal")));
        this.agent = stringToLowercase((String) jsonObjectRetrieval(goJSONObject, "agent"));
    }

    public String getGoal() {
        return goal;
    }

    public String getAgent() {
        return agent;
    }

    public String getClassName() {
        return "goalOwner";
    }
}
