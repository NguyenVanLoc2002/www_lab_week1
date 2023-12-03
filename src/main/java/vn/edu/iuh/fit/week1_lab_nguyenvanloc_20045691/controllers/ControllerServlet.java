package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.controllers;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.*;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories.AccountReponsitory;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories.GrantAccessReponsitory;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories.LogReponsitory;
import vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.reponsitories.RoleReponsitory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            GrantAccessReponsitory grantAccessReponsitory = new GrantAccessReponsitory();
            RoleReponsitory roleReponsitory = new RoleReponsitory();
            AccountReponsitory accountReponsitory = new AccountReponsitory();

            List<GrantAccess> grantAccessList = grantAccessReponsitory.getAll();
            List<Role> roleList = roleReponsitory.getAll();
            List<Account> listAccount = accountReponsitory.getAll();

            HttpSession session = req.getSession();
            session.setAttribute("grantAccessList", grantAccessList);

            if (action.equals("getListAccount")) {
                session.setAttribute("listAccount", listAccount);
                resp.sendRedirect("managerAccount.jsp");
            } else if (action.equals("getListRoles")) {
                session.setAttribute("roleList", roleList);
                resp.sendRedirect("managerRole.jsp");
            } else if (action.equals("grantRoles")) {
                session.setAttribute("listAccount", listAccount);
                session.setAttribute("roleList", roleList);

                resp.sendRedirect("grantRoles.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RoleReponsitory roleReponsitory = new RoleReponsitory();
        GrantAccessReponsitory grantAccessReponsitory = new GrantAccessReponsitory();


        HttpSession session = req.getSession();
        if(action.equals("logon")) {
            logon(req, resp);
        } else if (action.equals("grantRole")) {
            boolean rs = grantRole(req, resp);
            if (rs) {
                try {
                    List<GrantAccess> grantAccessList  = grantAccessReponsitory.getAll();
                    session.setAttribute("grantAccessList", grantAccessList);
                    resp.sendRedirect("managerAccount.jsp");
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                session.getAttribute("listAccount");
                session.getAttribute("roleList");
                resp.sendRedirect("grantRoles.jsp?action=grantRoles?error=KoThemDuoc");
            }
        } else if (action.equals("logout")) {
            try {
                LogReponsitory logReponsitory = new LogReponsitory();
                List<Logs> list  = logReponsitory.getAll();
               if(!list.isEmpty()){
                   Logs log = list.get(list.size() - 1);
                   Timestamp logout_time = new Timestamp(new Date().getTime());
                   logReponsitory.updateLog(log.getId(),logout_time);
                   resp.sendRedirect("index.jsp");

               }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void logon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        try {
            AccountReponsitory accountReponsitory = new AccountReponsitory();
            Account account = accountReponsitory.getByUserName(username).orElse(null);
            HttpSession session = req.getSession();
            if (account != null && account.getPassword().equals(pass)) {
                LogReponsitory logReponsitory = new LogReponsitory();
                Timestamp login_time = new Timestamp(new Date().getTime());
                Timestamp logout_time = new Timestamp(new Date().getTime());
                logReponsitory.insertLog(account.getId(),login_time, logout_time,"");
                session.setAttribute("loggedInAccount", account);
                if (checkRoleAdmin(account.getId())) {
                    resp.sendRedirect("dashboard.jsp");
                } else {
                    resp.sendRedirect("accountInfo.jsp");
                }
            } else {
                // Chuyển hướng đến trang đăng nhập với thông báo lỗi
                resp.sendRedirect("index.jsp?error=Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean checkRoleAdmin(String id) throws Exception {
        GrantAccessReponsitory grantAccessReponsitory = new GrantAccessReponsitory();
        List<GrantAccess> grantAccessList = grantAccessReponsitory.getByAccountId(id);
        if (grantAccessList != null) {
            for (GrantAccess grantAccess : grantAccessList) {
                Role role = grantAccess.getRole();
                if (role != null) {
                    return role.getName().equalsIgnoreCase("admin");
                }
            }
        }
        return false;

    }


    public boolean grantRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account_id = req.getParameter("accountId");
        String role_id = req.getParameter("roleId");

        try {
            GrantAccessReponsitory grantAccessReponsitory = new GrantAccessReponsitory();
            AccountReponsitory accountReponsitory = new AccountReponsitory();
            RoleReponsitory roleReponsitory = new RoleReponsitory();

            // Kiểm tra xem account và role có tồn tại
            Account account = accountReponsitory.getById(account_id).orElse(null);
            Role role = roleReponsitory.getById(role_id).orElse(null);

            if (account != null && role != null) {
                List<GrantAccess> grantAccessList = grantAccessReponsitory.getAll();

                // Kiểm tra xem đã có GrantAccess tương tự chưa
                boolean grantExists = false;
                for (GrantAccess grantAccess : grantAccessList) {
                    if (grantAccess.getAccount().getId().equals(account_id) && grantAccess.getRole().getId().equals(role_id)) {
                        grantExists = true;
                        break;
                    }
                }

                // Nếu chưa có GrantAccess tương tự, thêm mới
                if (!grantExists) {
                    GrantAccess gc = new GrantAccess(role, account, Is_Grant_Enum.ONE, null);
                    return grantAccessReponsitory.insertGrantAccess(gc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
