public class Contact {

    String name;
    String email;
    String phonenumber;

    Contact() {
        this.name = "";
        this.email = "test@email.com";
        this.phonenumber = "0000000000";
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

/*
void addContact(Contact contact){
    myFriends[friendsCount] = contact;
    friendsCount++;
}*/
