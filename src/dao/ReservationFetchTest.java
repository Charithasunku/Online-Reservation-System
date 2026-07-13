package dao;

import model.Reservation;

public class ReservationFetchTest {
    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();
        Reservation reservation = dao.getReservationByPNR("PNR847895");
        if (reservation != null) {
            System.out.println("PNR : " + reservation.getPnr());
            System.out.println("Passenger : " + reservation.getPassengerName());
            System.out.println("Train No : " + reservation.getTrainNo());
            System.out.println("Train Name : " + reservation.getTrainName());
            System.out.println("Class : " + reservation.getClassType());
            System.out.println("Journey Date : " + reservation.getJourneyDate());
            System.out.println("Source : " + reservation.getSource());
            System.out.println("Destination : " + reservation.getDestination());
        } else {
            System.out.println("Reservation Not Found!");
        }
    }
}