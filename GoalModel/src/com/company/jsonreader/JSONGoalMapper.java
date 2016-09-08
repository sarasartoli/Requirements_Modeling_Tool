package com.company.jsonreader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Collections;

/**
 * Created by E5-571 on 7/28/2016.
 */
public class JSONGoalMapper {

    private String defaultContextValue = "noContext";
    private String defaultGoalValue = "noGoal";
    private String defaultTaskValue = "noTask";

    public String getDefaultContextValue() {
        return defaultContextValue;
    }

    public String getDefaultGoalValue() {
        return defaultGoalValue;
    }

    public String getDefaultTaskValue() {
        return defaultTaskValue;
    }

    protected Object jsonObjectRetrieval (JSONObject object, String key){
        return object.get(key);
    }

    //get root and subsequent json object
    public Object jsonObjectRetrieval (Object object, String key1, String key2, int arrayIndex, boolean rootNode){
        //root json object that should contain key 2 for the immediate child object
        if (rootNode && arrayIndex == -1 && !key2.equals("")){
            JSONObject object1 = (JSONObject) jsonObjectRetrieval((JSONObject) object,key1);
            if(object1.containsKey(key2)){
                return (JSONArray) jsonObjectRetrieval(object1, key2);
            }
            else {
                System.out.println("error with child node key provided");
                return null;}
        }
        else {
            //threat first object as a json array and overload method
            return jsonObjectRetrieval((JSONArray) object, arrayIndex, key1);
        }
    }


    protected Object jsonObjectRetrieval (JSONArray object, int arrayIndex, String key ){
        JSONObject object1 = (JSONObject)object.get(arrayIndex);
        return object1.get(key);
    }

    protected String stringToLowercase(String value) {
        if (value != null && !value.isEmpty()) {
            String truck = value.substring(0,1).toLowerCase();
            value = truck.concat(value.substring(1));
        }
        return value;
    }

    protected String changeDecompositon(String value) {
        if (value != null && !value.isEmpty()) {
            switch (value){
                case "and":
                    value = "andDec";
                    break;
                case "or":
                    value = "orDec";
                    break;
                case "":
                    value = "noDec";
                    break;
                default:
                    break;

            }
        }
        return value;
    }

    /**
     * Remove null values from the code and replace with default value
     * @param oldValue
     * @param newValue
     * @return
     */
    protected String removeNullValue(String newValue, String oldValue){
        if (oldValue != null && !oldValue.isEmpty()) {
            return oldValue;
        }
        else{
            return newValue;
        }
    }

}
