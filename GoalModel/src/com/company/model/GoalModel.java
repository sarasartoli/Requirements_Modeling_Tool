package com.company.model;

import com.company.jsonreader.JSONGoalMapper;
import com.company.model.attributes.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Created by E5-571 on 7/25/2016.
 * Json Parser feeds the goalModel to each Object
 */

public class GoalModel {

    private JSONObject initGoalModel;
    private JSONArray modelObjects;
    private JSONGoalMapper mapper = new JSONGoalMapper();

    private String[] nodes  = new String[] {"goal", "domainAgent", "actionTask", "domainContext"};
    private String[] links = new String[]{"goalOwner", "goalDecomposition","taskLink", "taskDecomposition"};

    private HashMap<String, String[]> modelMapper = new HashMap<>();

    private ArrayList<DomainAgent> domainAgentList = new ArrayList<>();
    private ArrayList<Goal> goalList = new ArrayList<>();
    private ArrayList<ActionTask> actionTaskList = new ArrayList<>();
    private ArrayList<DomainContext> contextList = new ArrayList<>();
    private ArrayList<GoalOwner> goalOwnerList = new ArrayList<>();
    private ArrayList<GoalDecomposition> goalDecompositionList = new ArrayList<>();
    private ArrayList<TaskLink> taskLinkLisk = new ArrayList<>();
    private ArrayList<TaskDecomposition> taskDecompositionList = new ArrayList<>();

    public GoalModel(JSONObject initGoalModel) {
        this.initGoalModel = initGoalModel;
        this.modelMapper.put("nodes", nodes);
        this.modelMapper.put("links", links);
        generateAllModelObjects(initGoalModel);
    }

    private void generateAllModelObjects(JSONObject initGoalModel) {
        this.modelMapper.forEach((k,v)->{
            if( initGoalModel.containsKey(k) ) {

                for (String val : v) {
                    System.out.println("Accessing "+ val);
                    this.modelObjects = (JSONArray) mapper.jsonObjectRetrieval(initGoalModel, k, val, -1, true);
                    generateModelObjects(this.modelObjects, val);

                    //String ves = taskDecomposition.getModelClassName();
                    //compare map array item value woth class to set each object
                    System.out.println("Done parsing " +val);

                }
            }
            else {
                System.out.println("GoalModel.generateAllModelObjects: error with key provided" );
            }
        });

    }

    private void generateModelObjects(JSONArray modelObjects, String newKey){
        for (int mO = 0; mO < modelObjects.size(); mO++) {
            getModelClassName(newKey, mO);
        }
    }

    public ArrayList<DomainAgent> getDomainAgentList() {
        return domainAgentList;
    }

    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    public ArrayList<ActionTask> getActionTaskList() {
        return actionTaskList;
    }

    public ArrayList<DomainContext> getContextList() {
        return contextList;
    }

    public ArrayList<GoalOwner> getGoalOwnerList() {
        return goalOwnerList;
    }

    public ArrayList<GoalDecomposition> getGoalDecompositionList() {
        return goalDecompositionList;
    }

    public ArrayList<TaskLink> getTaskLinkLisk() {
        return taskLinkLisk;
    }

    public ArrayList<TaskDecomposition> getTaskDecompositionList() {
        return taskDecompositionList;
    }

    private void getModelClassName(String key, int mO){
        DomainAgent domainAgent;
        Goal goal;
        ActionTask actionTask;
        DomainContext context;
        GoalOwner goalOwner;
        GoalDecomposition goalDecomposition;
        TaskLink taskLink;
        TaskDecomposition taskDecomposition;

        String s = key.substring(0, 1).toUpperCase() + key.substring(1);

        try {


            switch (s) {
                case "Goal":
                    Object object3 = Class.forName("com.company.model.attributes."+s).newInstance();
                    goal = (Goal) object3;
                    goal.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.goalList.add(goal);
                    break;

                case "DomainAgent":
                    Object object = Class.forName("com.company.model.attributes."+s).newInstance();
                    domainAgent = (DomainAgent) object;
                    domainAgent.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.domainAgentList.add(domainAgent);
                    break;

                case "ActionTask":
                    Object object1 = Class.forName("com.company.model.attributes."+s).newInstance();
                    actionTask = (ActionTask) object1;
                    actionTask.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.actionTaskList.add(actionTask);
                    break;

                case "DomainContext":
                    Object object2 = Class.forName("com.company.model.attributes."+s).newInstance();
                    context = (DomainContext) object2;
                    context.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.contextList.add(context);
                    break;

                case "GoalOwner":
                    Object object4 = Class.forName("com.company.model.attributes."+s).newInstance();
                    goalOwner = (GoalOwner) object4;
                    goalOwner.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.goalOwnerList.add(goalOwner);
                    break;

                case "GoalDecomposition":
                    Object object5 = Class.forName("com.company.model.attributes."+s).newInstance();
                    goalDecomposition = (GoalDecomposition) object5;
                    goalDecomposition.setModelObject((JSONObject) modelObjects.get(mO));
                    this.goalDecompositionList.add(goalDecomposition);
                    break;

                case "TaskLink":
                    Object object6 = Class.forName("com.company.model.attributes."+s).newInstance();
                    taskLink = (TaskLink) object6;
                    taskLink.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.taskLinkLisk.add(taskLink);
                    break;

                case "TaskDecomposition":
                    Object object8 = Class.forName("com.company.model.attributes."+s).newInstance();
                    taskDecomposition = (TaskDecomposition) object8;
                    taskDecomposition.setModelJSONObject((JSONObject) modelObjects.get(mO));
                    this.taskDecompositionList.add(taskDecomposition);
                    break;
                default:
                    break;
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
