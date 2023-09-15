<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.Account" %>
<%@ page import="vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models.GrantAccess" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2023
  Time: 9:44 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>DASHBOARD</title>
</head>
<body>
    <h1>DASHBOARD</h1>
    <a href="ControllerServlet?action=getListAccount">Xem tài khoản</a>
    <br/>
    <a href="ControllerServlet?action=getListRoles">Xem quyền</a>
    <br/>
    <a href="ControllerServlet?action=grantRoles">Cấp quyền</a>
</body>
</html>
