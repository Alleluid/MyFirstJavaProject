import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TestClass{
    String name;
    int age;
    String message;
    char [] initials;
    TestClass(){
        name = "A B C";
        age = 0;
        message = "";
    }

    void setName(String name){
        this.name = name;
    }
    void setAge(int age){
        this.age = age;
    }
    void setMessage(String message){
        this.message = message;
    }
    void checkInfo(){
        System.out.println("Name: "+name+" Age: "+age+" Message: "+message);
    }
}

class ClassRecord {
    private String className;
    private double gradeAverage;
    ClassRecord(String className){
        this.className = className;
        gradeAverage = Math.random()*100;
    }
    boolean isPassing(){
        return gradeAverage > 50.0;
    }
    String outputPassFail(){
        if (isPassing()){
            return "Pass";
        } else {
            return "Fail";
        }
    }
    double getGradeAverage(){
        return this.gradeAverage;
    }
    void setGradeAverage(double average){
        this.gradeAverage = average;
    }
    String getClassName(){
        return this.className;
    }


}

class Student{
    String name;
    public ClassRecord record1;
    public ClassRecord record2;
    public ClassRecord record3;
    Student(String name){
        this.name = name;
        record1 = new ClassRecord("English");
        record2 = new ClassRecord("Math");
        record3 = new ClassRecord("Science");
    }
    String getName(){
        return name;
    }
    String getLowerName(){
        return name.toLowerCase();
    }
    void printInfo(ClassRecord cr){
        System.out.println(cr.getClassName()+": "+cr.getGradeAverage()+" | "+cr.outputPassFail());
    }
}

public class Main {
    public static void print(String input){ //I'm lazy
        System.out.println(input);
    }

    public static void newln(int num){
        for (int i = 0; i < num; i++) {
            System.out.print(System.lineSeparator());
        }
    }
    public static void newln(){
        System.out.print(System.lineSeparator());
    }


    public static void main(String[] args) {
        //setup:
        List<Student> studentRecords = new ArrayList<>();

        studentRecords.add(new Student("Fred"));
        studentRecords.add(new Student("Sally"));
        studentRecords.add(new Student("Bob"));
        studentRecords.add(new Student("June"));


        //Running in cmd:

        Student activeStudent = null;
        while (true) { //repeats until there is valid student name entered
            print("Students in list:");
            for (Student s: studentRecords) {
                print(s.name);
            }
            newln();

            print("Whom do you want to view?");
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().toLowerCase();


            for (Student s: studentRecords) {
                if (s.getLowerName().equals(input)) {
                    activeStudent = s;
                }
            }

            if (activeStudent != null) {
                break;
            } else {
                print("Sorry, student not found.");
            }
        }
        newln(2);
        print("Student's Info:");
        print(activeStudent.getName());
        newln();
        print("Grade Averages: ");
        activeStudent.printInfo(activeStudent.record1);
        activeStudent.printInfo(activeStudent.record2);
        activeStudent.printInfo(activeStudent.record3);



z
    }
}