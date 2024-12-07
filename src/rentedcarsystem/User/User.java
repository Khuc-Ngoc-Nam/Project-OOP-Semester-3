package User;

public class User {
    private String username;
    private String personalInfo;

    public User(String username, String personalInfo) {
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

