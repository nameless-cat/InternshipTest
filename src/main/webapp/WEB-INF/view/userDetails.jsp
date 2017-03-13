<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/resources/style.css" type="text/css">

</head>
<body>
<form:form method="post" enctype="application/x-www-form-urlencoded" modelAttribute="user" action="/addUser">
    <table>
        <tr>
            <th colspan="2">Add User</th>
        </tr>
        <tr>
            <form:hidden path="id" />
            <td><form:label path="name">User Name:</form:label></td>
            <td><form:input path="name" size="25" maxlength="25" placeholder="alphabetic, digits, -"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="age">Age:</form:label></td>
            <td><form:input path="age" size="3" maxlength="3"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="admin">Admin</form:label></td>
            <td><form:checkbox path="admin"></form:checkbox></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="blue-button"/></td>
        </tr>
    </table>
</form:form>
</br>
<c:if test="${!empty listOfUsers}">

    <div class="filter-container">
        <form:form method="post" enctype="application/x-www-form-urlencoded" action="/filter" modelAttribute="user">
            <label >Filter by name:</label>
            <form:input path="name" size="25" maxlength="25" value="${filter}" placeholder="alphabetic, digits, -" />
            <input type="submit" class="blue-button" value="Filter"/>
        </form:form>
    </div>

    <div class="nav-container">
            ${navigator}
    </div>
    <table class="tg">
        <tr>
            <th class="user-id">Id</th>
            <th class="user-name">Name</th>
            <th class="user-age">Age</th>
            <th class="user-admin">Admin</th>
            <th class="user-date">Date</th>
            <th class="user-edit">Edit</th>
            <th class="user-delete">Delete</th>
        </tr>
        <c:forEach items="${listOfUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.admin}</td>
                <td>${user.date}</td>
                <td><a href="<c:url value='/updateUser/${user.id}' />">Edit</a></td>
                <td><a href="<c:url value='/deleteUser/${user.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<c:if test="${empty listOfUsers}">
    <a href="<c:url value='/spawnUserTestData' />">Spawn user test data</a>
</c:if>
</body>
</html>
