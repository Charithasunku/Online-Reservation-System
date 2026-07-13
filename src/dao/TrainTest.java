package dao;

public class TrainTest {
    public static void main(String[] args) {
        TrainDAO dao = new TrainDAO();
        String train = dao.getTrainName(12237);
        if (train != null) {
            System.out.println("Train Name : " + train);
        } else {
            System.out.println("Train Not Found");
        }
    }
}