package com.company;

import com.company.aspwriter.ASPGoalFileWriter;
import com.company.jsonreader.JSONGoalFileReader;
import com.company.model.GoalModel;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.print("Begin analysis, by entering the json file eg " +
                "\"C:\\Users\\username\\document\\samplefile.json\"");

        System.out.print("\nPlease enter source file: ");
        String source = scanner.next();
        //String source = "resources\\json\\elevatorx.json";

        File jFile = new File(source);
        System.out.print("\nPlease enter destination folder: ");
        String destination = scanner.next();
        //String destination = "resources";
        System.out.println();

        JSONGoalFileReader jsonFile = new JSONGoalFileReader(jFile);

        try {

            GoalModel goalModel = new GoalModel(jsonFile.parseFile());
            ASPGoalFileWriter aspfile = new ASPGoalFileWriter();
            aspfile.writeToASP(goalModel, jsonFile.getFileTitle(), destination);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
