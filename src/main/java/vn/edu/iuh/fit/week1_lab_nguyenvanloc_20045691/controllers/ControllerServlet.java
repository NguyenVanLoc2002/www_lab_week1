package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories.AccountReponsitory;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("getListAccount")){
            try {
                AccountReponsitory accountRepository = new AccountReponsitory();
                List<Account> accountList = accountRepository.getAll();
                req.setAttribute("accountList", accountList);
                RequestDispatcher dispatcher = req.getRequestDispatcher("accountList.jsp");
                dispatcher.forward(req, resp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
