package src.User;

public class Customer {
    private String username;
    private String personalInfo;

    public Customer(String username, String personalInfo) {
        this.username = username;
        this.personalInfo = personalInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }
}

