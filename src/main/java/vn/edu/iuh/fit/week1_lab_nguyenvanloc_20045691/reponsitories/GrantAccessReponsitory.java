package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories;

import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect.Connect;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.GrantAccess;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Is_Grant_Enum;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.System.*;

public class GrantAccessReponsitory {
    public Connection connection;

    public GrantAccessReponsitory() {
        connection = Connect.getConnection();
    }

    public boolean insertGrantAccess(GrantAccess grantAccess) throws Exception {
        AccountReponsitory accountReponsitory = new AccountReponsitory();
        RoleReponsitory roleReponsitory = new RoleReponsitory();
        if (accountReponsitory.getById(grantAccess.getAccount().getId()).isPresent() && roleReponsitory.getById(grantAccess.getRole().getId()).isPresent()) {
            String sql = "insert into Grant_access values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, grantAccess.getRole().getId());
            ps.setString(2, grantAccess.getAccount().getId());
            ps.setInt(3, grantAccess.getIs_grant().getValue());
            ps.setString(4, grantAccess.getNote());
            return ps.executeUpdate() > 0;
        } else
            return false;
    }

    public boolean updateGrantAccess(GrantAccess grantAccess) throws Exception {
        String sql = "update Grant_access set is_grant = ?, note =? where role_id=? and account_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, grantAccess.getIs_grant().toString());
        ps.setString(2, grantAccess.getNote());
        ps.setString(3, grantAccess.getRole().getId());
        ps.setString(4, grantAccess.getAccount().getId());
        return ps.executeUpdate() > 0;
    }

    public boolean deleteGrantAccess(GrantAccess grantAccess) throws Exception {
        String sql = "delete from Grant_access where role_id=? and account_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, grantAccess.getIs_grant().toString());
        ps.setString(2, grantAccess.getNote());
        return ps.executeUpdate() > 0;
    }

    public Optional<GrantAccess> getById(String ac_id, String role_id) throws Exception {
        String sql = "select * from grant_access  where account_id=? and role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ac_id);
        ps.setString(2, role_id);
        ResultSet rs = ps.executeQuery();
        AccountReponsitory accountReponsitory = new AccountReponsitory();
        RoleReponsitory roleReponsitory = new RoleReponsitory();
        if (rs.next()) {
            Optional<Account> optionalAccount = accountReponsitory.getById(rs.getString(2));
            Account account = optionalAccount.orElse(null);
            Optional<Role> optionalRole = roleReponsitory.getById(rs.getString(1));
            Role role = optionalRole.orElse(null);



            int isGrantValueFromDB = rs.getInt("is_grant");
            Is_Grant_Enum isGrantEnum = null;
            if (isGrantValueFromDB == 0) {
                isGrantEnum = Is_Grant_Enum.ZERO;
            } else if (isGrantValueFromDB == 1) {
                isGrantEnum = Is_Grant_Enum.ONE;
            }

            GrantAccess grantAccess = new GrantAccess(role, account, isGrantEnum, rs.getString(4));
            return Optional.of(grantAccess);
        }
        return Optional.empty();
    }

    public List<GrantAccess> getByAccountId(String ac_id) throws Exception {
        String sql = "select * from grant_access  where account_id=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ac_id);
        ResultSet rs = ps.executeQuery();
        List<GrantAccess> list = new ArrayList<>();
        AccountReponsitory accountReponsitory = new AccountReponsitory();
        RoleReponsitory roleReponsitory = new RoleReponsitory();
        while (rs.next()) {
            Optional<Account> optionalAccount = accountReponsitory.getById(rs.getString(2));
            Account account = optionalAccount.orElse(null);
            Optional<Role> optionalRole = roleReponsitory.getById(rs.getString(1));
            Role role = optionalRole.orElse(null);

            int isGrantValueFromDB = rs.getInt("is_grant");
            Is_Grant_Enum isGrantEnum = null;
            if (isGrantValueFromDB == 0) {
                isGrantEnum = Is_Grant_Enum.ZERO;
            } else if (isGrantValueFromDB == 1) {
                isGrantEnum = Is_Grant_Enum.ONE;
            }

            GrantAccess grantAccess = new GrantAccess(role, account, isGrantEnum, rs.getString(4));
            list.add(grantAccess);
        }
        return list;
    }

    public List<GrantAccess> getByRoleId(String role_id) throws Exception {
        String sql = "select * from grant_access  where role_id=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, role_id);
        ResultSet rs = ps.executeQuery();
        List<GrantAccess> list = new ArrayList<>();
        AccountReponsitory accountReponsitory = new AccountReponsitory();
        RoleReponsitory roleReponsitory = new RoleReponsitory();
        while (rs.next()) {
            Optional<Account> optionalAccount = accountReponsitory.getById(rs.getString(2));
            Account account = optionalAccount.orElse(null);
            Optional<Role> optionalRole = roleReponsitory.getById(rs.getString(1));
            Role role = optionalRole.orElse(null);

            int isGrantValueFromDB = rs.getInt("is_grant");
            Is_Grant_Enum isGrantEnum = null;
            if (isGrantValueFromDB == 0) {
                isGrantEnum = Is_Grant_Enum.ZERO;
            } else if (isGrantValueFromDB == 1) {
                isGrantEnum = Is_Grant_Enum.ONE;
            }

            GrantAccess grantAccess = new GrantAccess(role, account, isGrantEnum, rs.getString(4));
            list.add(grantAccess);
        }
        return list;
    }

    public List<GrantAccess> getAll() throws Exception {
        String sql = "select * from grant_access";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<GrantAccess> list = new ArrayList<>();
        AccountReponsitory accountReponsitory = new AccountReponsitory();
        RoleReponsitory roleReponsitory = new RoleReponsitory();
        while (rs.next()) {
            Optional<Account> optionalAccount = accountReponsitory.getById(rs.getString(2));
            Account account = optionalAccount.orElse(null);
            Optional<Role> optionalRole = roleReponsitory.getById(rs.getString(1));
            Role role = optionalRole.orElse(null);

            int isGrantValueFromDB = rs.getInt("is_grant");
            Is_Grant_Enum isGrantEnum = null;
            if (isGrantValueFromDB == 0) {
                isGrantEnum = Is_Grant_Enum.ZERO;
            } else if (isGrantValueFromDB == 1) {
                isGrantEnum = Is_Grant_Enum.ONE;
            }

            GrantAccess grantAccess = new GrantAccess(role, account, isGrantEnum, rs.getString(4));
            list.add(grantAccess);
        }
        return list;
    }



}
