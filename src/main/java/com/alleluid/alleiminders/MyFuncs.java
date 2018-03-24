package com.alleluid.alleiminders;

import java.io.*;
import java.time.*;
import java.util.ArrayList;

public class MyFuncs {
    private static ZoneOffset systemOffset = OffsetDateTime.now().getOffset();

    public static ZoneOffset getSystemOffset(){
        return systemOffset;
    }
    public LocalDateTime getLocalDateTimeFromEpoch(long epoch){

        return LocalDateTime.ofEpochSecond(epoch, 0, systemOffset);
    }
    public LocalDate getLocalDateFromEpoch(long epoch){

        return LocalDateTime.ofEpochSecond(epoch, 0, systemOffset).toLocalDate();
    }
//********************************************************************************************************************
    //public static String saveDir = "C:/Alleiminders/saveData.dat";

    public static void serializeArrayList(ArrayList list){
        File file = new File(ConfigHandler.currentDir+"/Alleiminders/saveData.dat");
        file.getParentFile().mkdirs();

        try {
            FileOutputStream fileOut = new FileOutputStream(ConfigHandler.saveDir);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.println("List saved to "+ConfigHandler.saveDir);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList deserializeArrayList(){
        File file = new File(ConfigHandler.currentDir+"/Alleiminders/saveData.dat");
        file.getParentFile().mkdirs();

        ArrayList<Entry> list = null;
        try {
            FileInputStream fileIn = new FileInputStream(ConfigHandler.saveDir);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Entry>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            ArrayList<Entry> errorList = new ArrayList<>();
            errorList.add(new Entry("No Data",LocalDate.now(), LocalTime.now(), "First run? Use Help -> ~Debug~ to make some data!"));
            return errorList;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return list;
        }
        return list;
    }







}

