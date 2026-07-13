package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import dao.ReservationDAO;
import model.Reservation;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellationFrame extends JFrame {

    JLabel lblTitle;
    JLabel lblPNR;
    JLabel lblPassengerName;
    JLabel lblTrainNo;
    JLabel lblTrainName;
    JLabel lblClassType;
    JLabel lblJourneyDate;
    JLabel lblSource;
    JLabel lblDestination;

    JTextField txtPNR;
    JTextField txtPassengerName;
    JTextField txtTrainNo;
    JTextField txtTrainName;
    JTextField txtClassType;
    JTextField txtJourneyDate;
    JTextField txtSource;
    JTextField txtDestination;

    JButton btnFetch;
    JButton btnCancel;
    JButton btnBack;

    public CancellationFrame() {

        setTitle("Cancellation");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("TICKET CANCELLATION");
        lblTitle.setBounds(180, 20, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitle);

        lblPNR = new JLabel("PNR");
        lblPNR.setBounds(50, 80, 100, 25);
        add(lblPNR);

        txtPNR = new JTextField();
        txtPNR.setBounds(180, 80, 180, 25);
        add(txtPNR);

        btnFetch = new JButton("Fetch");
        btnFetch.setBounds(390, 80, 100, 25);
        add(btnFetch);
        btnFetch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String pnr = txtPNR.getText().trim();

                if (pnr.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            CancellationFrame.this,
                            "Please enter PNR.");

                    return;
                }

                ReservationDAO dao = new ReservationDAO();

                Reservation reservation = dao.getReservationByPNR(pnr);

                if (reservation != null) {

                    txtPassengerName.setText(reservation.getPassengerName());
                    txtTrainNo.setText(String.valueOf(reservation.getTrainNo()));
                    txtTrainName.setText(reservation.getTrainName());
                    txtClassType.setText(reservation.getClassType());
                    txtJourneyDate.setText(reservation.getJourneyDate());
                    txtSource.setText(reservation.getSource());
                    txtDestination.setText(reservation.getDestination());

                } else {

                    JOptionPane.showMessageDialog(
                            CancellationFrame.this,
                            "Reservation Not Found!");

                }

            }

        });

        lblPassengerName = new JLabel("Passenger Name");
        lblPassengerName.setBounds(50, 130, 120, 25);
        add(lblPassengerName);

        txtPassengerName = new JTextField();
        txtPassengerName.setBounds(180, 130, 220, 25);
        txtPassengerName.setEditable(false);
        add(txtPassengerName);

        lblTrainNo = new JLabel("Train Number");
        lblTrainNo.setBounds(50, 170, 120, 25);
        add(lblTrainNo);

        txtTrainNo = new JTextField();
        txtTrainNo.setBounds(180, 170, 220, 25);
        txtTrainNo.setEditable(false);
        add(txtTrainNo);

        lblTrainName = new JLabel("Train Name");
        lblTrainName.setBounds(50, 210, 120, 25);
        add(lblTrainName);

        txtTrainName = new JTextField();
        txtTrainName.setBounds(180, 210, 220, 25);
        txtTrainName.setEditable(false);
        add(txtTrainName);

        lblClassType = new JLabel("Class Type");
        lblClassType.setBounds(50, 250, 120, 25);
        add(lblClassType);

        txtClassType = new JTextField();
        txtClassType.setBounds(180, 250, 220, 25);
        txtClassType.setEditable(false);
        add(txtClassType);

        lblJourneyDate = new JLabel("Journey Date");
        lblJourneyDate.setBounds(50, 290, 120, 25);
        add(lblJourneyDate);

        txtJourneyDate = new JTextField();
        txtJourneyDate.setBounds(180, 290, 220, 25);
        txtJourneyDate.setEditable(false);
        add(txtJourneyDate);

        lblSource = new JLabel("Source");
        lblSource.setBounds(50, 330, 120, 25);
        add(lblSource);

        txtSource = new JTextField();
        txtSource.setBounds(180, 330, 220, 25);
        txtSource.setEditable(false);
        add(txtSource);

        lblDestination = new JLabel("Destination");
        lblDestination.setBounds(50, 370, 120, 25);
        add(lblDestination);

        txtDestination = new JTextField();
        txtDestination.setBounds(180, 370, 220, 25);
        txtDestination.setEditable(false);
        add(txtDestination);

        btnCancel = new JButton("Cancel Ticket");
        btnCancel.setBounds(120, 430, 150, 35);
        add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String pnr = txtPNR.getText().trim();

                if (pnr.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            CancellationFrame.this,
                            "Please enter PNR.");
                    return;
                }
                int choice = JOptionPane.showConfirmDialog(
                        CancellationFrame.this,
                        "Are you sure you want to cancel this ticket?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION
                );
                if (choice == JOptionPane.YES_OPTION) {

                    ReservationDAO dao = new ReservationDAO();
                    boolean status = dao.cancelTicket(pnr);
                    if (status) {
                        JOptionPane.showMessageDialog(
                                CancellationFrame.this,
                                "Ticket Cancelled Successfully!");
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(
                                CancellationFrame.this,
                                "Cancellation Failed!");
                    }
                }
            }
        });
        btnBack = new JButton("Back");
        btnBack.setBounds(320, 430, 100, 35);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeFrame();
                dispose();
            }
        });

        setVisible(true);
    }
    public static void main(String[] args) {
        new CancellationFrame();
    }
    private void clearFields() {

        txtPNR.setText("");
        txtPassengerName.setText("");
        txtTrainNo.setText("");
        txtTrainName.setText("");
        txtClassType.setText("");
        txtJourneyDate.setText("");
        txtSource.setText("");
        txtDestination.setText("");

    }
}