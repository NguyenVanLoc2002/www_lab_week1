package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories;

import jakarta.servlet.AsyncContext;
import org.checkerframework.checker.units.qual.A;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect.Connect;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountReponsitory {
    private Connection connection;

    public AccountReponsitory() {
        connection = Connect.getConnection();
    }


    public boolean insertAccount(Account account) throws Exception{
        String sql = "insert into account values(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getId());
        ps.setString(2, account.getFull_name());
        ps.setString(3, account.getPassword());
        ps.setString(4, account.getEmail());
        ps.setString(5, account.getPhone());
        ps.setInt(6, account.getStatus());
        return ps.executeUpdate()>0;
    }

    public boolean updatetAccount(Account account) throws Exception{
        String sql = "update account set full_name =?, password=?, status = ? where account_id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getFull_name());
        ps.setString(2, account.getPassword());
        ps.setInt(3, account.getStatus());
        ps.setString(4, account.getId());
        return ps.executeUpdate()>0;
    }

    public boolean deleteAccount(String id) throws Exception{
        String sql = "delete from account where account_id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate()>0;
    }


    public Optional<Account> getById(String id) throws Exception{
        String sql = "select * from account where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Account account = new Account(rs.getString(1),rs.getString(2),rs.getString(3)
            ,rs.getString(4),rs.getString(5),rs.getInt(6));
            return  Optional.of(account);
        }
        return Optional.empty();
    }

    public Optional<Account> getByUserName(String name) throws Exception{
        String sql = "select * from account where full_name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Account account = new Account(rs.getString(1),rs.getString(2),rs.getString(3)
                    ,rs.getString(4),rs.getString(5),rs.getInt(6));
            return  Optional.of(account);
        }
        return Optional.empty();
    }

    public List<Account> getAll() throws Exception{
        String sql = "select * from account";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Account> list = new ArrayList<>();
        while (rs.next()){
            Account account = new Account(rs.getString(1),rs.getString(2),rs.getString(3)
                    ,rs.getString(4),rs.getString(5),rs.getInt(6));
            list.add(account);
        }
        return list;
    }
}
