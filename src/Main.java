import java.util.ArrayList;
import java.util.List;

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
        gradeAverage = 0.0;
    }
    boolean isPassing(){
        return gradeAverage > 50.0;
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
    private ClassRecord englishRecord;
    private ClassRecord mathRecord;
    private ClassRecord scienceRecord;
    Student(String name){
        this.name = name;
        englishRecord = new ClassRecord("English");
        mathRecord = new ClassRecord("Math");
        scienceRecord = new ClassRecord("Science");
    }

}

public class Main {
    public static void main(String[] args) {
        List<Student> studentRecords = new ArrayList<>();

        studentRecords.add(new Student("Fred"));
        studentRecords.add(new Student("Sally"));
        studentRecords.add(new Student("Bob"));
        studentRecords.add(new Student("June"));

        System.out.println("Students in list:");
        for (Student s: studentRecords) {
            System.out.println(s.name);
        }


    }
}