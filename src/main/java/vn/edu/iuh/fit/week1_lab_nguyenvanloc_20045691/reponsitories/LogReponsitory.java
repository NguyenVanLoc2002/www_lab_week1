package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories;

import org.apache.commons.logging.Log;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect.Connect;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Logs;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Role;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogReponsitory {
    private Connection connection;

    public LogReponsitory() {
        connection = Connect.getConnection();
    }

    public boolean insertLog(Logs log) throws Exception {
        String sql = "Insert into Log values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, log.getId());
        ps.setString(2, log.getAccount_id());
        ps.setTimestamp(3, log.getLogin_time());
        ps.setTimestamp(4, log.getLogout_time());
        ps.setString(5, log.getNotes());
        return ps.executeUpdate() > 0;
    }

    public boolean updateLog(Logs log) throws Exception {
        String sql = "update Log set notes = ? where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(2, log.getId());
        ps.setString(1, log.getNotes());
        return ps.executeUpdate() > 0;
    }

    public boolean deletLog(Logs log) throws Exception {
        String sql = "delete from Log  where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, log.getNotes());
        return ps.executeUpdate() > 0;
    }

    public List<Logs> getAll() throws Exception {
        String sql = "select * from log";
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        List<Logs> list = new ArrayList<>();
        if (rs.next()) {
            Logs log = new Logs(rs.getInt(1), rs.getString(2),
                    rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5));
            list.add(log);
        }
        return list;
    }

    public Optional<Logs> getById(String id) throws Exception {
        String sql = "select * from log  where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Logs log = new Logs(rs.getInt(1), rs.getString(2),
                    rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5));
            return Optional.of(log);
        }
        return Optional.empty();
    }
}
