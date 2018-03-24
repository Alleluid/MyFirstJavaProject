package com.alleluid.alleiminders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.*;

public class ConfigHandler {
    private static final LocalTime systemDefaultTime = LocalTime.parse("09:00"); //Defaults to 9 AM
    private static final LocalDate systemDefaultDate = LocalDate.now().plusDays(1); //Defaults to tomorrow
    private static final DateTimeFormatter systemCustomTimeFormat = DateTimeFormatter.ofPattern("h:mm a");
    private static final DateTimeFormatter systemCustomDateFormat = DateTimeFormatter.ofPattern("yy-MM-dd");
    private static final DateTimeFormatter systemCustomDateTimeFormat = DateTimeFormatter.ofPattern("yy-MM-dd h:mm a");
    private static final String systemDefaultNotes = "Made on "+LocalDate.now();
    private static final String systemCurrentDir = System.getProperty("user.dir");
    private static final String systemSaveDir = systemCurrentDir+"/Alleiminders/saveData.dat";


    //TODO set up to load from JSON
    public static LocalTime defaultTime = LocalTime.parse("09:00"); //Defaults to 9 AM
    public static LocalDate defaultDate = LocalDate.now().plusDays(1); //Defaults to tomorrow
    public static DateTimeFormatter customTimeFormat = DateTimeFormatter.ofPattern("h:mm a");
    public static DateTimeFormatter customDateFormat = DateTimeFormatter.ofPattern("yy-MM-dd");
    public static DateTimeFormatter customDateTimeFormat = DateTimeFormatter.ofPattern("yy-MM-dd h:mm a");
    public static String defaultNotes = "Made on "+LocalDate.now();
    public static String currentDir = System.getProperty("user.dir");
    public static String saveDir = currentDir+"/Alleiminders/saveData.dat";


    public boolean loadConfigs(){
        Gson gson = new Gson();
        //gson.toJson();
        return true;

    }

}
