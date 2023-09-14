package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories.AccountReponsitory;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("getListAccount")) {
            try {
//                AccountReponsitory accountRepository = new AccountReponsitory();
//                List<Account> accountList = accountRepository.getAll();
//                req.setAttribute("accountList", accountList);
//                RequestDispatcher dispatcher = req.getRequestDispatcher("accountList.jsp");
//                dispatcher.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("logon")) {
            Logon(req,resp);
        }

    }

    public void Logon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        try {
            AccountReponsitory accountReponsitory =  new AccountReponsitory();
            Account account = accountReponsitory.getByUserName(username).orElse(null);
            if(account!=null && account.getPassword().equals(pass)){
                HttpSession session = req.getSession();
                session.setAttribute("loggedInAccount", account);
//                if (account.isAdmin()) {
//                    resp.sendRedirect("dashboard.jsp"); // Đường dẫn đến trang Dashboard
//                } else {
//                    resp.sendRedirect("accountInfo.jsp"); // Đường dẫn đến trang thông tin tài khoản
//                }
                resp.sendRedirect("accountInfo.jsp");
            }else{
                // Chuyển hướng đến trang đăng nhập với thông báo lỗi
                resp.sendRedirect("index.jsp?error=Not Found");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
