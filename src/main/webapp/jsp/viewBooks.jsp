<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Books</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
    <h1>Available Books</h1>

    <%
        Object booksObject = request.getAttribute("books");

        if (booksObject instanceof List) {
            @SuppressWarnings("unchecked")
            List<Book> books = (List<Book>) booksObject;
            if (!books.isEmpty()) {
    %>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Price</th>
                            <th>Pages</th>
                            <th>Available</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Book book : books) { %>
                            <tr>
                                <td><%= book.getId() %></td>
                                <td><%= book.getTitle() %></td>
                                <td><%= book.getAuthor() %></td>
                                <td><%= book.getPrice() %></td>
                                <td><%= book.getPages() %></td>
                                <td><%= book.isAvailable() ? "Yes" : "No" %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
    <%
            } else {
    %>
                <p>No books available at the moment.</p>
    <%
            }
        } else {
    %>
            <p>Error: Unable to retrieve books data.</p>
    <%
        }
    %>
</body>
</html>
