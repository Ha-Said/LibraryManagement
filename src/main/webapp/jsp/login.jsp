<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
<style type="text/css"> <%@ include file="/resources/css/styles.css" %> </style>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/styles.css">

</head>
<body>
    <form class="form" action="LoginServlet" method="POST">
        <p class="form-title">Sign in to your account</p>
        <div class="input-container">
            <input placeholder="Enter Username" type="text" name="username" required>
            <span>
            </span>
        </div>
        <div class="input-container">
            <input placeholder="Enter password" type="password" name="password" required>
            <span>
            </span>
        </div>
        <button class="submit" type="submit">Sign in</button>
        <p class="signup-link">
            No account? <a href="signup.jsp">Sign up</a>
        </p>
    </form>

 

</body>
</html>
