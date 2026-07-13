package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.TrainDAO;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.ReservationDAO;
import model.Reservation;
import javax.swing.JOptionPane;

public class ReservationFrame extends JFrame {

    JLabel lblTitle;
    JLabel lblPassengerName, lblTrainNo, lblTrainName;
    JLabel lblClassType, lblJourneyDate;
    JLabel lblSource, lblDestination;

    JTextField txtPassengerName;
    JTextField txtTrainNo;
    JTextField txtTrainName;
    JTextField txtJourneyDate;
    JTextField txtSource;
    JTextField txtDestination;

    JComboBox<String> cmbClassType;
    JButton btnFetch;
    JButton btnBook;
    JButton btnBack;

    public ReservationFrame() {

        setTitle("Reservation Form");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Title
        lblTitle = new JLabel("RESERVATION FORM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(180, 20, 250, 30);
        add(lblTitle);

        // Passenger Name
        lblPassengerName = new JLabel("Passenger Name");
        lblPassengerName.setBounds(50, 80, 120, 25);
        add(lblPassengerName);

        txtPassengerName = new JTextField();
        txtPassengerName.setBounds(200, 80, 220, 25);
        add(txtPassengerName);

        // Train Number
        lblTrainNo = new JLabel("Train Number");
        lblTrainNo.setBounds(50, 120, 120, 25);
        add(lblTrainNo);
        btnFetch = new JButton("Fetch");
        btnFetch.setBounds(440, 120, 90, 25);
        add(btnFetch);
        

        txtTrainNo = new JTextField();
        txtTrainNo.setBounds(200, 120, 220, 25);
        add(txtTrainNo);

        // Train Name
        lblTrainName = new JLabel("Train Name");
        lblTrainName.setBounds(50, 160, 120, 25);
        add(lblTrainName);

        txtTrainName = new JTextField();
        txtTrainName.setBounds(200, 160, 220, 25);
        txtTrainName.setEditable(false);
        add(txtTrainName);

        // Class Type
        lblClassType = new JLabel("Class Type");
        lblClassType.setBounds(50, 200, 120, 25);
        add(lblClassType);

        cmbClassType = new JComboBox<>();
        cmbClassType.addItem("Sleeper");
        cmbClassType.addItem("AC 3 Tier");
        cmbClassType.addItem("AC 2 Tier");
        cmbClassType.addItem("First Class");
        cmbClassType.setBounds(200, 200, 220, 25);
        add(cmbClassType);

        // Journey Date
        lblJourneyDate = new JLabel("Journey Date");
        lblJourneyDate.setBounds(50, 240, 120, 25);
        add(lblJourneyDate);

        txtJourneyDate = new JTextField();
        txtJourneyDate.setBounds(200, 240, 220, 25);
        add(txtJourneyDate);

        // Source
        lblSource = new JLabel("Source");
        lblSource.setBounds(50, 280, 120, 25);
        add(lblSource);

        txtSource = new JTextField();
        txtSource.setBounds(200, 280, 220, 25);
        add(txtSource);

        // Destination
        lblDestination = new JLabel("Destination");
        lblDestination.setBounds(50, 320, 120, 25);
        add(lblDestination);

        txtDestination = new JTextField();
        txtDestination.setBounds(200, 320, 220, 25);
        add(txtDestination);

        // Buttons
        btnBook = new JButton("Book Ticket");
        btnBook.setBounds(150, 380, 130, 35);
        add(btnBook);

        btnBack = new JButton("Back");
        btnBack.setBounds(310, 380, 110, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeFrame();
                dispose();
            }
        });
        btnFetch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int trainNo = Integer.parseInt(txtTrainNo.getText());

                    TrainDAO dao = new TrainDAO();

                    String trainName = dao.getTrainName(trainNo);

                    if (trainName != null) {

                        txtTrainName.setText(trainName);

                    } else {

                        JOptionPane.showMessageDialog(
                                ReservationFrame.this,
                                "Train Not Found!");

                        txtTrainName.setText("");

                    }

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            ReservationFrame.this,
                            "Please enter a valid Train Number.");

                }

            }

        });
        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String passengerName = txtPassengerName.getText().trim();
                    String trainNoText = txtTrainNo.getText().trim();
                    String trainName = txtTrainName.getText().trim();
                    String classType = cmbClassType.getSelectedItem().toString();
                    String journeyDate = txtJourneyDate.getText().trim();
                    String source = txtSource.getText().trim();
                    String destination = txtDestination.getText().trim();

                    // Validation
                    if (passengerName.isEmpty() || trainNoText.isEmpty()
                            || trainName.isEmpty() || journeyDate.isEmpty()
                            || source.isEmpty() || destination.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                ReservationFrame.this,
                                "Please fill all the fields!");
                        return;
                    }
                    int trainNo = Integer.parseInt(trainNoText);
                    Reservation reservation = new Reservation();
                    reservation.setPassengerName(passengerName);
                    reservation.setTrainNo(trainNo);
                    reservation.setTrainName(trainName);
                    reservation.setClassType(classType);
                    reservation.setJourneyDate(journeyDate);
                    reservation.setSource(source);
                    reservation.setDestination(destination);
                    ReservationDAO dao = new ReservationDAO();
                    boolean status = dao.bookTicket(reservation);
                    if (status) {
                        JOptionPane.showMessageDialog(
                                ReservationFrame.this,
                                "Ticket Booked Successfully!\n\nPNR : "
                                        + reservation.getPnr());
                    } else {
                        JOptionPane.showMessageDialog(
                                ReservationFrame.this,
                                "Booking Failed!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            ReservationFrame.this,
                            "Train Number must be numeric.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            ReservationFrame.this,
                            ex.getMessage());
                }
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {
        new ReservationFrame();
    }
}