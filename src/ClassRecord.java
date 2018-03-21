public class ClassRecord {
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
