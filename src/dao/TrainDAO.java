package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DBConnection;

public class TrainDAO {

    public String getTrainName(int trainNo) {
        String trainName = null;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT train_name FROM train WHERE train_no = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, trainNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                trainName = rs.getString("train_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainName;
    }
}