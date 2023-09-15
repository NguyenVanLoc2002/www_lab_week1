package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories;

import org.apache.commons.logging.Log;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect.Connect;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Logs;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogReponsitory {
    private Connection connection;

    public LogReponsitory() {
        connection = Connect.getConnection();
    }

    public void insertLog(String account_id, Timestamp login_time, Timestamp logout_time, String notes) throws Exception {
        String sql = "Insert into Log(account_id,login_time,logout_time,notes) values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account_id);
        ps.setTimestamp(2, login_time);
        ps.setTimestamp(3, logout_time);
        ps.setString(4, notes);
        ps.executeUpdate();
    }

    public void updateLog(int id , Timestamp logout_time) throws Exception {
        String sql = "update Log set logout_time = ? where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setTimestamp(1,logout_time);
        ps.setInt(2, id);
        ps.executeUpdate() ;
    }

    public boolean deletLog(Logs log) throws Exception {
        String sql = "delete from Log  where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, log.getNotes());
        return ps.executeUpdate() > 0;
    }

    public List<Logs> getAll() throws Exception {
        String sql = "select * from log";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Logs> list = new ArrayList<>();
        while (rs.next()) {
            Logs log = new Logs(rs.getInt(1), rs.getString(2),
                    rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5));
            list.add(log);
        }
        return list;
    }

    public Optional<Logs> getById(int id) throws Exception {
        String sql = "select * from log  where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Logs log = new Logs(rs.getInt(1), rs.getString(2),
                    rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5));
            return Optional.of(log);
        }
        return Optional.empty();
    }
}
