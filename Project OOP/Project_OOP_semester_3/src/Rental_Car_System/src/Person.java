package Rental_Car_System.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Person {
    protected String name;
    protected String id;
    protected List<String> mailbox;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        this.mailbox = new ArrayList<>();
    }

    public void sendMessage(String message, Person recipient) {
        recipient.receiveMessage(message, this);
    }

    private void receiveMessage(String message, Person sender) {
        mailbox.add("From " + sender.getName() + ": " + message);
    }



    public String getMailbox() {
        StringBuilder messages = new StringBuilder();
    
        for (String message : mailbox) {
            messages.append(message).append("\n"); // Append each message with a newline
        }
    
        return messages.toString().trim(); // Trim to remove any trailing newline
    }

    public abstract boolean logIn(String username, String password, Map<String, String> credentials);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}