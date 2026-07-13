package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LoginDAO;

public class LoginFrame extends JFrame {

    JLabel lblTitle, lblUsername, lblPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;

    public LoginFrame() {

        setTitle("Online Reservation System");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Title
        lblTitle = new JLabel("ONLINE RESERVATION SYSTEM");
        lblTitle.setBounds(85, 20, 350, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitle);

        // Username Label
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(70, 80, 100, 25);
        add(lblUsername);

        // Username TextField
        txtUsername = new JTextField();
        txtUsername.setBounds(180, 80, 180, 25);
        add(txtUsername);

        // Password Label
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(70, 130, 100, 25);
        add(lblPassword);

        // Password Field
        txtPassword = new JPasswordField();
        txtPassword.setBounds(180, 130, 180, 25);
        add(txtPassword);

        // Login Button
        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(180, 190, 100, 35);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	String username = txtUsername.getText().trim();
            	String password = String.valueOf(txtPassword.getPassword()).trim();

            	if (username.isEmpty() || password.isEmpty()) {
            	    JOptionPane.showMessageDialog(LoginFrame.this,
            	            "Please enter Username and Password!");
            	    return;
            	}
                LoginDAO dao = new LoginDAO();

                boolean status = dao.validateLogin(username, password);

                if (status) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful");
                    new HomeFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid Username or Password");
                }

            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}