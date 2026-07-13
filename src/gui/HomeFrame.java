package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeFrame extends JFrame {

    JLabel lblTitle;
    JButton btnReservation;
    JButton btnCancellation;
    JButton btnLogout;

    public HomeFrame() {

        setTitle("Home");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("WELCOME TO ONLINE RESERVATION SYSTEM");
        lblTitle.setBounds(40, 30, 400, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle);

        btnReservation = new JButton("Reservation");
        btnReservation.setBounds(150, 100, 180, 40);
        add(btnReservation);
        btnReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservationFrame();
                dispose();
            }
        });

        btnCancellation = new JButton("Cancellation");
        btnCancellation.setBounds(150, 170, 180, 40);
        add(btnCancellation);
        btnCancellation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CancellationFrame();
                dispose();
            }
        });

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(150, 240, 180, 40);
        add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {
        new HomeFrame();
    }
}