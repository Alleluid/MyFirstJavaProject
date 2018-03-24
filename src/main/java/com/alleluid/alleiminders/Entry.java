package com.alleluid.alleiminders;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Entry implements java.io.Serializable{
    private transient SimpleStringProperty labelProp = new SimpleStringProperty("");
    //private transient SimpleStringProperty dueDateProp = new SimpleStringProperty("");
    //private transient SimpleStringProperty dueTimeProp = new SimpleStringProperty("");
    //private transient SimpleStringProperty dueDateTimeProp = new SimpleStringProperty("");
    private transient SimpleStringProperty notesProp = new SimpleStringProperty("");

    private String labelString;
    private String notesString;
    private String dueDateTimeString;
    private String dueTimeString;
    private String dueDateString;

    private LocalDateTime dueDateTime = LocalDateTime.ofEpochSecond(0, 0, MyFuncs.getSystemOffset());
    private LocalDate dueDate = dueDateTime.toLocalDate();
    private LocalTime dueTime = dueDateTime.toLocalTime();

//*****************************************************************************************************************
    //TODO ensure it can handle having null notes
    //No params constructor
    public Entry() {
        this("Untitled", ConfigHandler.defaultDate, ConfigHandler.defaultTime, ConfigHandler.defaultNotes);
    }
    //Date-notes-only constructor, sets default time
    public Entry(String label, LocalDate dueDate, String notes) {
        this(label, dueDate, ConfigHandler.defaultTime, notes);
    }
    //Time-notes-only constructor, sets default date
    public Entry(String label, LocalTime dueTime, String notes) {
        this(label, ConfigHandler.defaultDate, dueTime, notes);
    }
    //Name-notes-only constructor, sets default date and time
    public Entry(String label, String notes) {
        this(label, ConfigHandler.defaultDate, ConfigHandler.defaultTime, notes);
    }


    //Date-only constructor, sets default time and notes
    public Entry(String label, LocalDate dueDate) {
        this(label, dueDate, ConfigHandler.defaultTime, ConfigHandler.defaultNotes);

    }
    //Time-only constructor, sets default date and notes
    public Entry(String label, LocalTime dueTime) {
        this(label, ConfigHandler.defaultDate, dueTime, ConfigHandler.defaultNotes);

    }
    //name-only constructor, sets default date, time, and notes
    public Entry(String label) {
        this(label, ConfigHandler.defaultDate, ConfigHandler.defaultTime, ConfigHandler.defaultNotes);
    }


    //Full Service constructor, separate date and time
    public Entry(String label, LocalDate dueDate, LocalTime dueTime, String notes) {
        setLabelString(label);
        setNotesString(notes);
        setDueDateTimeSeparate(dueDate, dueTime);
        setupDateTimeStrings();
        setupLabelNotesStrings();
    }
//************************************************************************************************************
    //Label get/setters
    public String getLabelProp() {
        return labelProp.get();
    }
    public String getLabelString() {
        return labelString;
    }
    public void setLabelProp(String nLabel){
        labelProp.set(nLabel);
    }
    public void setLabelString(String nLabel){
        labelString = nLabel;
    }



    //Date/Time getters
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalTime getDueTime() {
        return dueTime;
    }

    public String getDueDateTimeString() {
        return dueDateTimeString;
    }
    public String getDueDateString() {
        return dueDateString;
    }
    public String getDueTimeString() {
        return dueTimeString;
    }



    //Date/Time setters TODO maybe default to default time/date vars instead of now?
    public void setDueDateTime(LocalDateTime ldt){
        if (ldt==null){ ldt = LocalDateTime.now(); }
        dueDateTime = ldt;
        setDueDate(ldt.toLocalDate());
        setDueTime(ldt.toLocalTime());
    }
    public void setDueDateTimeSeparate(LocalDate ld, LocalTime lt){
        if (lt==null){ lt = LocalTime.now(); }
        if (ld==null){ ld = LocalDate.now(); }
        setDueDateTime(LocalDateTime.of(ld,lt));
    }
    public void setDueDate(LocalDate ld){
        if (ld==null){ ld = LocalDate.now(); }
        dueDate = ld;
    }
    public void setDueTime(LocalTime lt){
        if (lt==null){ lt = LocalTime.now(); }
        dueTime = lt;
    }
    //Date/Time setters parsing strings TEMP TODO change this to date picker UI, preferably JFXtras
    public void parseDueDate(String strDueDate){
        if (!strDueDate.isEmpty()||strDueDate.contains(" ")){dueDate = LocalDate.parse(strDueDate, ConfigHandler.customDateFormat);}
        setupDateTimeStrings();
    }
    public void parseDueTime(String strDueTime){
        dueTime = LocalTime.parse(strDueTime, ConfigHandler.customTimeFormat); //FIXME handle null string commits
        setupDateTimeStrings();
    }



    //Date/Time property string getters
    public String getDueDateTimeProp() {
        return dueDateTime.format(ConfigHandler.customDateTimeFormat);
    }
    public String getDueDateProp() {
        return dueDate.format(ConfigHandler.customDateFormat);
    }
    public String getDueTimeProp() {
        return dueTime.format(ConfigHandler.customTimeFormat);
    }

    //Notes set/getters
    public String getNotesProp() {
        return notesProp.get();
    }
    public String getNotesString() {
        return notesString;
    }
    public void setNotesProp(String nNotes){
        notesProp.set(nNotes);
    }
    public void setNotesString(String nNotes){
        notesString = nNotes;
    }


    /**
     * Runs in full service constructor to ensure correct strings
     */
    public void setupDateTimeStrings() {
        //dueDateTimeProp.set(dueDateTime.format(customDateTimeFormat));
        dueDateTimeString = dueDateTime.format(ConfigHandler.customDateTimeFormat);
        //dueDateProp.set(dueDate.format(customDateFormat));
        dueDateString = dueDate.format(ConfigHandler.customDateFormat);
        //dueTimeProp.set(dueTime.format(customTimeFormat));
        dueTimeString = dueTime.format(ConfigHandler.customTimeFormat);
    }

    public void setupLabelNotesStrings() {
        labelProp.set(labelString);
        notesProp.set(notesString);
    }

}
