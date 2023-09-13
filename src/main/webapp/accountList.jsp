<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/09/2023
  Time: 4:26 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách tài khoản</title>
</head>
<body>
<h1>Danh sách tài khoản</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Họ và tên</th>
        <th>Mật khẩu</th>
        <th>Email</th>
        <th>Số điện thoại</th>
        <th>Trạng thái</th>
    </tr>
    <c:forEach var="account" items="${accountList}">
        <tr>
            <td>${account.account_id}</td>
            <td>${account.full_name}</td>
            <td>${account.password}</td>
            <td>${account.email}</td>
            <td>${account.phone}</td>
            <td>${account.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
