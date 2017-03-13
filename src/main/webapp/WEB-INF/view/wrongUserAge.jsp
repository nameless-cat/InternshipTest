<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/resources/err-style.css" type="text/css">

</head>
<body>

<div class="error-container">
    <span class="error">User age must be natural number and bigger than zero.</span>
    <div class="return-button">
        <a class="btn" href="/getAllUsers">Got it!</a>
    </div>
</div>

</body>
</html>

