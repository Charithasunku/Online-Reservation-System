package dao;

public class LoginTest {

    public static void main(String[] args) {
        LoginDAO dao = new LoginDAO();
        boolean status = dao.validateLogin("admin", "admin123");
        if (status) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
    }
}