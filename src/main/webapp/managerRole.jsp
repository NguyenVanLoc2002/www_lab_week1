<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2023
  Time: 4:13 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Quản lí quyền</title>
</head>
<body>
<h1>Quản lí quyền</h1>
<ul>
    <c:forEach var="role" items="${roleList}">
        <li>Role Name: ${role.name}</li>
        <ul>
            <li>Account Name:</li>
            <c:forEach var="gc" items="${grantAccessList}">
                <c:if test="${gc.role.id == role.id}">
                    ${gc.account.full_name}
                    <br/>
                </c:if>
            </c:forEach>
        </ul>
        <br/>
    </c:forEach>
</ul>
</body>
</html>

