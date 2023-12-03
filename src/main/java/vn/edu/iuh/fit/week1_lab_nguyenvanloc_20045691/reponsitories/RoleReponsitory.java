package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories;

import jakarta.ws.rs.core.Response;
import jdk.jshell.Snippet;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect.Connect;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleReponsitory {
    private Connection connection;

    public RoleReponsitory() {
        connection = Connect.getConnection();
    }

    public boolean insertRole(Role role) throws Exception {
        String sql = "insert into Role values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, role.getId());
        ps.setString(2, role.getName());
        ps.setString(3, role.getDiscription());
        ps.setInt(4, role.getStatus());
        return ps.executeUpdate() > 0;
    }

    public boolean updateRole(Role role) throws Exception {
        String sql = "update Role set role_name=?, description =? , status=?  where role_id=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, role.getName());
        ps.setString(2, role.getDiscription());
        ps.setInt(3, role.getStatus());
        ps.setString(4, role.getId());
        return ps.executeUpdate() > 0;
    }

    public boolean deleteRole(String id) throws Exception {
        String sql = "delete from role where role_id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() > 0;
    }

    public Optional<Role> getById(String id) throws Exception {
        String sql = "select * from Role  where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Role role = new Role(rs.getString(1),
                    rs.getString(2), rs.getString(3), rs.getInt(4));
            return Optional.of(role);
        }
        return Optional.empty();
    }

    public List<Role> getAll() throws Exception {
        String sql = "select * from role";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Role> list = new ArrayList<>();
        while (rs.next()) {
            Role role = new Role(rs.getString(1),
                    rs.getString(2), rs.getString(3), rs.getInt(4));
            list.add(role);
        }
        return list;
    }

    public List<Role> getRoleNotExist(String acId) throws Exception{
        String sql = "SELECT R.*" +
                "FROM role R" +
                "LEFT JOIN grant_access GA ON R.role_id = GA.role_id AND GA.account_id = ? WHERE GA.role_id IS NULL";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, acId);
        ResultSet rs = ps.executeQuery();
        List<Role> list = new ArrayList<>();
        while (rs.next()) {
            Role role = new Role(rs.getString(1),
                    rs.getString(2), rs.getString(3), rs.getInt(4));
            list.add(role);
        }
        return list;
    }


}
