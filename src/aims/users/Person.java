package aims.users;

import aims.store.Store;

public abstract class Person {
    private String username; // Tên đăng nhập
    private String password; // Mật khẩu

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public abstract void displayMenu(Store store);

    public void changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Mật khẩu đã được thay đổi thành công.");
        } else {
            System.out.println("Mật khẩu cũ không đúng. Vui lòng thử lại.");
        }
    }

    public void printInfo() {
        System.out.println("Tên đăng nhập: " + username);
        System.out.println("Loại tài khoản: " + this.getClass().getSimpleName());
    }
}
