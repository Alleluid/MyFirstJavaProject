public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class Contact{
    String name;
    String email;
    String phonenumber;
    Contact(){
        this.name = "";
        this.email = "test@email.com";
    }
}

class ContactsManager {
    // Fields:
    Contact [] myFriends;
    int friendsCount;
    //Constructor:
    ContactsManager(){
        this.friendsCount = 0;
        this.myFriends = new Contact[500];
    }
}