package Rental_Car_System.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Person {
    protected String firstName, lastName, gender, location, contact;
    protected List<String> mailbox;

    public Person() {
        
    }

    public Person(String firstName, String lastName, String gender, String location, String contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.location = location;
        this.contact = contact;
        this.mailbox = new ArrayList<>();
    }

    public void sendMessage(String message, Person recipient) {
        recipient.receiveMessage(message, this);
    }

    private void receiveMessage(String message, Person sender) {
        mailbox.add("From " + sender.getFullName() + ": " + message);
    }



    public String getMailbox() {
        StringBuilder messages = new StringBuilder();
    
        for (String message : mailbox) {
            messages.append(message).append("\n"); // Append each message with a newline
        }
    
        return messages.toString().trim(); // Trim to remove any trailing newline
    }

    public abstract boolean logIn(String username, String password, Map<String, String> credentials);

    public void setPerson(String fN, String lN, String g, String l, String c){
	
		firstName = fN;
		lastName = lN;
		gender = g;
		location = l;
		contact = c;
	}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    public String getGender() {
		return gender;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getContact() {
		return contact;
	}
}