package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories;

import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.Connect.Connect;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class AccountReponsitory {
    private Connection connection;

    public AccountReponsitory() {
        connection = Connect.getConnection();
    }

    public Optional<Account> getAll() throws Exception{
        String sql = "select * from account";
        PreparedStatement ps = connection.prepareStatement(sql);
        

    }
}
