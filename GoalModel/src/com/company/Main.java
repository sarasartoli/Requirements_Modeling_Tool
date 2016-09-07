package com.company;

import com.company.aspwriter.ASPGoalFileWriter;
import com.company.jsonreader.JSONGoalFileReader;
import com.company.model.GoalModel;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File jFile = new File("/Users/sarasartoli/Desktop/workspace/GoalModel/data/json/goalsample1.json");
        JSONGoalFileReader jsonFile = new JSONGoalFileReader(jFile);

        try {

            GoalModel goalModel = new GoalModel(jsonFile.parseFile());
            System.out.println(goalModel.getActionTaskList().get(1).getId());

            ASPGoalFileWriter aspfile = new ASPGoalFileWriter();
            aspfile.writeToASP(goalModel, jsonFile.getFileTitle());
            System.out.println(goalModel.getGoalList().get(1).getId());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
