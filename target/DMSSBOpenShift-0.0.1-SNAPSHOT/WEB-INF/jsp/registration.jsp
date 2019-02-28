<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>User registration</title>
    <link rel="stylesheet" href="../../style/style.css" type="text/css">

    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>

</head>
<body>
<form:form action="/registerUser" method="post" modelAttribute="user">
    <div class="login-box">
        <h1>Fill in user data</h1>
        <div class="textbox">
            <form:input type="text" placeholder="Name" path="firstName" ></form:input>
        </div>

        <div class="textbox">
            <form:input type="text" placeholder="Last name" path="lastName" value=""></form:input>
        </div>

        <div class="textbox">
            <form:input type="text" placeholder="Login" path="username" value="" ></form:input>
        </div>

        <div class="textbox">
            <form:input type="password" placeholder="Password" path="password" value=""></form:input>
        </div>

        <div class="custom-select" style="width:280px;">
            <select name="role" required>
                <option value="">Select</option>
                <option value="VIEWER">Viewer</option>
                <option value="CONTRIBUTOR">Contributor</option>
                <option value="MANAGER">Manager</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>

        <input class="btn" type="submit" name="" value="Create">

        <br>
        <br>
        <input class="btn" type="button" value="Return" onclick="location.href='/adminpanel'">

    </div>

    <form:errors path="firstName" cssStyle="color: red"/><br>
    <form:errors path="lastName" cssStyle="color: red"/><br>
    <form:errors path="username" cssStyle="color: red"/><br>
    <form:errors path="password" cssStyle="color: red"/><br>
</form:form>

<script src="../../jsscripts/dropdownmenu.js"></script>

</body>
</html>
