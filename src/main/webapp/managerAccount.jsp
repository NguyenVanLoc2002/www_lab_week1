<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2023
  Time: 4:06 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Quản lí tài khoản</title>
</head>
<body>
<h1>Quản lí tài khoản</h1>
<ul>
    <c:forEach var="account" items="${listAccount}">
        <li>User Name: ${account.full_name}</li>
        <ul>
            <li>Role Name:</li>
            <c:forEach var="gc" items="${grantAccessList}">
                <c:if test="${gc.account.id == account.id}">
                    ${gc.role.name}
                    <br/>
                </c:if>
            </c:forEach>
        </ul>
        <br/>
    </c:forEach>
</ul>
</body>
</html>


