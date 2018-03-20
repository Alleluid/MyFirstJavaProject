public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class Contact{
    String name;
    String email;
    String phonenumber;
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