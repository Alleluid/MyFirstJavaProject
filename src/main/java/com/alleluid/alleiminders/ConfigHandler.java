package com.alleluid.alleiminders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static javafx.application.Platform.exit;

/**
 * Used to set hard-coded system defaults
 * Has static class JSONConfigHandler to store uhhh...
 */
public class ConfigHandler {
    //Manually set non-changing vars
    public static final String version = "PRE-ALPHA";


    //These need to be parsed from strings, not set directly
    public static LocalTime defaultTime = LocalTime.parse("09:00"); //Defaults to 9 AM
    public static LocalDate defaultDate = LocalDate.now().plusDays(1); //Defaults to tomorrow
    public static String defaultNotes = "Made on "+LocalDate.now();
    public static String currentDir = System.getProperty("user.dir");

    //Strings saved separately to be readable if needed to generate config
    public static String workingDir = currentDir;
    public static String customTFormatStr = "h:mm a";
    public static String customDFormatStr = "yy-MM-dd";
    public static String customTDFormatStr = "yy-MM-dd h:mm a";
    //TODO: Figure out how to deal with user configuring these
    public static String saveFile = workingDir+"/Alleiminders/saveData.dat";
    public static String configFile = workingDir+"/Alleiminders/config.json";
    public static String testString = "This is the value from ConfigHandler";

    public static DateTimeFormatter customTimeFormat = DateTimeFormatter.ofPattern(customTFormatStr);
    public static DateTimeFormatter customDateFormat = DateTimeFormatter.ofPattern(customDFormatStr);
    public static DateTimeFormatter customDateTimeFormat = DateTimeFormatter.ofPattern(customTDFormatStr);


    /**
     * TODO: implement checking versions, to update if lacking new fields
     * Stores fields used to store configuration objects to be parsed from JSON.
     */
    private static class JSONConfigHolder {
        private static String workingDir = ConfigHandler.workingDir;
        private static String customTimeFormat = ConfigHandler.customTFormatStr;
        private static String customDateFormat = ConfigHandler.customDFormatStr;
        private static String customDateTimeFormat = ConfigHandler.customTDFormatStr;
        private static String saveFile = ConfigHandler.saveFile;
        private static String configFile = ConfigHandler.configFile;
        private static String testString = "This is the value from JSONConfigHolder";

        private JSONConfigHolder(){ } //Empty constructor

        public static void loadDataIntoHandler() {
            ConfigHandler.workingDir = workingDir;
            ConfigHandler.customTFormatStr = customTimeFormat;
            ConfigHandler.customDFormatStr = customDateFormat;
            ConfigHandler.customTDFormatStr = customDateTimeFormat;
            ConfigHandler.saveFile = saveFile;
            ConfigHandler.configFile = configFile;
            ConfigHandler.testString = testString;
        }
    }



    /**
     * Sets the config string fields to their defaults from parent class before generating new file from fields.
     */
    public static void readFileFromJSON() { //TODO once working, set up to load on launch
        if (!checkAndGenConfigs()) {
            return; //read not needed
        } else {
            File file = new File(configFile);
            if (!file.exists()) {System.out.println("How did you do this. ConfigHandler issue"); exit();}
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers((Modifier.TRANSIENT)).create();
            try {
                /*FileInputStream fileIn = new FileInputStream(file);
                Reader in = new BufferedReader(new InputStreamReader(fileIn));
                in.read();*/
                JSONConfigHolder loadedJSONObj = gson.fromJson(new FileReader(configFile), JSONConfigHolder.class);
                loadedJSONObj.loadDataIntoHandler();
            } catch (IOException i){
                i.printStackTrace();
            }
        }
    }

    /**
     * Used to generate JSON config file if it doesn't already exist.
     * Also handles making dir if it somehow doesn't exist from tableData, and prints results.
     * @return returns true if the file already exists.
     */
    public static boolean checkAndGenConfigs(){
        File file = new File(configFile);
        if (file.exists()) {
            return true;
        } else {
            System.out.println("File didn't exist");
            file.getParentFile().mkdirs();
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
            try {
            gson.toJson(new JSONConfigHolder(), new FileWriter(configFile));
            } catch (IOException i){
                i.printStackTrace();
            }
            return false;

            /*String gsonStr = gson.toJson(new JSONConfigHolder());
            try {
                FileOutputStream fileOut = new FileOutputStream(configFile);
                Writer out = new BufferedWriter(new OutputStreamWriter(fileOut));
                out.write(gsonStr);
                out.close();
                fileOut.close();
            } catch (IOException i){
                i.printStackTrace();
            }
            return false;*/
        }
    }

}
