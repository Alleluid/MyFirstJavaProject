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

public class Main {
    public static void main(String[] args) {
        TestClass test1 = new TestClass();

        test1.setName("Fredrick");
        test1.setAge(25);
        test1.setMessage("I'm fred.");

        test1.checkInfo();


    }
}