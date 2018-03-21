import java.text.DecimalFormat;

public class Student{
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
        System.out.println(cr.getClassName()+": "+new DecimalFormat("#.##").format(cr.getGradeAverage())+" | "+cr.outputPassFail());
    }
}