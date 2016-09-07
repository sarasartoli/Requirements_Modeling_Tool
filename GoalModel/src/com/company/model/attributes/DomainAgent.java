package com.company.model.attributes;

import com.company.jsonreader.JSONGoalMapper;
import org.json.simple.JSONObject;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class DomainAgent extends JSONGoalMapper {
    private JSONObject modelJSONObject;
    private String id;
    private String name;
    private String agentType;

    public DomainAgent(String id) {
        this.id = id;
    }

    public DomainAgent() {
    }

    public void setModelJSONObject(JSONObject modelJSONObject) {
        this.modelJSONObject = modelJSONObject;
        
        this.id = (String) jsonObjectRetrieval(modelJSONObject, "id");
        this.name = (String) jsonObjectRetrieval(modelJSONObject, "name");
        this.agentType = (String) jsonObjectRetrieval(modelJSONObject, "type");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAgentType() {
        return agentType;
    }

    public String getClassName() {
        return "agent";
    }}
