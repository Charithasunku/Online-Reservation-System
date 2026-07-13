package dao;

public class CancellationTest {

    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();
        boolean status = dao.cancelTicket("PNR845955"); // Use an actual PNR
        if (status) {
            System.out.println("Ticket Cancelled Successfully!");
        } else {
            System.out.println("Cancellation Failed!");
        }
    }
}