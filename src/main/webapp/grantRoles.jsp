<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cấp quyền cho tài khoản</title>
</head>
<body>
<h1>Cấp quyền cho tài khoản</h1>
<form action="ControllerServlet" method="post">
    <label >Chọn tài khoản:</label>
    <select name="accountId">
        <c:forEach var="account" items="${listAccount}">
            <option value="${account.id}">${account.full_name}</option>
        </c:forEach>
    </select>
    <label >Chọn quyền chưa được cấp:</label>
    <select name="roleId">
        <c:forEach var="role" items="${roleList}">
            <option value="${role.id}">${role.name}</option>
            ${role.id}
        </c:forEach>
    </select>
    <input type="submit" value="Cấp quyền">
    <input type="hidden" name="action" value="grantRole">
</form>
</body>
</html>
