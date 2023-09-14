<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14/09/2023
  Time: 6:13 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h1>Dashboard</h1>
    <c:if test="${not empty loggedInAccount}">
        <p>Xin chào, ${loggedInAccount.full_name}!</p>
        <p>Email: ${loggedInAccount.email}</p>
        <p>Phone: ${loggedInAccount.phone}</p>
        <p>Trạng thái: ${loggedInAccount.status}</p>
    </c:if>

    <a href="logout.jsp">Đăng xuất</a>
</body>
</html>
