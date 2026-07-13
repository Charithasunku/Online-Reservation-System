package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import database.DBConnection;
import model.Reservation;

public class ReservationDAO {
    public boolean bookTicket(Reservation reservation) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            // Generate PNR
            String pnr = "PNR" + (100000 + new Random().nextInt(900000));
            reservation.setPnr(pnr);
            ps.setString(1, reservation.getPnr());
            ps.setString(2, reservation.getPassengerName());
            ps.setInt(3, reservation.getTrainNo());
            ps.setString(4, reservation.getTrainName());
            ps.setString(5, reservation.getClassType());
            ps.setDate(6, java.sql.Date.valueOf(reservation.getJourneyDate()));
            ps.setString(7, reservation.getSource());
            ps.setString(8, reservation.getDestination());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Reservation getReservationByPNR(String pnr) {
        Reservation reservation = null;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM reservation WHERE pnr = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pnr);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reservation = new Reservation();

                reservation.setPnr(rs.getString("pnr"));
                reservation.setPassengerName(rs.getString("passenger_name"));
                reservation.setTrainNo(rs.getInt("train_no"));
                reservation.setTrainName(rs.getString("train_name"));
                reservation.setClassType(rs.getString("class_type"));
                reservation.setJourneyDate(rs.getDate("journey_date").toString());
                reservation.setSource(rs.getString("source"));
                reservation.setDestination(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }
    public boolean cancelTicket(String pnr) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM reservation WHERE pnr = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pnr);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}