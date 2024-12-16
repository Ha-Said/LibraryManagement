<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, entity.Book" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Loans</title>
</head>
<body>
    <h1>Manage Loans</h1>

    <h2>Borrowed Books</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Action</th>
        </tr>
        <%
            // Retrieve the borrowedBooks list from the request attribute
            Object borrowedBooksObject = request.getAttribute("borrowedBooks");

            if (borrowedBooksObject instanceof List) {
                @SuppressWarnings("unchecked")
                List<Book> borrowedBooks = (List<Book>) borrowedBooksObject;
                if (!borrowedBooks.isEmpty()) {
                    // Loop through the borrowedBooks list and display each book
                    for (Book book : borrowedBooks) {
        %>
                        <tr>
                            <td><%= book.getTitle() %></td>
                            <td><%= book.getAuthor() %></td>
                            <td>
                             <form method="post" action="${pageContext.request.contextPath}/manageLoans">
    <input type="hidden" name="bookId" value="<%= book.getId() %>">
    <input type="hidden" name="action" value="return">
    <button type="submit">Return</button>
</form>


                            </td>
                        </tr>
                    <%
                    }
                } else {
        %>
                    <tr><td colspan="3">No borrowed books available.</td></tr>
                <%
                }
            } else {
        %>
                <tr><td colspan="3">Error: Unable to retrieve borrowed books.</td></tr>
        <% 
            }
        %>
    </table>

    <h2>Available Books</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Action</th>
        </tr>
        <%
            // Retrieve the availableBooks list from the request attribute
            Object availableBooksObject = request.getAttribute("availableBooks");

            if (availableBooksObject instanceof List) {
                @SuppressWarnings("unchecked")
                List<Book> availableBooks = (List<Book>) availableBooksObject;
                if (!availableBooks.isEmpty()) {
                    // Loop through the availableBooks list and display each book
                    for (Book book : availableBooks) {
        %>
                        <tr>
                            <td><%= book.getTitle() %></td>
                            <td><%= book.getAuthor() %></td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/manageLoans">
    <input type="hidden" name="bookId" value="<%= book.getId() %>">
    <input type="hidden" name="action" value="loan">
    <button type="submit">Loan</button>
</form>

                            </td>
                        </tr>
                    <%
                    }
                } else {
        %>
                    <tr><td colspan="3">No available books at the moment.</td></tr>
                <%
                }
            } else {
        %>
                <tr><td colspan="3">Error: Unable to retrieve available books.</td></tr>
        <% 
            }
        %>
    </table>

</body>
</html>
