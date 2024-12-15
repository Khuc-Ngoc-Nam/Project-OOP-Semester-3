package aims.users;

import aims.store.Store;

public abstract class Person {
    private String username; // Tên đăng nhập
    private String password; // Mật khẩu

    // Constructor khởi tạo tài khoản người dùng
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter cho tên đăng nhập
    public String getUsername() {
        return username;
    }

    // Phương thức xác thực đăng nhập
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // Phương thức trừu tượng để hiển thị menu cho từng loại người dùng
    public abstract void displayMenu(Store store);

    // Phương thức thay đổi mật khẩu
    public void changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Mật khẩu đã được thay đổi thành công.");
        } else {
            System.out.println("Mật khẩu cũ không đúng. Vui lòng thử lại.");
        }
    }

    // Phương thức in thông tin cơ bản của người dùng
    public void printInfo() {
        System.out.println("Tên đăng nhập: " + username);
        System.out.println("Loại tài khoản: " + this.getClass().getSimpleName());
    }
}
