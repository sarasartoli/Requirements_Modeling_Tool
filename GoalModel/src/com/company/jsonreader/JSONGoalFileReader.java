package com.company.jsonreader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class JSONGoalFileReader {

    private File goalFile;
    private String fileTitle;

    public JSONGoalFileReader(File goalFile) {
        this.goalFile = goalFile;
    }

    public JSONObject parseFile() throws IOException, ParseException {
        FileReader fileReader = new FileReader(goalFile);
        JSONParser parser = new JSONParser();
        JSONObject parse = (JSONObject) parser.parse(fileReader);

        this.fileTitle = (String) parse.get("title");
        return parse;
    }

    public String getFileTitle() {
        return fileTitle;
    }
}
