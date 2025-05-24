package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtil {
    private static final String Test_data_Path= "src/test/resources/TestData/";
    //TODO: Reading data from json files
    public static String getJsonData(String filename, String key) throws FileNotFoundException {
        try {
            //Define object to read file
            FileReader reader = new FileReader(Test_data_Path + filename + ".json");

            //Parse json directly to a jsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(key).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //TODO: Reading data from properties files
    public static String getPropertiesData(String filename, String key) throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream(Test_data_Path + filename +".properties"));
        return properties.getProperty(key);
    }
}
