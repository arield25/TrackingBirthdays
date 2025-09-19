package com.trackingbirthdays;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BirthdayExample {

    // private static hashmap to store the birthdays
    private static HashMap<String, String> birthdayMap = new HashMap<>();

    // reads a JSON file into a JSONArray
    public static JSONArray readJSONArrayFile(String fileName) {
        JSONParser jsonParser = new JSONParser();
        JSONArray birthdayArr = null;

        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);
            birthdayArr = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return birthdayArr;
    }

    // initialize the hashmap from the JSON file
    public static void initializeMap(final String pathToFile) {
        JSONArray jsonData = readJSONArrayFile(pathToFile);
        if (jsonData == null) return;

        for (int i = 0; i < jsonData.size(); i++) {
            JSONObject obj = (JSONObject) jsonData.get(i);
            String name = (String) obj.get("name");
            String birthday = (String) obj.get("birthday");

            birthdayMap.put(name, birthday);
        }
    }

    // NEW helper method for tests and external queries
    public static String getBirthday(String name) {
        return birthdayMap.get(name); // returns null if name not found
    }

    public static void main(final String[] args) {
        // path to your JSON file
        String pathToFile = "src/main/resources/birthday.json";

        // initialize the hashmap
        initializeMap(pathToFile);

        // read user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String name = input.nextLine();

        // check for exact match first
        if (birthdayMap.containsKey(name)) {
            System.out.println(name + "'s birthday is: " + birthdayMap.get(name));
        } else {
            // check for partial match
            boolean found = false;
            for (String key : birthdayMap.keySet()) {
                if (key.toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(key + "'s birthday is: " + birthdayMap.get(key));
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Birthday for " + name + " is unknown.");
            }
        }

        input.close();
    }
}
