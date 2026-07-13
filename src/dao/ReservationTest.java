package dao;
import model.Reservation;

public class ReservationTest {
    public static void main(String[] args) {
        Reservation reservation = new Reservation();
        reservation.setPassengerName("Bheem");
        reservation.setTrainNo(12627);
        reservation.setTrainName("Karnataka Express");
        reservation.setClassType("Sleeper");
        reservation.setJourneyDate("2026-07-20");
        reservation.setSource("Bangalore");
        reservation.setDestination("Delhi");
        ReservationDAO dao = new ReservationDAO();
        boolean status = dao.bookTicket(reservation);
        if (status) {
            System.out.println("Ticket Booked Successfully!");
            System.out.println("Generated PNR : " + reservation.getPnr());
        } else {
            System.out.println("Booking Failed!");
        }
    }
}