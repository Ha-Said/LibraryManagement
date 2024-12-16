<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Dashboard</title>
</head>
<body>

    <h1>Welcome to the Library Dashboard</h1>

    <div class="button-container">
        <!-- Button for managing loans wrapped in a form for POST request -->
       
        <form action="dashboard" method="post">
    <button type="submit" name="action" value="manageLoans">Manage Loans</button>
</form>
        
        <button onclick="location.href='ViewBooks'">View Books</button>
        <button onclick="location.href='LogoutServlet'">Logout</button>
    </div>

</body>
</html>
